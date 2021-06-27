package todo_manager;

import database.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static String result = "";

    public static void main(String[] args) {

        Connection connection = DBConnection.DBConnection();

        if (connection!=null){
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM mydbtest.events;");
                StringBuffer stringBuffer = new StringBuffer();

                while (resultSet.next()){

                    stringBuffer.append(resultSet.getString(2));
                    stringBuffer.append(" ");
                    stringBuffer.append(resultSet.getDate(3));
                    stringBuffer.append("\n");
                }

                result = stringBuffer.toString();
                connection.close();
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }

        Panel panel = new Panel(result);
        panel.showPanel();



    }
}
