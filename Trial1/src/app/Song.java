package app;

public class Song 
{
	private String song;
	private String artist;
	private String album;
	private String year;
	
	
	public Song(String song, String year)
	{
		this.song = song;
		this.year = year;
		this.album = null;
		this.artist = null;
	}
	
	// Constructor to instantiate a song object
	public Song(String song, String artist, String year, String album) 
	{
		this.song = song;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}

	/**
	 * @return the song
	 */
	public String getSong() 
	{
		return song;
	}

	/**
	 * @param song the song to set
	 */
	public void setSong(String song) 
	{
		this.song = song;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() 
	{
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) 
	{
		this.artist = artist;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() 
	{
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) 
	{
		this.album = album;
	}

	/**
	 * @return the year
	 */
	public String getYear() 
	{
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) 
	{
		this.year = year;
	}
}
