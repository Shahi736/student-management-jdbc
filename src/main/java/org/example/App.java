package org.example;
import java.sql.*;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {



            String url = "jdbc:postgresql://localhost:5432/college_db";
            String user= "postgres";
            String password = "4321";
        Scanner sc=new Scanner(System.in);
            try{
            Connection con= DriverManager.getConnection(url,user,password);
            while(true)
            {
                System.out.println("\n Add Student");
                System.out.println("\n View Student");
                System.out.println("\n Delete Student");
                System.out.println("\n Exit");
                int choice= sc.nextInt();
                Statement stmt = con.createStatement();
                switch (choice)
                {
                    case 1:
                        System.out.println("Enter ID:");
                        int id=sc.nextInt();
                        System.out.println("Enter Name:");
                        String name= sc.next();
                        stmt.executeUpdate("INSERT INTO student VALUES (" + id + ", '" + name + "')");
                        System.out.println("Student Added!");
                        break;
                    case 2:

                        ResultSet rs = stmt.executeQuery("SELECT * FROM student");

                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + " " + rs.getString(2));
                        }
                        break;
                    case 3:
                        System.out.print("Enter ID to delete: ");
                        int delId = sc.nextInt();

                        stmt.executeUpdate("DELETE FROM student WHERE id=" + delId);
                        System.out.println("Student Deleted!");
                        break;
                    case 4:

                        System.out.println("Exiting...");
                        con.close();
                        return;

                    default:
                        System.out.println("Invalid choice");




                }

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
