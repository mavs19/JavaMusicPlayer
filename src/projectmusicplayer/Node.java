/*
 * David Perry
 * 30010019
 * 25 May 2020
 * Music Player application, stores songs on a doubly linked list, sort with a Merge sort
 * Using a Binary search, Third party library to save playlist to CSV, must have Help files
 */
package projectmusicplayer;

public class Node {

    // Previous and getNext instances used for Linked List
    // The String title and path are the song attributes
    public Node prev;
    public Node next;
    public String title;
    public String path;

    // Default constructor
    public Node() {
    }

    // Overloaded constructor accepting the song attributes
    public Node(String title, String path) {
        this.title = title;
        this.path = path;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Node getPrev() {
        return prev;
    }

    public Node getNext() {
        return next;
    }

    // Overriding the toString method to dispaly title only in list view
    @Override
    public String toString() {
        return title;
    }

}