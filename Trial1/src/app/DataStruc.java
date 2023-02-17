package app;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.JSONObject;

public class DataStruc
{
	public static Map songData;
	public static LinkedList<JSONObject> songs = new LinkedList<JSONObject>();
	public static int count;
	
	
	
	public static boolean writeToFile() throws FileNotFoundException
	{
		PrintWriter write = new PrintWriter("outFile.json");
		
		for (JSONObject jsonObject : songs) 
		{
			System.out.println("Json Data: " + jsonObject.toString());
			write.append(jsonObject.toString());
		}
		
		write.close();
		return true;
	}
	
	public static void add(Song song)
	{
		count++;
		JSONObject json = new JSONObject();
		songData = new LinkedHashMap(2);
		songData.put("Song", song.getSong());
		songData.put("Year", song.getYear());
		json.putAll(songData);
		songs.add(json);
	}
	
}
