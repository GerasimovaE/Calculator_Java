package todo_manager2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;



public class Panel {

    private static Panel panel;
    private static String result = new String("Task List:\n");
    private JPanel panelMain;
    private JButton save;
    private JButton delete;
    private JButton set;

    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextArea field5;

    JScrollPane scrollPane;
    JComboBox<Event> dropDownList;

    //ПРОЧИТАТЬ ПРО ПАТТЕРН Singleton
    //Этот паттерн гарантирует, что у класса есть только один объект. А панель у нас может быть только одна
    //Это необходима для вызова обновления панели после изменения БД  Panel.getPanel().updatePanel(); в DBInteraction т.к. метод updatePanel вызывается только у экземпляра класса
    private Panel(){}

    public static Panel getPanel(){
        if(panel == null){		//если объект еще не создан
            panel = new Panel();	//создать новый объект
            result = DBInteraction.getEvents();
        }
        return panel;		// вернуть ранее созданный объект
    }

    public void updatePanel(){

        result = DBInteraction.getEvents();
        field5.setText(result);

    }

    void showPanel() {

        panelMain = new JPanel();
        save = new JButton("SAVE");
        delete = new JButton("DELETE");
        set = new JButton("SET");

        field1 = new JTextField(10);

        field2 = new JTextField(10);
        field3 = new JTextField(10);
        field4 = new JTextField(10);
        field5 = new JTextArea(result);

        scrollPane = new JScrollPane(field5);
        scrollPane.setBounds(10,60,780,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        dropDownList = new JComboBox();
        dropDownList.setModel(new DefaultComboBoxModel<>(Event.values()));
        dropDownList.getSelectedObjects();
        dropDownList.setEditable(true);

        String[] taskStatus = {"DONE", "NOT_DONE", "PROGRESS"};
        JComboBox taskStatusBox = new JComboBox(taskStatus);
        taskStatusBox.getSelectedObjects();


        GridLayout layout = new GridLayout(6, 7); // appearance of the panel
        panelMain.setLayout(layout);

        panelMain.add(new JLabel("Create task: "));
        panelMain.add(field1);
        panelMain.add(dropDownList);
        panelMain.add(new JLabel("Set Date (format MM/dd/yyyy HH:mm): "));
        panelMain.add(field2);
        panelMain.add(save);
        panelMain.add(new JLabel("To Delete insert task id: "));
        panelMain.add(field3);
        panelMain.add(delete);
        panelMain.add(new JLabel("To set task status insert task id: "));
        panelMain.add(field4);
        panelMain.add(taskStatusBox);
        panelMain.add(new JLabel(" "));
        panelMain.add(new JLabel(" "));
        panelMain.add(set);
        panelMain.add(scrollPane);

        ActionListener actionListenerSave = e -> {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "MM/dd/yyyy HH:mm" );
            LocalDateTime formattedDateInput = null;

            try {
                formattedDateInput = LocalDateTime.parse(field2.getText(), formatter);

                String date = String.format("%d/%d/%d %d:%d", formattedDateInput.getDayOfMonth(), formattedDateInput.getMonthValue(), formattedDateInput.getYear(), formattedDateInput.getHour(), formattedDateInput.getMinute());
                DBInteraction.SaveDB(field1.getText(), field2.getText(), Arrays.toString(dropDownList.getSelectedObjects()));
            }catch (Exception exc){
                System.out.println("Вы ввели неверный формат даты");
            }

        };

        ActionListener actionListenerDelete = e -> {

            int taskId = 0;

            try{
                taskId = Integer.parseInt(field3.getText());
                DBInteraction.DeleteDB(taskId);
            }catch (Exception exc){
                System.out.println("Вы выбрали неверный объект");
            }
        };


        ActionListener actionListenerSet = e -> {

            int taskId = 0;

            try{
                taskId = Integer.parseInt(field4.getText());
                DBInteraction.SetDB(taskStatusBox.getSelectedObjects(), taskId);
            }catch (Exception exc){
                System.out.println("Вы выбрали неверный объект");
            }

        };

        save.addActionListener(actionListenerSave);
        delete.addActionListener(actionListenerDelete);
        set.addActionListener(actionListenerSet);


        JFrame frame = new JFrame("TODO MANAGER");
        //frame.pack();    // what this is doing ? what is included in the pack ?
        frame.setContentPane(panelMain);
        frame.setSize(1100, 600);
        frame.setVisible(true);

    }

    }

