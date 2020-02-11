package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_examples {
    //1.connection database
    String dbUrl = "jdbc:oracle:thin:@54.243.9.65:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //create connection to database, create object
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //2.create statement object
        Statement statement = connection.createStatement();
        //3.execute the query and get the result in result object
        ResultSet resultSet = statement.executeQuery("Select region_name from regions");

        while (resultSet.next()) {
            String regionName = resultSet.getString("region_name");
            System.out.println(regionName);
        }
        //using same object to run another query
        //================
        resultSet = statement.executeQuery("Select * from countries");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }


        //close all connection in reverse order
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void CountandNavigate() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many record(rows) i have for my query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println("rowCount: " + rowCount);

        //this print last row
        System.out.println(resultSet.getString("department_name"));

        //we need to move before the first row to get full list since we are at the last row right now
        resultSet.beforeFirst();
        System.out.println("---------While loop starts--------");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("department_name"));
        }


        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metadata() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");
        //get the databases related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println("User: "+ dbMetadata.getUserName());
        System.out.println("Database product name: "+ dbMetadata.getDatabaseProductName());
        System.out.println("Database product version: "+ dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver: "+ dbMetadata.getDriverName());
        System.out.println("Driver version: "+ dbMetadata.getDriverVersion());
        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();
        //how many column we have ?
        System.out.println("Column count:"+rsMetadata.getColumnCount());
        System.out.println("=================================");
        //column names
        //System.out.println(rsMetadata.getColumnName(8));

        //print all the column names dynamically
        int columnCount = rsMetadata.getColumnCount();
        for (int i = 1; i <=columnCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}


