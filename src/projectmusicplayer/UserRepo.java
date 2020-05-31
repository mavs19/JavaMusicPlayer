/*
 * David Perry
 * 30010019
 * 25 May 2020
 * Music Player application, stores songs on a doubly linked list, sort with a Merge sort
 * Using a Binary search, Third party library to save playlist to CSV, must have Help files
 */
package projectmusicplayer;

import java.util.ArrayList;

// Class to store the users in a list
public class UserRepo {

    // Array List to store the users once created
    ArrayList<User> users = new ArrayList<User>();

    // Method to add the user to the Array List 
    public void addUser(User user) {

        users.add(user);
    }

    // Method to return the list for displaying 
    public ArrayList<User> displayUsers() {

        users.toArray();
        return users;
    }

    // Method to receieve a username and return the details of the Object
    // Uses a for each loop to find by User name, 
    // This method is used in the verifying password method 
    public User GetUser(String userName) {
        User result = null;
        for (User u : users) {
            if (userName.equals(u.getUserName())) {
                result = u;
            }
        }
        return result;
    }
}