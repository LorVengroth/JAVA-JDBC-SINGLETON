package co.istad.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    // Singleton pattern


    // use static to create connection object
    private static Connection conn ;


    public static Connection getInstance() {

        return conn;

    }


    // Initialize connection object
    public static void init(){


        if(conn == null){

            // Start JDBC foundation
            // Step 1. Load driver

            try{

                Class.forName("org.postgresql.Driver");

            }catch (ClassNotFoundException e){

                System.out.println("Driver load failed : " + e.getMessage());

            }


            // step 2. Define connection URL

            /*

            Thing to know :

            1. hostname
            2. port number
            3. database_name

             */


            final String URL = "jdbc:postgresql://localhost:5432/postgres";
            final String USER = "postgres";
            final String PASSWORD = "369369369";


            // step 3. Establish Connection

            try {

                conn = DriverManager.getConnection(URL , USER , PASSWORD);

            }catch ( SQLException e ){

                System.out.println("Error SQL : " + e.getMessage());

            }

        }else{

            System.out.println("Connection already initialize");

        }


    }

}
