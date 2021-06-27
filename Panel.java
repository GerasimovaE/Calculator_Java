package todo_manager;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class Panel extends JFrame {
    private String result = new String("Список событий: ");

    public Panel(java.lang.String result) {
        this.result = this.result + result;
    }

    void showPanel() {
            //Добавляем событие
            JPanel panel = new JPanel();
           // JButton next = new JButton("NEXT");
            JButton save = new JButton("SAVE");

            JLabel label1 = new JLabel("Create event: ");
            JLabel label2 = new JLabel("Set Date: ");
            JLabel label3 = new JLabel(result);

            JTextField field2 = new JTextField(10);
            JTextField field1 = new JTextField(10);

            JComboBox<Event> dropDownList = new JComboBox();
            dropDownList.setModel(new DefaultComboBoxModel<>(Event.values()));
            dropDownList.setEditable(true);


            GridLayout layout = new GridLayout(3, 3);
           // layout.  = 10;
           // layout.marginRight = 5;
            panel.setLayout(layout);
            panel.add(label1);
            panel.add(field1);
            panel.add(dropDownList);

            panel.add(label2);
            panel.add(field2);
            panel.add(save);

            panel.add(label3);


           // field1.addActionListener(next.getAction()); //  to DB


            //drop down list with elements from enum

            // get the value of the ComboBox on click
           // dropDownList.getSelectedItem();  //  to DB

            // String selectedOption = (String) dropDownList.getSelectedItem(); // not working - Event ENUM cant be cast to String

            // buttons
            //before lambda was like that
            //ActionListener actionListener = new ActionListener(){
           // ActionListener actionListener1 = e -> {
               // JPanel nextWindowContent = new JPanel();


             //   field2.addActionListener(next.getAction()); //  to DB
             //   nextWindowContent.add(nextLabel);
             //   nextWindowContent.add(field2);
             //   nextWindowContent.add(save);
             //   nextFrame.setContentPane(nextWindowContent);
              //  nextFrame.setSize(500, 300);
              //  nextFrame.setVisible(true);
           // };

            ActionListener actionListener2 = new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    Connection connection = DBConnection.DBConnection();

                    if (connection!=null){
                        try{
                            Statement statement = connection.createStatement();

                            String eventNext = field1.getText();
                            eventNext = eventNext;

                            int dateNext = Integer.parseInt(field2.getText());
                            String query = "insert into events (name, date) values ('" + eventNext + "', DATE_ADD(CURRENT_DATE(), INTERVAL " +dateNext+" DAY))";

                            boolean res = statement.execute(query);

                            //if (res==true){
                              //  System.out.println("Событие внесено в расписание)");
                            //}else {
                            //    System.out.println("Событие не удалось внести в расписание!!!");
                            //}

                            connection.close();
                        }catch (Exception exc){
                            exc.printStackTrace();
                        }
                    }
                }
            };

            save.addActionListener(actionListener2);

            // panel look
            JFrame frame = new JFrame("TODO MANAGER");
            //frame.pack();    // what this is doing ? what is included in the pack ?
            frame.setContentPane(panel);
            frame.setSize(500, 500);
            frame.setVisible(true);

        }

    }
/*
    List<EventAdd> eventList () throws SQLException, ClassNotFoundException {


        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from events");
        List<EventAdd> events = new ArrayList<EventAdd>();
        while (resultSet.next()){

            Integer eventId = resultSet.getInt("id");
            String date = resultSet.getString("date");
            String nameOfEvent = resultSet.getString("nameOfEvent");
            String description = resultSet.getString("description");
            EventAdd event = new EventAdd (eventId, date, nameOfEvent, description);
            events.add(event);
        }
        return events;
    }

    void insertEvent (EventAdd event) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO events (date, nameOfEvent, description)";
        PreparedStatement preparedStatement = DbConnectionEvent.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, event.getDate());
        preparedStatement.setString(3, event.getNameOfEvent());
        preparedStatement.setString(4, event.getDescription());

        preparedStatement.executeUpdate();
    }


*/
