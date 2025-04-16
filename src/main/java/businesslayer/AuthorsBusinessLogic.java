package businesslayer;

import java.util.List;
import dataaccesslayer.AuthorsDao;
import dataaccesslayer.AuthorsDaoImpl;
import transferobjects.AuthorDTO;
import dataaccesslayer.CredentialsDao;
import dataaccesslayer.CredentialsDaoImpl;
import transferobjects.CredentialsDTO;

/**
 * This class as the business logic calls the methods implemented 
 * in AuthorsDaoImpl class and CredentialsDaoImpl class on data access layer.
 * It contains a default constructor and seven methods 
 * including calling each method in AuthorsDaoImpl class 
 * and several methods in CredentialsDaoImpl class.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see dataaccesslayer.AuthorsDao
 * @see dataaccesslayer.AuthorsDaoImpl
 * @see transferobjects.AuthorDTO
 * @see dataaccesslayer.CredentialsDao
 * @see dataaccesslayer.CredentialsDaoImpl
 * @see transferobjects.CredentialsDTO
 * @since JDK 21 (Default)
 */
public class AuthorsBusinessLogic {
    
    // declare a null AuthorsDao to store the reference to authorsDao instance
    private AuthorsDao authorsDao = null;
    
    // declare a null CredentialsDao to store the reference to credentialsDao instance
    private CredentialsDao credentialsDao = null;
    
    /**
     * This is the default constructor for AuthorsBusinessLogic.
     * It is used to initialize 
     * a new instance of AuthorsDaoImpl and a new instance of CredentialsDaoImpl.
     */
    public AuthorsBusinessLogic() {
        authorsDao = new AuthorsDaoImpl();
        credentialsDao = new CredentialsDaoImpl();
    }
    
    /**
     * This method calls authorsDao instance to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    public List<String[]> getMetaData() {
        return authorsDao.getMetaData();
    }
    
    /**
     * This method calls authorsDao instance to return a list including all authors data.
     * 
     * @return The list contains all authors data.
     */
    public List<AuthorDTO> getAllAuthors() {
        return authorsDao.getAllAuthors();
    }
    
    /**
     * This method calls authorsDao instance to retrieve a author data in the kind of DTO 
     * according to AuthorID received.
     * 
     * @param authorID The AuthorID received to retrieve a specific author
     * @return The AuthorDTO based on AuthorID derived
     */
    public AuthorDTO getAuthor(Integer authorID) {
        return authorsDao.getAuthorByAuthorID(authorID);
    }
    
    /**
     * This method calls authorsDao instance to insert a author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be inserted to data source
     */
    public void addAuthor(AuthorDTO author) {
        authorsDao.addAuthor(author);
    }
    
    /**
     * This method calls authorsDao instance to update a author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be updated in data source
     */
    public void updateAuthor(AuthorDTO author) {
        authorsDao.updateAuthor(author);
    }
    
    /**
     * This method calls authorsDao instance to delete a author data in the kind of DTO to data source.
     * 
     * @param author The AuthorDTO received to be deleted in data source
     */
    public void deleteAuthor(AuthorDTO author) {
        authorsDao.deleteAuthor(author);
    }
    
    /**
     * This method calls credentialsDao instance to return a list including all credentials data.
     * 
     * @return The list contains all credentials data.
     */
    public List<CredentialsDTO> getAllCredentials() {
        return credentialsDao.getAllCredentials();
    }
    
    /**
     * This method calls credentialsDao instance to return a list including all credentials data,
     * then verifies whether the CredentialsDTO received is valid or not.
     * 
     * @param credential The credential received
     * @return True if the CredentialsDTO received is valid, False otherwise.
     */
    public boolean validateCredentials(CredentialsDTO credential) {
        
        boolean validCredential = false;
        
        String currentUsername = credential.getUsername();
        String currentPassword = credential.getPassword();
        
        List<CredentialsDTO> list = credentialsDao.getAllCredentials();
        
        for (CredentialsDTO creds : list) {
            String username = creds.getUsername();
            String password = creds.getPassword();
            
            if (currentUsername.equals(username) && currentPassword.equals(password)) {
                validCredential = true;
                return validCredential;
            }
        }
        
        return validCredential;
    }
    
}
