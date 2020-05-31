/*
 * David Perry
 * 30010019
 * 25 May 2020
 * Music Player application, stores songs on a doubly linked list, sort with a Merge sort
 * Using a Binary search, Third party library to save playlist to CSV, must have Help files
 */
package projectmusicplayer;

public class User {
    
    // Fields for the User
    private String userName;
    private String salt;
    private String passwordHash;
    
    // Default constructor
    public User(){
        
    }
    
    // Overloaded constructor
    public User(String userName, String salt, String passwordHash){
        this.userName = userName;
        this.salt = salt;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
}
