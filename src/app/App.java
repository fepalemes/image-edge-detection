package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
	
     public static void main (String args[]) {
        launch(args);
    }
	
	 public void start(Stage palco) throws Exception {
	        new ImgGui(palco);
	    }
    
   
}