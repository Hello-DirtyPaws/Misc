package com.upadhyay.suraj.dto;

public class User 
{	
	private String userId;
	private String username;
	
	/**
	 * 
	 * @param userId
	 */
	public User(String userId)
	{
		this.userId = userId;
		this.username = userId;
	}
	
	/**
	 * 
	 * @param userId
	 * @param username
	 */
	public User(String userId, String username)
	{
		this.userId = userId;
		this.username = username;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUsername()
	{
		return this.username;
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		return ("UserId: " + userId + ", Username: " + username);
	}
}

