package todo_manager2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DBInteraction {

    private static Connection getConnectDB(){
        Connection connection = null;

        try {
            connection = DBConnection.DBConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

    public static String getEvents(){

        Connection connection = getConnectDB();
        String result = "";

        if (connection!=null){
            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM events");
                StringBuilder stringBuffer = new StringBuilder();

                while (resultSet.next()){
                    stringBuffer.append(resultSet.getString(1));
                    stringBuffer.append(": ");
                    stringBuffer.append(resultSet.getString(3));
                    stringBuffer.append(" - ");
                    stringBuffer.append(resultSet.getString(4));
                    stringBuffer.append(" - ");
                    if (resultSet.getString(5) != null) {
                        stringBuffer.append(resultSet.getString(5));
                        stringBuffer.append(", ");
                    }
                    stringBuffer.append(resultSet.getDate(2));
                    stringBuffer.append("; " + "\n");
                }
                result = stringBuffer.toString();
                connection.close();
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }

        return result;

    }

    public static void SaveDB(String text1, String date, String eventDropListInto){
        Connection connection = getConnectDB();

        if (connection != null) {
            try {
                String eventNext = text1;
                String eventDropList = eventDropListInto;

                Statement statement = connection.createStatement();
                String sql = "INSERT into events (description, event, event_date) values ('" + eventNext + "', '" + eventDropList + "' , STR_TO_DATE('" + date + "', '%d/%m/%Y %H:%i'))";
                statement.execute(sql);
                System.out.println("Event has been added to the list");
                connection.close();
            } catch (Exception exc) {
                System.out.println("Event was not added to DB");
                exc.printStackTrace();
            }
        }

        Panel.getPanel().updatePanel();

    }

    public static void DeleteDB(int taskId){

        Connection connection = getConnectDB();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String sql = "DELETE from events WHERE (event_id) = (' " + taskId + "')";

                statement.execute(sql);
                System.out.println("Task was removed from the list");
                connection.close();


            } catch (Exception exc) {
                System.out.println("Error. Task was NOT removed from the list ");
                exc.printStackTrace();
            }
        }

        Panel.getPanel().updatePanel();
    }

    public static void SetDB(Object[] Status, int taskId){

        Connection connection = getConnectDB();

        if (connection != null) {
            try {
                Object[] setStatus =  Status;
                Statement statement = connection.createStatement();
                String sql = "UPDATE events SET status = ' " + Arrays.toString(setStatus) + "' WHERE event_id = '" + taskId + "'" ;
                statement.executeUpdate(sql);
                System.out.println("Task status was changed to: " + Arrays.toString(setStatus));
                connection.close();
            } catch (Exception exc) {
                System.out.println("Error. Task status was NOT changed");
                exc.printStackTrace();
            }
        }

        Panel.getPanel().updatePanel();
    }
}
