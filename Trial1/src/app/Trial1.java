package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Trial1 extends Application
{

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage mainStage) throws Exception 
	{
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("/view/btn.fxml"));
		 AnchorPane root = (AnchorPane)loader.load();
		 Scene scene = new Scene(root);
		 mainStage.setScene(scene);
		 mainStage.setResizable(false);
		 mainStage.show();
	}

}