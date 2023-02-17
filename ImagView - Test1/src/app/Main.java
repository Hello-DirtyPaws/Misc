package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		System.out.println("hey there!");
		Image imgUsa = new Image ("/photo/Soft Skills.png");
		//Image imgChina = new Image ("http://bestanimations.com/Flags/Asia/china/chinese-flag-waving-gif-animation-10.gif");

		ImageView ivUsa = new ImageView(imgUsa);
		//ImageView ivChina = new ImageView(imgChina);


		TextField errorText = new TextField();
		/*
		if (imgChina.isError()) {
			errorText.setText(imgChina.getException().getMessage());
		}
*/
		VBox root = new VBox(ivUsa, errorText);

		Scene scene = new Scene(root, 1000, 500);
		primaryStage.setTitle("Flags");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		System.out.println("hello");
		Application.launch(args);
	}
}