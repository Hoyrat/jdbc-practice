package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[]args) throws SQLException {

       //1.connection database
        String dbUrl = "jdbc:oracle:thin:@54.243.9.65:1521:xe";
        String dbUsername="hr";
        String dbPassword="hr";
        //create connection to database, create object
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //2.create statement object
        Statement statement=connection.createStatement();

        //3.execute the query and get the result in result object
        ResultSet resultSet=statement.executeQuery("Select * from employees");

        //move pointer to next row which is first
       // resultSet.next();
       // System.out.println(resultSet.getString("region_name"));
        //second row
        //resultSet.next();
        //System.out.println(resultSet.getString("region_name"));

        // loop
        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+"-"+ //coulmnnum  2. sütun, columnname de olur
                    resultSet.getString(3)+"-"+ //columnname last_name de kullanabilirdik
                    resultSet.getInt("salary")) ;
        }
        //close all connection in reverse order
        resultSet.close();
        statement.close();
        connection.close();
 }



}
