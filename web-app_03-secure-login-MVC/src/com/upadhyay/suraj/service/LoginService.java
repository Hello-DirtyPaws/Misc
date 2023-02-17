package com.upadhyay.suraj.service;

import java.util.HashMap;
import com.upadhyay.suraj.dto.User;

public class LoginService 
{
	HashMap<String, User> users = new HashMap<>();
	
	public LoginService()
	{
		users.put("john", new User("john", "John Doe"));
		users.put("suraj", new User("suraj", "Suraj Upadhyay"));
		users.put("ekon", new User("ekon", "Ekon Lamar"));
		users.put("christina", new User("christina", "Christina Repke"));
	}
	
	public boolean authenticate(String userId, String pass)
	{
		return (this.users.get(userId) != null);
	}
	
	public User getUserDetail(String userId)
	{
		return this.users.get(userId);
	}
}
