/*
 * David Perry
 * 30010019
 * 25 May 2020
 * Music Player application, stores songs on a doubly linked list, sort with a Merge sort
 * Using a Binary search, Third party library to save playlist to CSV, must have Help files
 */
package projectmusicplayer;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;


public class MusicPlayerController  {

    DoublyLinkedList<String> playList = new DoublyLinkedList<>();
    private static final UserRepo REPO = new UserRepo();
    private static final DoublyLinkedList DLL = new DoublyLinkedList();
    private final String fileName = "playlist.csv";

    @FXML
    private ListView<Node> listBox;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnNext;
    
    @FXML
    private Button btnPrev;
    
    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnHelp;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnLoad;
    
    @FXML
    private Button btnCreateUser;
    
    @FXML
    private Button btnSignIn;
    
    @FXML
    private TextField textUserName;
    
    @FXML
    private TextField textPassword;
    
    @FXML
    private TextField textMessage;
    
     @FXML
    private TextField textSearch;

    private MediaPlayer mediaPlayer;
    private Media song;
    private Node currentSong;
    private File selectedFile;

    // Event hanlder for Play button, the media player will play the current song node 
    @FXML
    public void btnPlay(ActionEvent event) {

        try{
            mediaPlayer.stop();
            Node newsong = (Node) listBox.getSelectionModel().getSelectedItem();
            newsong.path = newsong.getPath();
            song = new Media(newsong.path);
            currentSong = newsong;
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.play();
            textMessage.setText("Now playing : " + currentSong);
        }catch (NullPointerException e){
            textMessage.setText("No track selected");
        }
    }

    // Event hanlder for Pause button, the media player will pause the current song node
    @FXML
    public void btnPause(ActionEvent event) {
        
        try{
            mediaPlayer.pause();
        }catch (NullPointerException e){
            textMessage.setText("No track selected");
        }
    
    // Event hanlder for Stop button, the media player will stop the current song node
    }
    @FXML
    public void btnStop(ActionEvent event) {
        try{
            mediaPlayer.stop();
            textMessage.clear();
        }catch (NullPointerException e){
            textMessage.setText("No track selected");
        }
    }
    
    // Event hanlder for Next button, the media player will song song next on the list to the current song
    // Stops current song from playing, calls method to find and return the next song
    // The next song to play will be highlighted in the list view
    // Plays song and writes a message 
    @FXML
    public void btnNext(ActionEvent event) {

        try{
            clearText();
            mediaPlayer.stop();
            Node newSong = DLL.playNext(currentSong);
            song = new Media(newSong.path);
            listBox.getSelectionModel().select(newSong);
            currentSong = newSong;
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.play();
            textMessage.setText("Now playing : " + currentSong);
        }catch (NullPointerException e){
            textMessage.setText("No track selected");
        }
        
    }

    // Event hanlder for Previous button, the media player will song song previous on the list to the current song
    // Stops current song from playing, calls method to find and return the previous song
    // The previous song to play will be highlighted in the list view
    // Plays song and writes a message 
    @FXML
    public void btnPrev(ActionEvent event) {

        try{
            clearText();
            mediaPlayer.stop();
            Node newSong = DLL.playPrev(currentSong);
            song = new Media(newSong.path);
            listBox.getSelectionModel().select(newSong);
            currentSong = newSong;
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.play();
            textMessage.setText("Now playing : " + currentSong);
        }catch (NullPointerException e){
            textMessage.setText("No track selected");
        }    

    // Event hanlder for Delete button, 
    // A new node is created from the selected item from the list box when list is clicked
    // The selected song is the second argument of delete method called, which returns a node of the updated playlist
    // Clears the listbox and calls dispaly metthod to update listbox
    }
    @FXML
    public void btnDelete(ActionEvent event) {

        try{
            Node selectedSong = (Node) listBox.getSelectionModel().getSelectedItem();
        playList.head = DLL.delete(playList.head, selectedSong.title);
        if (playList.head != null) {
            listBox.getItems().clear();
            display(playList.head);
        }
        else{
            textMessage.setText("Nothing to delete");
        }
        clearText();
        }catch (NullPointerException e){
            textMessage.setText("No track selected");
        }    
    }
    
    // Help page loads upon clicking the help button, 
    // 
    @FXML
    public void btnHelp(ActionEvent event) {

        mediaPlayer.stop();
    }

