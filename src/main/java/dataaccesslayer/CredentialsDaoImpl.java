package dataaccesslayer;

import java.util.List;

import transferobjects.CredentialsDTO;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class as the data access object implements the methods defined by its interface.
 * It contains six(6) methods including returning string arrays recoding metadata, 
 * retrieving all credentials and conventional CRUD.
 * Each method handles the SQLException by convention.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see transferobjects.CredentialsDTO
 * @see java.util.ArrayList
 * @see java.sql.PreparedStatement
 * @see java.sql.Connection
 * @see java.sql.ResultSet
 * @see java.sql.ResultSetMetaData
 * @see java.sql.SQLException
 * @since JDK 21 (Default)
 */
public class CredentialsDaoImpl implements CredentialsDao{
    
    /**
     * This is the default constructor for CredentialsDaoImpl.
     * 
     * It can be used to initialize a new instance of CredentialsDaoImpl.
     */
    public CredentialsDaoImpl() {
    
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
        String query = "SELECT * FROM Credentials LIMIT 1";
        
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
     * This method is implemented to return a list including all credentials data.
     * 
     * @return The list contains all credentials data.
     */
    @Override
    public List<CredentialsDTO> getAllCredentials() {
        
        // declare a null ArrayList to store all credentials data retrieved
        ArrayList<CredentialsDTO> credentials = null;
        
        // prepare querying statement for retrieving all credentials data
        String query = "SELECT * FROM Credentials";
        
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                // retrieve data by executing
                ResultSet resultSet = prepQuery.executeQuery();
                
                ) {
            
            credentials = new ArrayList<CredentialsDTO>();
            
            // check whether the matching entries are found or not
            if(resultSet.next()) {
                
                do {
                    
                    // get each attribute of credentials data retrieved
                    CredentialsDTO credential =  new CredentialsDTO();
                    credential.setCredentialID(resultSet.getInt("CredentialID"));
                    credential.setUsername(resultSet.getString("Username"));
                    credential.setPassword(resultSet.getString("Password"));
                    credentials.add(credential);
                
                } while (resultSet.next());
                // end do-while
                
            } else {
            
                // print an appropriate message if no matching entries found
                System.out.println("No results found as there is no credential.");
            
            } // end if
            
        } // end try
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
        return credentials;
        
    } // end method getAllCredentials
    
    /**
     * This method is implemented to retrieve a credential data in the kind of DTO 
     * according to CredentialID received.
     * 
     * @param credentialID The CredentialID received to retrieve a specific credential
     * @return The CredentialsDTO based on CredentialID derived
     */
    @Override
    public CredentialsDTO getCredentialByCredentialID(Integer credentialID) {
        
        // declare a null CredentialsDTO to store the credential retrieved
        CredentialsDTO credential = null;
        
        // prepare querying statement for retrieving a credential according to CredentialID received
        String query = "SELECT * FROM Credentials WHERE CredentialID = ?";
        
        // try-with-resources on outer layer in the method retrieving a credential
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholder with CredentialID received
            prepQuery.setInt(1, credentialID);
            
            // nested try-with-resources on inner layer in the method retrieving a credential
            try(
                    
                    // retrieve data by executing
                    ResultSet resultSet = prepQuery.executeQuery();
                    
                    ) {
                
                // check whether the matching entry is found or not
                if(resultSet.next()) {
                    
                    // get each attribute of the result data retrieved
                    credential = new CredentialsDTO();
                    credential.setCredentialID(resultSet.getInt("CredentialID"));
                    credential.setUsername(resultSet.getString("Username"));
                    credential.setPassword(resultSet.getString("Password"));
                
                } else {
            
                    // print an appropriate message if no matching entry found
                    System.out.printf("No results found for the CredentialID: %d.", credentialID);
            
                } // end if
            
            } // end nested try on inner layer in the method retrieving a credential
            
        } // end nested try on outer layer in the method retrieving a credential
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
            
        return credential;
        
    } // end method getCredentialByCredentialID
    
    /**
     * This method is implemented to insert a credential data in the kind of DTO to data source.
     * 
     * @param credential The CredentialsDTO received to be inserted to data source
     */
    @Override
    public void addCredential(CredentialsDTO credential) {
        
        // prepare querying statement for inserting a credential according to the DTO received
        String query = "INSERT INTO Credentials (CredentialID, Username, Password) "
                       + "VALUES(?, ?, ?)";
        
        // try-with-resources in the method inserting a credential
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholders with the attributes drived from the CredentialsDTO received
            prepQuery.setInt(1, credential.getCredentialID().intValue());
            prepQuery.setString(2, credential.getUsername());
            prepQuery.setString(3, credential.getPassword());
            
            // call executeUpdate() to insert
            prepQuery.executeUpdate();
            
        } // end try in the method inserting a credential
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method addCredential
    
    /**
     * This method is implemented to update a credential data in the kind of DTO to data source.
     * 
     * @param credential The CredentialsDTO received to be updated in data source
     */
    @Override
    public void updateCredential(CredentialsDTO credential) {
        
        // prepare querying statement for updating a credential according to the DTO received
        String query = "UPDATE Credentials SET Username = ?, Password = ? WHERE CredentialID = ?";
        
        // try-with-resources in the method updating a credential
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholders with the attributes drived from the CredentialsDTO received
            prepQuery.setString(1, credential.getUsername());
            prepQuery.setString(2, credential.getPassword());
            prepQuery.setInt(3, credential.getCredentialID().intValue());
            
            // call executeUpdate() to update
            prepQuery.executeUpdate();
            
        } // end try in the method updating a credential
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method updateCredential
    
    /**
     * This method is implemented to delete a credential data in the kind of DTO to data source.
     * 
     * @param credential The CredentialsDTO received to be deleted in data source
     */
    @Override
    public void deleteCredential(CredentialsDTO credential) {
        
        // prepare querying statement for deleting a credential according to the DTO received
        String query = "DELETE FROM Credentials WHERE CredentialID = ?";
        
        // try-with-resources in the method deleting a credential
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholder with CredentialID drived from the CredentialsDTO received
            prepQuery.setInt(1, credential.getCredentialID().intValue());
            
            // call executeUpdate() to delete
            prepQuery.executeUpdate();
            
        } // end try in the method deleting a credential
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method deleteCredential
    
} // end class CredentialsDaoImpl
