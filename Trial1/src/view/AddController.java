package view;

import java.io.FileNotFoundException;

import app.DataStruc;
import app.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddController 
{
	@FXML TextField sName, sYear;
	@FXML Button btn;
	@FXML Label sNameLbl, sYearLbl;
	
	@FXML
	public void add(ActionEvent e) throws FileNotFoundException
	{
		String songName = sName.getText().trim();
		String songYear = sYear.getText().trim();
		if(songName.length() > 0 && songYear.length() > 0)
		{
			Song song = new Song(songName, songYear);
			DataStruc.add(song);
			
			System.out.println("Song: " + songName + "\nYear: " + songYear);
			DataStruc.writeToFile();
		}
	}
}
