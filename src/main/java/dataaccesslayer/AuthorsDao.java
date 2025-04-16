package dataaccesslayer;

import java.util.List;
import transferobjects.AuthorDTO;

/**
 * This interface defines the methods that a DAO class is supposed to implement.
 * It contains six methods including returning string arrays recording metadata, 
 * retrieving all authors and conventional CRUD. 
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see transferobjects.AuthorDTO
 * @since JDK 21 (Default)
 */
public interface AuthorsDao {
    
    /**
     * This method is defined to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    List<String[]> getMetaData();
    
    /**
     * This method is defined to return a list including all authors data.
     * 
     * @return The list contains all authors data.
     */
    List<AuthorDTO> getAllAuthors();
    
    /**
     * This method is defined to retrieve an author data in the kind of DTO 
     * according to AuthorID received.
     * 
     * @param authorID The AuthorID received to retrieve a specific author
     * @return The AuthorDTO based on AuthorID derived
     */
    AuthorDTO getAuthorByAuthorID(Integer authorID);
    
    /**
     * This method is defined to insert an author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be inserted to data source
     */
    void addAuthor(AuthorDTO author);
    
    /**
     * This method is defined to update an author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be updated to data source
     */
    void updateAuthor(AuthorDTO author);
    
    /**
     * This method is defined to delete an author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be deleted in data source
     */
    void deleteAuthor(AuthorDTO author);

}
