import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class guiTesting extends JFrame {
    JPanel guiPanel;
    JPanel doneButton;
    JPanel guiPanel2;
    JPanel guiPanel3;
    ArrayList<String> heap = new ArrayList<>();
    ArrayList<String> heap2 = new ArrayList<>();
    boolean pressed = false;
    guiTesting() {
        JCheckBox zine=new JCheckBox("Zine");
        JCheckBox full_Bundle=new JCheckBox("Full Bundle");
        JCheckBox flat_Bundle=new JCheckBox("Flat Bundle");
        JCheckBox scarf=new JCheckBox("Scarf");

        guiPanel = new JPanel();
        guiPanel.add(new JLabel("Select what bundles the Zine does NOT have."));
        guiPanel.add(full_Bundle);
        guiPanel.add(flat_Bundle);
        guiPanel.add(zine);
        guiPanel.add(scarf);
        guiPanel.setLayout( new BoxLayout(guiPanel,BoxLayout.Y_AXIS ));
        guiPanel.setSize(200,200);
        doneButton = new JPanel();

        JCheckBox collab = new JCheckBox("Collab");
        JCheckBox earlyBird = new JCheckBox("EB");
        guiPanel2=new JPanel();
        guiPanel2.add(new JLabel("Select what extras the Zine did NOT include."));

        guiPanel2.add(collab);
        guiPanel2.add(earlyBird);
        guiPanel2.add(new JLabel("If EB(early bird) is selected please select the date and time right after it ends."));
        guiPanel2.setLayout( new BoxLayout(guiPanel2,BoxLayout.Y_AXIS ));


        String yearString[]= new String[102];
        yearString[0] = "Year";
        for (int i = 2000; i <= 2100; i++) {
                yearString[i-1999] = ""+i;
        }
        guiPanel3 = new JPanel();
        String monthString[]= new String[13];
        monthString[0] = "Month";
        for (int i = 1; i <= 12; i++) {
            if(i<10){
                monthString[i]= "0"+i;
            }else{
                monthString[i] = ""+i;
            }
        }
        String dayString[]= new String[32];
        dayString[0] = "Day";
        for (int i = 1; i <= 31; i++) {
            if(i<10){
                dayString[i]= "0"+i;
            }else{
                dayString[i] = ""+i;
            }
        }
        String hourString[]= new String[13];
        hourString[0] = "Hour";
        for (int i = 1; i <= 12; i++) {
            if(i<10){
                hourString[i]= "0"+i;
            }else{
                hourString[i] = ""+i;
            }
        }
        String minString[]= new String[61];
        minString[0] = "Min";
        for (int i = 0; i <= 59; i++) {
            if(i<10){
                minString[i+1]= "0"+i;
            }else{
                minString[i] = ""+i;
            }
        }
        String amString[]= new String[2];

        amString[0] = "AM";
        amString[1] = "PM";

        JComboBox year = new JComboBox(yearString);
        JComboBox month = new JComboBox(monthString);
        JComboBox day = new JComboBox(dayString);
        JComboBox hour = new JComboBox(hourString);
        JComboBox min = new JComboBox(minString);
        JComboBox am = new JComboBox(amString);

        guiPanel3.add(year);
        guiPanel3.add(month);
        guiPanel3.add(day);
        guiPanel3.add(hour);
        guiPanel3.add(min);
        guiPanel3.add(am);

        JButton done = new JButton("Done");
        JLabel reminder = new JLabel("Check Files to find the result.");
        doneButton.setSize(10,10);
        doneButton.add(done);
        doneButton.add(reminder);
        reminder.setVisible(false);

        this.add(guiPanel);
        this.add(guiPanel2);
        this.add(guiPanel3);
        this.add(doneButton);

        done.addActionListener(e -> {

            for( int i=1; i<guiPanel.getComponentCount(); i++ ) {
                JCheckBox checkBox = (JCheckBox)guiPanel.getComponent( i );
                if(!checkBox.isSelected() ) {
                    heap.add(checkBox.getText());
                }
            }
            for( int i=1; i<guiPanel2.getComponentCount()-1; i++ ) {
                JCheckBox checkBox = (JCheckBox)guiPanel2.getComponent( i );
                if(!checkBox.isSelected()) {
                    heap2.add(checkBox.getText().toLowerCase());
                }
            }
            if(heap2.contains("eb")){
                JComboBox yearCombo= (JComboBox) guiPanel3.getComponent(0);
                JComboBox monthCombo= (JComboBox) guiPanel3.getComponent(1);
                JComboBox dayCombo= (JComboBox) guiPanel3.getComponent(2);
                JComboBox hourCombo = (JComboBox) guiPanel3.getComponent(3);
                JComboBox minCombo = (JComboBox) guiPanel3.getComponent(4);
                JComboBox amCombo = (JComboBox) guiPanel3.getComponent(5);

                heap2.add(heap2.indexOf("eb"),"eb "+yearCombo.getSelectedItem().toString()
                        +"-"+monthCombo.getSelectedItem().toString()+"-"+ dayCombo.getSelectedItem().toString()+
                        "," +hourCombo.getSelectedItem().toString()+":"+minCombo.getSelectedItem().toString()+" "
                        +amCombo.getSelectedItem().toString()+" ");
            }
            done.setVisible(false);
            reminder.setVisible(true);
            guiPanel.setVisible(false);
            guiPanel2.setVisible(false);
            guiPanel3.setVisible(false);
            pressed=true;
        });
    }

    public static void main(String[] args) throws InterruptedException {
        guiTesting mainWindow = new guiTesting();
        mainWindow.setTitle("Zine To Easy To Read");
        mainWindow.setDefaultCloseOperation(3);
        mainWindow.setSize(450, 500);
        mainWindow.setLayout(new GridLayout(0,1));
        mainWindow.setVisible(true);
        while(!mainWindow.pressed){
            Thread.sleep(1);
        }
        for (Object o: mainWindow.heap2) {
            System.out.println(o);
        }
    }
}
