package transferobjects;

import java.io.Serializable;

/**
 * This class as the data transfer object encapsulates authors data.
 * It contains three attributes representing a author 
 * including AuthorID, FirstName and LastName. 
 * There are several getters and setters one pair per attribute.
 * 
 * @author George Kriger
 * @author Xin Wang
 * @version 1.0
 * @see java.io.Serializable
 * @since JDK 21 (Default)
 */
public class AuthorDTO implements Serializable {
    
    /**
     * Represents AutherID.
     */
    private Integer authorID;
    /**
     * Represents FirstName.
     */
    private String firstName;
    /**
     * Represents LastName.
     */
    private String lastName;
    
    /**
     * This is the default constructor for AuthorDTO.
     * 
     * It can be used to initialize a new instance of AuthorDTO.
     */
    public AuthorDTO() {
    
    }
    
    /**
     * This is the partially overloaded constructor for AuthorDTO.
     * 
     * @param firstName The first name to load
     * @param lastName The last name to load
     */
    public AuthorDTO(String firstName, String lastName) {
        this(0, firstName, lastName);
    }
    
    /**
     * This is the overloaded constructor for AuthorDTO.
     * 
     * @param authorID The AuthorID to load
     * @param firstName The first name to load
     * @param lastName The last name to load
     * 
     */
    public AuthorDTO(Integer authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /**
     * This method as a getter returns the authorID.
     * 
     * @return The authorID to get
     */
    public Integer getAuthorID() {
        return authorID;
    }
    
    /**
     * This method as a setter sets the authorID.
     * 
     * @param authorID The authorID to set
     */
    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }
    
    /**
     * This method as a getter returns the firstName.
     * 
     * @return The firstName to get
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * This method as a setter sets the firstName.
     * 
     * @param firstName The firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * This method as a getter returns the lastName.
     * 
     * @return The lastName to get
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * This method as a setter sets the lastName.
     * 
     * @param lastName The lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
} // End class AuthorDTO
