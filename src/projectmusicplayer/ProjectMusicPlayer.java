/*
 * David Perry
 * 30010019
 * 25 May 2020
 * Music Player application, stores songs on a doubly linked list, sort with a Merge sort
 * Using a Binary search, Third party library to save playlist to CSV, must have Help files
 */
package projectmusicplayer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProjectMusicPlayer extends Application{

    // Overriding the start method to load the GUI, the primary stage is set with the fxml page being called
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("musicPlayer.fxml"));
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(new Scene(root, 750, 520));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }  
    
}
