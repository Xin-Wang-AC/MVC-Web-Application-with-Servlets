package dataaccesslayer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This enum serves as a singleton encapsulating the connection to database by using Properties class.
 * It contains two(2) methods including getting connection to database once either not created yet or already closed,
 * plus opening database.properties file by using Properties class to return the string array containing connection information.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.io.IOException
 * @see java.io.InputStream
 * @see java.sql.Connection
 * @see java.sql.DriverManager
 * @see java.sql.SQLException
 * @see java.util.Properties
 * @see java.util.logging.Level
 * @see java.util.logging.Logger
 * @since JDK 21 (Default)
 */
public enum DataSourceSingleton {
    
    /** 
     * It serves as the singleton instance.
     * 
     * It ensures there is only one connection.
     */
    DATA_SOURCE;
    
    private static Connection connection = null;
    
    private DataSourceSingleton() {
          try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    /**
     * This static method returns the connection to database once either not created yet or already closed.
     * 
     * @return The Connection based on the database.properties file information
     */
    public static synchronized Connection getConnection() {
        
        String[] connectionInfo = openPropsFile();
        
        try {
            
            // check whether the connection has not been created yet or already closed
            if (connection == null || connection.isClosed()) {
                
                connection = DriverManager.getConnection(connectionInfo[0], connectionInfo[1], connectionInfo[2]);
                
                
            } else {
                
                // print an appropriate message if the connection has been created
                System.out.println("Cannot create new connection, using existing one instead.");
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            
        }
        
        return connection;
        
    } // end method getConnection
    
    /**
     * This static method opens database.properties file by using Properties class 
     * to return the string array recording connecting information.
     * 
     * @return A string array containing connection information
     */
    private static String[] openPropsFile() {
        
        // use Properties class
        Properties props = new Properties();
        
        // use try-with-resources to load database.properties
        try (InputStream in = DataSourceSingleton.class.getClassLoader().getResourceAsStream("database.properties")) {
            
            props.load(in);
            
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }// end try and catch

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        
        // use the string array to record connecting information
        String[] info = new String[3];
        info[0] = url;
        info[1] = username;
        info[2] = password;

        return info;
    } // end method openPropsFile
    
} // end enum DataSourceSingleton
