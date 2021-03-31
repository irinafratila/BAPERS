package Break;

import BapersControl.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BreakController implements Initializable {

    private Main m;

    @FXML
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private Label feedback;


    public BreakController(){
        this.m = new Main();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{

            for(int i=0 ; i>= 5; ++i ){

            }
            String path = new File("/Users/tobiadewunmi/Desktop/BAPERS-main/BAPERS/src/media/play.MP3").getAbsolutePath();
            //String path = "/media/play.MP3";
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            //mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);
            feedback.setText("Playing Audio");
        }catch (Exception e){
            e.printStackTrace();
        }


    }




    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
        mediaPlayer.pause();
    }

    public void play() throws IOException {
        System.out.println("play");
        mediaPlayer.play();
        feedback.setText("Playing Audio");
       // m.changeScene("dashboard.fxml");
    }
    public void pause() throws IOException {
        mediaPlayer.pause();
        System.out.println("pause");
        feedback.setText("Paused Audio");
        // m.changeScene("dashboard.fxml");
    }



}
