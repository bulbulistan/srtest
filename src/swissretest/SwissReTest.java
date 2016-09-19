package swissretest;
import java.sql.*;

public class SwissReTest {

static final String DB_URL = "jdbc:postgresql://localhost/SwissRe";
static final String USER = "postgres";
static final String PASS = "admin";
public static Connection connection = null;

public static void connect(){      

  try{            
    System.out.println("Connecting to the database...");    
    connection = DriverManager.getConnection(DB_URL,USER,PASS);    
    }
    catch(SQLException se){
    se.printStackTrace();
    }
}

public static void update(String sql){
    connect();
    Statement update = null;    
    
    try{
    update = connection.createStatement();
    update.executeUpdate(sql);
    update.close();
    connection.close();
    }
    
    catch(SQLException se){
    se.printStackTrace();
    }
}

public static int runquery(String query){
    connect();
    int resultSize = 0;
    Statement statement = null;    
    ResultSet results = null;
    
    try{
    statement = connection.createStatement();
    results = statement.executeQuery(query);
    
    while(results.next())
    {
        resultSize = resultSize + 1;
    }
   
    connection.close();
    }
    
    catch(SQLException se){
    se.printStackTrace();
    }
    
    return resultSize;
}

public static void main(String[] args) {   

}
}

