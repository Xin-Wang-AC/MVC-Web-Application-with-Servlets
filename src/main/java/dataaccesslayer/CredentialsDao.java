package dataaccesslayer;

import java.util.List;
import transferobjects.CredentialsDTO;

/**
 * This interface defines the methods that a DAO class is supposed to implement.
 * It contains six methods including returning string arrays recording metadata, 
 * retrieving all credentials and conventional CRUD. 
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see transferobjects.CredentialsDTO
 * @see java.sql.SQLException
 * @since JDK 21 (Default)
 */
public interface CredentialsDao {
    
    /**
     * This method is defined to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    List<String[]> getMetaData();
    
    /**
     * This method is defined to return a list including all credentials data.
     * 
     * @return The list contains all credentials data.
     */
    List<CredentialsDTO> getAllCredentials();
    
    /**
     * This method is defined to retrieve a credential data in the kind of DTO 
     * according to CredentialID received.
     * 
     * @param credentialID The CredentialID received to retrieve a specific credential
     * @return The CredentialsDTO based on CredentialID derived
     */
    CredentialsDTO getCredentialByCredentialID(Integer credentialID);
    
    /**
     * This method is defined to insert a credential data in the kind of DTO to data source.
     * 
     * @param credential The CredentialsDTO received to be inserted to data source
     */
    void addCredential(CredentialsDTO credential);
    
    /**
     * This method is defined to update a credential data in the kind of DTO to data source.
     * 
     * @param credential The CredentialsDTO received to be updated to data source
     */
    void updateCredential(CredentialsDTO credential);
    
    /**
     * This method is defined to delete a credential data in the kind of DTO to data source.
     * 
     * @param credential The CredentialsDTO received to be deleted in data source
     */
    void deleteCredential(CredentialsDTO credential);

}
