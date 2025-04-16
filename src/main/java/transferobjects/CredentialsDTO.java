package transferobjects;

import java.io.Serializable;

/**
 * This class as the data transfer object encapsulates credentials data.
 * It contains two attributes representing a credential 
 * including CredentialID, Username and Password. 
 * There are several getters and setters one pair per attribute.
 * 
 * @author George Kriger
 * @author Xin Wang
 * @version 1.0
 * @see java.io.Serializable
 * @since JDK 21 (Default)
 */
public class CredentialsDTO implements Serializable {
    
    /**
     * Represents CredentialID.
     */
    private Integer credentialID;
    /**
     * Represents Username.
     */
    private String username;
    /**
     * Represents Password.
     */
    private String password;
    
    /**
     * This is the default constructor for CredentialsDTO.
     * 
     * It can be used to initialize a new instance of CredentialsDTO.
     */
    public CredentialsDTO() {
    
    }
    
    /**
     * This method as a getter returns the credentialID.
     * 
     * @return The credentialID to get
     */
    public Integer getCredentialID() {
        return credentialID;
    }
    
    /**
     * This method as a setter sets the credentialID.
     * 
     * @param credentialID The credentialID to set
     */
    public void setCredentialID(Integer credentialID) {
        this.credentialID = credentialID;
    }
    
    /**
     * simple getter for user name portion of the credentials
     * @return user name portion of the credentials
     */
    public String getUsername() {
        return username;
    }

    /**
     * simple setter for user name portion of the credentials
     * @param username user name portion of the credentials
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * simple getter for password portion of the credentials
     * @return password portion of the credentials
     */
    public String getPassword() {
        return password;
    }

    /**
     * simple setter for password portion of the credentials
     * @param password password portion of the credentials
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
} // End class CredentialsDTO
