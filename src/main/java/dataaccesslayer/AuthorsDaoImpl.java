package dataaccesslayer;

import java.util.List;

import transferobjects.AuthorDTO;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class as the data access object implements the methods defined by its interface.
 * It contains six methods including returning string arrays recoding metadata, 
 * retrieving all authors and conventional CRUD.
 * Each method handles the SQLException by convention.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see transferobjects.AuthorDTO
 * @see java.util.ArrayList
 * @see java.sql.PreparedStatement
 * @see java.sql.Connection
 * @see java.sql.ResultSet
 * @see java.sql.ResultSetMetaData
 * @see java.sql.SQLException
 * @since JDK 21 (Default)
 */
public class AuthorsDaoImpl implements AuthorsDao{
    
    /**
     * This is the default constructor for AuthorsDaoImpl.
     * 
     * It can be used to initialize a new instance of AuthorsDaoImpl.
     */
    public AuthorsDaoImpl() {
    
    }
    
    /**
     * This method is implemented to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    @Override
    public List<String[]> getMetaData() {
        
        // declare a null ResultSetMetaData to store the metadata
        ResultSetMetaData metaData = null;
        
        // declare a integer to record the number of columns in the metadata
        int numberOfColumns = 0;
        
        // declare a null ArrayList to keep string arrays recoding metadata
        ArrayList<String[]> metaDataStrings = null;
        
        // prepare querying statement to access ResultSetMetaData
        String query = "SELECT * FROM Authors LIMIT 1";
        
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                // retrieve data by executing
                ResultSet resultSet = prepQuery.executeQuery();
                
                ) {
            
            // process query results
            metaData = resultSet.getMetaData();
            numberOfColumns = metaData.getColumnCount();
            metaDataStrings = new ArrayList<>();
            
            // use for loop to obtain column attributes from the metadata
            for (int i = 1; i <= numberOfColumns; i++) {
                
                String[] strings = new String[3];
                strings[0] = metaData.getColumnName(i);
                strings[1] = metaData.getColumnTypeName(i);
                strings[2] = metaData.getColumnClassName(i);
                metaDataStrings.add(strings);
                
            } // end for loop
            
        } // end try
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
        return metaDataStrings;
        
    } // end method getMetaData
    
    /**
     * This method is implemented to return a list including all authors data.
     * 
     * @return The list contains all authors data.
     */
    @Override
    public List<AuthorDTO> getAllAuthors() {
        
        // declare a null ArrayList to store all authors data retrieved
        ArrayList<AuthorDTO> authors = null;
        
        // prepare querying statement for retrieving all authors data
        String query = "SELECT * FROM Authors";
        
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                // retrieve data by executing
                ResultSet resultSet = prepQuery.executeQuery();
                
                ) {
            
            authors = new ArrayList<AuthorDTO>();
            
            // check whether the matching entries are found or not
            if(resultSet.next()) {
                
                do {
                    
                    // get each attribute of authors data retrieved
                    AuthorDTO author =  new AuthorDTO();
                    author.setAuthorID(resultSet.getInt("AuthorID"));
                    author.setFirstName(resultSet.getString("FirstName"));
                    author.setLastName(resultSet.getString("LastName"));
                    authors.add(author);
                
                } while (resultSet.next());
                // end do-while
                
            } else {
            
                // print an appropriate message if no matching entries found
                System.out.println("No results found as there is no author.");
            
            } // end if
            
        } // end try
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
        return authors;
        
    } // end method getAllAuthors
    
    /**
     * This method is implemented to retrieve an author data in the kind of DTO 
     * according to AuthorID received.
     * 
     * @param authorID The AuthorID received to retrieve a specific author
     * @return The AuthorDTO based on AuthorID derived
     */
    @Override
    public AuthorDTO getAuthorByAuthorID(Integer authorID) {
        
        // declare a null AuthorDTO to store the author retrieved
        AuthorDTO author = null;
        
        // prepare querying statement for retrieving an author according to AuthorID received
        String query = "SELECT * FROM Authors WHERE AuthorID = ?";
        
        // try-with-resources on outer layer in the method retrieving an author
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholder with AuthorID received
            prepQuery.setInt(1, authorID);
            
            // nested try-with-resources on inner layer in the method retrieving an author
            try(
                    
                    // retrieve data by executing
                    ResultSet resultSet = prepQuery.executeQuery();
                    
                    ) {
                
                // check whether the matching entry is found or not
                if(resultSet.next()) {
                    
                    // get each attribute of the result data retrieved
                    author = new AuthorDTO();
                    author.setAuthorID(resultSet.getInt("AuthorID"));
                    author.setFirstName(resultSet.getString("FirstName"));
                    author.setLastName(resultSet.getString("LastName"));
                
                } else {
            
                    // print an appropriate message if no matching entry found
                    System.out.printf("No results found for the AuthorID: %d.", authorID);
            
                } // end if
            
            } // end nested try on inner layer in the method retrieving an author
            
        } // end nested try on outer layer in the method retrieving an author
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
            
        return author;
        
    } // end method getAuthorByAuthorID
    
    /**
     * This method is implemented to insert an author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be inserted to data source
     */
    @Override
    public void addAuthor(AuthorDTO author) {
        
        // prepare querying statement for inserting an author according to the DTO received
        String query = "INSERT INTO Authors (FirstName, LastName) "
                       + "VALUES(?, ?)";
        
        // try-with-resources in the method inserting an author
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholders with the attributes drived from the AuthorDTO received
            prepQuery.setString(1, author.getFirstName());
            prepQuery.setString(2, author.getLastName());
            
            // call executeUpdate() to insert
            prepQuery.executeUpdate();
            
        } // end try in the method inserting an author
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method addAuthor
    
    /**
     * This method is implemented to update an author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be updated in data source
     */
    @Override
    public void updateAuthor(AuthorDTO author) {
        
        // prepare querying statement for updating an author according to the DTO received
        String query = "UPDATE Authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?";
        
        // try-with-resources in the method updating an author
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholders with the attributes drived from the AuthorDTO received
            prepQuery.setString(1, author.getFirstName());
            prepQuery.setString(2, author.getLastName());
            prepQuery.setInt(3, author.getAuthorID().intValue());
            
            // call executeUpdate() to update
            prepQuery.executeUpdate();
            
        } // end try in the method updating an author
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method updateAuthor
    
    /**
     * This method is implemented to delete an author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be deleted in data source
     */
    @Override
    public void deleteAuthor(AuthorDTO author) {
        
        // prepare querying statement for deleting an author according to the DTO received
        String query = "DELETE FROM Authors WHERE AuthorID = ?";
        
        // try-with-resources in the method deleting an author
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholder with AuthorID drived from the AuthorDTO received
            prepQuery.setInt(1, author.getAuthorID().intValue());
            
            // call executeUpdate() to delete
            prepQuery.executeUpdate();
            
        } // end try in the method deleting an author
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method deleteAuthor
    
} // end class AuthorsDaoImpl