    // Event hanlder for Search button, String variable created from the text field input
    // If statement to excecute only if text is entered, binary Search method called with search input as the target and returns a node
    // if song was found it is selected on the list view, and added to the media player and played, current song variable updated
    // Messages to display various results
    @FXML
    public void btnSearch(ActionEvent event) {
        
        try{
            String target = textSearch.getText();
        if (target != null && !target.isEmpty()) {
            Node newsong = DLL.binarySearch(playList.head, target);
            if (newsong != null) {
                System.out.println("Found soung..." + newsong);
                listBox.getSelectionModel().select(newsong);
                song = new Media(newsong.path);
                currentSong = newsong;
                mediaPlayer = new MediaPlayer(song);
                mediaPlayer.play();
                textSearch.clear();
                textMessage.setText("Now playing : " + currentSong);
            }
            else{
                textMessage.setText("Song not found");
            }
        }
        else{
            textMessage.setText("Please enter a song to search..");
        }
        }catch (NullPointerException e){
            textMessage.setText("Error" + e);
        } 
    }

    // Event handler for the Save button,calls a method to save if the list has data
    @FXML
    public void btnSave(ActionEvent event) {
        
        clearText();
        try{
            if (playList.head != null) {
                saveData(playList.head);
                textMessage.setText("Playlist saved to : " + fileName);
            }
            else{
                textMessage.setText("Error, nothing to save");
            }
        }
        catch (NullPointerException e){
            textMessage.setText("Error");
        }
    }

    // Event handler for Add button, uses File Chooser, required extensions allocated with a filter
    // A list of class File is created to allow multiple files to be added at once
    // Open dialog opens explorer in a new window for selection, for each loop to add files to list
    // The Pattern and Matcher classes are used to amend spaces and forward/back slashes, .mp3
    // The string URL, which will save as one of the Node object attributes has teh correct path allocated
    // The title and url are added to the playlist with a method called
    // Each time files are added the list view is cleared, a new node is created to store returned data from the merge sort,
    // The sorted data is replaces the original playlist data befoe sorted list is dispalyed via display method
    // 
    @FXML
    public void btnAdd(ActionEvent event) {
        try{
           FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3"));
            List<File> fileList = fileChooser.showOpenMultipleDialog(listBox.getScene().getWindow());
            if (fileList != null) {
                for (File file : fileList) {
                    Pattern spacePattern = Pattern.compile(" ");
                    String url = ("file:///" + file.getAbsolutePath());
                    Matcher matcher = spacePattern.matcher(url);
                    url = matcher.replaceAll("%20");
                    url = url.replace("\\", "/");
                    String title = file.getName();
                    title = title.replace(".mp3", "");
                    playList.addLast(title, url);//
            }
        }
        clearText();
        listBox.getItems().clear();
        Node node = null;
        node = playList.mergeSort(playList.head);
        playList.head = node;
        display(playList.head); 
        }catch(Exception e){
            textMessage.setText("Error " + e);
        }
    }

    // Event handler for load button, calls method 
    @FXML
    public void btnLoad(ActionEvent event) {
        
        try{
            clearText();
            loadData();
        } catch (NullPointerException e){
            textMessage.setText("Error " + e);
        }
    }
    
    // Event handler for Create User Button, text inputs are stored as string variables and sent in a method
    // Messag eto display result, clears text fields
    @FXML
    public void btnCreateUser(ActionEvent event) {

        if (textUserName.getText().isEmpty()) {
            textMessage.setText("Text field empty");
        }
        else if (textPassword.getText().isEmpty()) {
            textMessage.setText("Text field empty");
        }else{
            String userName = textUserName.getText();
            String passWord = textPassword.getText();
            createUser(userName,passWord);
            textMessage.setText("User : " + userName + " created");
            textUserName.clear();
            textPassword.clear(); 
        }
    }
    
    // Event handler for sign in button, text inputs are stored as string variables and sent in a method
    // A switch block will display a result depending on the result from the method's returned String
    @FXML
    public void btnSignIn(ActionEvent event) {
        
        if (textUserName.getText().isEmpty()) {
            textMessage.setText("Text field empty");
        }
        else if (textPassword.getText().isEmpty()) {
            textMessage.setText("Text field empty");
        }else{
            String userNameCheck = textUserName.getText();
            String passwordCheck = textPassword.getText();
            String result = clientLogin(userNameCheck, passwordCheck);
            switch (result) {
                case "success":
                    textMessage.setText("Sign in succeful, Hello " + userNameCheck);
                    break;
                case "passwordFail":
                    textMessage.setText("Incorrect password, try again");
                    break;
                case "usernameFail":
                    textMessage.setText("Incorrect username, try again");
                    break;
                default:
                    break;
            }
            textUserName.clear();
            textPassword.clear();
        }
    }

