import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.*;



public class timeout extends VBox {
	private Timeline time;
	public timeout(Stage primaryStage) {
		//sets a timeout timer that will send the user to the login screen and notify them that they will need to login due to inactivity.
		time = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert exit = new Alert(Alert.AlertType.INFORMATION);
                exit.setHeaderText("You were inactive for (10) seconds, returning to loginpage");
                exit.setTitle("Please relogin");
                exit.show();

                	primaryStage.close();
                    openLoginPage();
                    
                    
                    time.stop();
                
            }
        	}));
		
			time.setCycleCount(Timeline.INDEFINITE);
	        time.play();
	
	        primaryStage.addEventFilter(KeyEvent.ANY, new EventHandler<Event>() {
	            @Override
	            public void handle(Event event) {
	                time.playFromStart();
	            }
	        });
	        primaryStage.addEventFilter(MouseEvent.ANY, new EventHandler<Event>() {
	            @Override
	            public void handle(Event event) {
	                time.playFromStart();
	            }
	        });
	}



	private void openLoginPage() {
	    Stage loginStage = new Stage();
	    LoginPage loginPage = new LoginPage(loginStage);
	
	    Scene loginScene = new Scene(loginPage, 400, 200);
	    loginStage.setScene(loginScene);
	
	    loginStage.show();
	}
}
