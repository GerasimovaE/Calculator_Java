package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private DriverManager DbConnectionEvent;

    //Gerasimova_START
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static Connection connection;
    //Gerasimova_FIN

    public static Connection DBConnection() {

        try {
            //Gerasimova_START
            Class.forName("com.mysql.cj.jdbc.Driver");
           // return DriverManager.getConnection
           //         ("jdbc:mysql://localhost:3306/todo_manager", "root", "root");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);


            if (!connection.isClosed()){
                System.out.println("Соединение с базой данных установлено!");
            }

            return connection;


            //if (connection.isClosed()){
              //  System.out.println("Соединение с базой данных закрыто!");
           // }

            //Gerasimova_FIN
        } catch (Exception e) {
            System.out.println("У нас проблемы с подключением к базе данных");
        }

        return null;

    }
}