    // Event handler for clicking a song on the list view
    // An instance of the node class retrives the data from list item
    // The path is retrieved of the slected song with the string being addeed to the media instance
    // The current song variable is updated and song is played
    @FXML
    public void handleClickListView() throws InterruptedException {
        
        try{
            Node newsong = (Node) listBox.getSelectionModel().getSelectedItem();
            newsong.path = newsong.getPath();
            song = new Media(newsong.path);
            currentSong = newsong;
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.stop();
            mediaPlayer.play();
            textMessage.setText("Now playing : " + currentSong);
        }
        catch(Exception e){
            textMessage.setText("Error " + e);
        }
    }

    // Method to display the song titles in the list view
    // The argument of type node is used to loop through the list until the end (null)
    // The variable temp holds the data to be added, during each iteration temp is moved to the next item on list 
    public void display(Node n) {
        Node temp = n;
        while (temp != null) {
            listBox.getItems().add(temp);
            temp = temp.next;
        }
    }

    // Method to save the play list to a csv file using third party library OpenCSV
    // An instance of Open CSV Writer class is declared with the allocated file name using File Writer
    // While loop iterates until the end of the list (when null is reached), String array with both attributes added in each loop
    // WriteNext method adds the string array with a comma seperating data. Each iteration will save data toa new line
    public void saveData(Node node) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName));
            Node temp = node;
            while (node != null) {
                System.out.print(node.title + " ");
                temp = node;
                String[] data = {node.title,node.path};
                writer.writeNext(data);
                node = node.next;
            }
            textMessage.setText("Playlist successfully saved as : " + fileName);
            writer.close();
        } catch (IOException ex) {
            textMessage.setText("Could not save file");
        }
    }
    
    // Method to load data using Open CSV, an instance of CSV Reader is declared with the allocated filename using File Reader
    //  A list of type String array used to store the read data using built in iterator, playlist is made null before data added
    // While loop iterates until no more data to read, String array created to store both attributes into the node instance
    // The attributes data is added the the last of the list to avoid re sorting, list is added to the list view
    public void loadData() {
        listBox.getItems().clear();
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
            List<String[]> records = reader.readAll();
            Iterator<String[]> iter = records.iterator();
            Node node = new Node();
            playList.head = null;
            while (iter.hasNext()) {
                String[] record = iter.next();
                node.setTitle(record[0]);
                node.setPath(record[1]);
                playList.addLast(node.getTitle(),node.getPath());
            }
            reader.close();
            display(playList.head);
        } catch (FileNotFoundException ex) {
            textMessage.setText("Unable to load file, cannot find : " + fileName);
            //Logger.getLogger(MusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Method to create a user with name and password received from user input
    // The Salt and Hash are created from their respective methods in the Utility Class
    // A new user is created as a User object
    // The user is added to a list in the User Repo class
    // The name, salt and hash are printed
    public void createUser(String u, String p) {

        String userName = u;
        String password = p;
        String salt = Utility.generateSalt(5).get();
        String passwordHash = Utility.hashPassword(password, salt).get();
        User user = new User(userName, salt, passwordHash);
        REPO.addUser(user);
        System.out.println("User = " + user.getUserName());
        System.out.println("password Hash = " + user.getPasswordHash());
        System.out.println("Salt = " + user.getSalt());
    }
    
    // Method for the client to login, receives the request in the parameters
    // The user Object is retrived using the username input
    // Verify Password method called from Utility as a boolean result with the password input
    // being compared to the retrived Object's salt and hash in the methods parameters
    // Each result will return a unique String while displaying to the server the outcome
    public String clientLogin(String userName, String password) {

        User user = REPO.GetUser(userName);
        if (user != null) {
            boolean result = Utility.verifyPassword(password, user.getPasswordHash(), user.getSalt());
            if (result) {
                System.out.println(userName + " has logged in.");
                return "success";
            } else {
                System.out.println("Login attempt failed by " + userName + ": Incorrect password");
                return "passwordFail";
            }
        } else {
            System.out.println("Login attempt failed by " + userName + ": Incorrect username");
            return "usernameFail";
        }
    }

    // Method to clear all the text fields
    public void clearText(){
        textSearch.clear();
        textMessage.clear();
        textUserName.clear();
        textPassword.clear();
    }
    
    // Initialize method when GUI is loaded
    public void initialize() {
        
    }

}