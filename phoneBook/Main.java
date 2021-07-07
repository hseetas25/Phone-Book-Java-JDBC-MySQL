package phoneBook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
		System.out.println("Connection is successfully Established");
		
		ContactList Book = new ContactList();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println(" 1. Add Contact \n 2. Display Contacts \n 3. Update Contact \n 4. Delete Contact \n 5. Search Contact");
			System.out.print("Enter your choice: ");
			switch(sc.nextInt()) {
			
				case 1 : System.out.print("Enter Name : ");
						 String name = sc.next();
						 System.out.print("Enter Phone Number : ");
						 String phno = sc.next();
						 Book.add(name, phno,con);
						 break;
						 
				case 2 : Book.displayall(con);
						 break;
						 
				case 3 : System.out.print("Enter Name : ");
				 		 String name1 = sc.next();
				 		 System.out.print("Enter Phone Number : ");
				 		 String phno1 = sc.next();
				 		 Book.update(name1, phno1,con);
				 		 break;
				 		 
				case 4 : System.out.print("Enter Name : ");
		 		 		 String name2 = sc.next();
		 		 		 Book.delete(name2,con);
		 		 		 break;
		 		 		 
				case 5 : System.out.print("Enter Name: ");
						 String name3 = sc.next();
						 System.out.println(Book.search(name3,con));
						 break;
						 
				default : System.out.println("Invaild !!");

			}
			
			System.out.println("Enter -1 to exit");
			if (sc.nextInt() == -1){
				break;
			}
		}
	}
}
