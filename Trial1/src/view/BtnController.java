package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage; 

public class BtnController 
{
	@FXML Button btn;
	
	public void add(ActionEvent e) throws IOException
	{
		Stage mainStage = (Stage)((Button)e.getSource()).getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/add.fxml"));
		Pane root = (Pane)loader.load();
		
		Scene newScene = new Scene(root);
		mainStage.setScene(newScene);
		
		//mainStage.show();
	}
}
