import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main 
{

	public static void main(String[] args) 
	{
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernatedb", "root", "password");
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in connecting!!");
			e.printStackTrace();
		}
		
		try {
			Statement st = conn.createStatement();
			st.execute("create table table1(id int, name varchar(20), primary key(id) );");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
