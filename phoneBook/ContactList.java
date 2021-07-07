package phoneBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactList {
	
	final String CONTACT_NOT_FOUND="contact not found";
	
    public void add(String name,String phoneno,Connection con) throws SQLException{
        String stmt = "insert into phonebook values ('" + name + "','" + phoneno+"')";
        Statement st = con.createStatement();
        int re = st.executeUpdate(stmt);
        if (re>=1) {
			System.out.println("Contact Added Successfully .. ");
		}
		else {
			System.out.println("Error occured!!");
		}
    }
    
    public String search(String name,Connection con) throws SQLException{
    	Statement st = con.createStatement();
    	String stmt = "select * from phonebook";
    	ResultSet rs = st.executeQuery(stmt);
    	while(rs.next()) {
    		if(rs.getString(1).equals(name)) {
    			return rs.getString(2);
    		}
    	}
    	return CONTACT_NOT_FOUND;
    }
    
    public void update(String name,String phoneno,Connection con) throws SQLException{
    	Statement st = con.createStatement();
    	String stmt = "select * from phonebook";
    	int re = 0;
    	ResultSet rs = st.executeQuery(stmt);
    	if(!search(name,con).equals(CONTACT_NOT_FOUND)){
    		while(rs.next()) {
    			if (rs.getString(1).equals(name)) {
    				
    				PreparedStatement ps = con.prepareStatement("update phonebook set phoneNumber = ? where name = ?");
    				ps.setString(1, phoneno);
    				ps.setString(2, name);
    				re = ps.executeUpdate();
    				
    				break;
    			}
    		}
    		if (re>=1) {
    			System.out.println("Contact Updated successfully");
    		}
    		else {
    			System.out.println("Error occured");
    		}
    	}
                
    }
	
    public void delete(String name,Connection con) throws SQLException{
    	Statement st = con.createStatement();
    	String stmt = "select * from phonebook";
    	ResultSet rs = st.executeQuery(stmt);
    	
    	int re = 0;
    	
    	while(rs.next()) {
    		if(rs.getString(1).equals(name)) {
    			String smt = "delete from phonebook where name = '" + name +"'";
    			re = st.executeUpdate(smt);
    			break;
    		}
    	}
    	
    	if (re>=1) {
    		System.out.println("Contact Deleted Successfully .. ");
    	}
    	else {
    		System.out.println(CONTACT_NOT_FOUND);
    	}
    }
    
    public void displayall(Connection con) throws SQLException{
    	Statement st = con.createStatement();
    	String stmt = "select * from phonebook";
    	ResultSet rs = st.executeQuery(stmt);
        while(rs.next()) {
        	System.out.println("Contact =>  Name :" + rs.getString(1) + "\t\t PhoneNumber : "+rs.getString(2));
        }
    }
   
}

