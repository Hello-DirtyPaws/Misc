package webapp;

public class UserValidation 
{
	public static boolean isValidUser(String username, String password)
	{
		if(username.compareToIgnoreCase("suraj")==0 && password.compareToIgnoreCase("pass")==0)
			return true;
		return false;
	}
}
