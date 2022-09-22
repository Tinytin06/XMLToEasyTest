import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class CSVToNums {

    Scanner myScanner;
    ArrayList myArrayList;
    String simpleName;
    public CSVToNums() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".csv") || f.isDirectory();
            }

            public String getDescription() {
                return "Excel Files";
            }
        });
        int r = chooser.showOpenDialog(new JFrame());
        if (r == JFileChooser.APPROVE_OPTION) {
            String name = chooser.getSelectedFile().getAbsolutePath();
            simpleName = chooser.getSelectedFile().getName();
            simpleName = simpleName.substring(0,simpleName.length()-4);
            myScanner = new Scanner(new File(name));
            myArrayList = new ArrayList<String>();
            while(myScanner.hasNextLine()){
                myArrayList.add(myScanner.nextLine());
            }
            System.out.println(name);
        }else{
            System.out.println("stopping process");
            System.exit(0);
        }

    }

    public String finding_Name_Of_String_Nums(String NameOfString){
        int contains = 0;
        for (int i = 0; i < myArrayList.size(); i++) {
            if (myArrayList.get(i).toString().toLowerCase().contains(NameOfString.toLowerCase())){
                contains++;
            }

        }
        return NameOfString+" "+contains;
    }
    public String findingCollabs(String NameOfString) {
        int contains = 0;
        ArrayList allI = new ArrayList<Integer>();
        for (int i = 0; i < myArrayList.size(); i++) {

            if (myArrayList.get(i).toString().toLowerCase().contains(NameOfString.toLowerCase())&&myArrayList.get(i).toString().toLowerCase().contains("collab")) {
                contains++;
                allI.add(i);
            }
        }
        for (int i = allI.size(); i > 0 ; i--) {
            myArrayList.remove((int)allI.get(i-1));
        }
        return NameOfString+" + Collabs "+ contains;
    }
    public String findingCollabsAndEB(String NameOfString, String EBDateAndTime) throws ParseException {
        int contains = 0;
        ArrayList allI = new ArrayList<Integer>();
        for (int i = 1; i < myArrayList.size(); i++) {
            String date1 =myArrayList.get(i).toString();
            String dateOfOrder;
            String dateEndEB=EBDateAndTime.substring(3);
            //System.out.println(dateEndEB);
            int whereDateIs;
            if(date1.contains("EDT")){
                whereDateIs = date1.indexOf("EDT");
            }else if(date1.contains("EST")){
                whereDateIs = date1.indexOf("EST");
            }else if(date1.contains("CDT")){
                whereDateIs = date1.indexOf("CDT");
            }else if(date1.contains("CST")){
                whereDateIs = date1.indexOf("CST");
            }else if(date1.contains("MDT")){
                whereDateIs = date1.indexOf("MDT");
            }else if(date1.contains("MST")){
                whereDateIs = date1.indexOf("MST");
            }else if(date1.contains("PDT")){
                whereDateIs = date1.indexOf("PDT");
            }else if(date1.contains("PST")){
                whereDateIs = date1.indexOf("PST");
            }else if(date1.contains("AKDT")){
                whereDateIs = date1.indexOf("AKDT");
            }else if(date1.contains("AKST")){
                whereDateIs = date1.indexOf("AKST");
            }else if(date1.contains("HST")){
                whereDateIs = date1.indexOf("HST");
            }else if(date1.contains("HDT")){
                whereDateIs = date1.indexOf("HDT");
            }else{
                whereDateIs = date1.indexOf("HDT");
            }
            if(date1.substring(whereDateIs-19,whereDateIs).startsWith("2")){
                dateOfOrder=date1.substring(whereDateIs-19,whereDateIs);
            }else if(date1.substring(whereDateIs-19,whereDateIs).startsWith("0")){
                dateOfOrder= date1.substring(whereDateIs-20,whereDateIs);
            }else{
                dateOfOrder = "5000-12-31,12:00 pm ";
            }
            SimpleDateFormat dateFormatting = new SimpleDateFormat("yyyy-MM-dd,h:m aaa ");

            if (myArrayList.get(i).toString().toLowerCase().contains(NameOfString.toLowerCase())&&
                    dateFormatting.parse(dateOfOrder).before(dateFormatting.parse(dateEndEB))&&
                    myArrayList.get(i).toString().toLowerCase().contains("collab")){
                contains++;
                allI.add(i);
            }


        }
        for (int i = allI.size(); i > 0 ; i--) {
            //System.out.println(myArrayList.get((Integer) allI.get(i-1)));
            myArrayList.remove((int)allI.get(i-1));
        }
        return NameOfString + " + Collabs + EB " + contains;
    }
    public String findingEB(String NameOfString, String EBDateAndTime) throws ParseException {
        int contains = 0;
        ArrayList allI = new ArrayList<Integer>();
        for (int i = 1; i < myArrayList.size(); i++) {
            String date1 =myArrayList.get(i).toString();
            String dateOfOrder;
            String dateEndEB=EBDateAndTime.substring(3);
            //System.out.println(dateEndEB);
            int whereDateIs;
            if(date1.contains("EDT")){
                whereDateIs = date1.indexOf("EDT");
            }else if(date1.contains("EST")){
                whereDateIs = date1.indexOf("EST");
            }else if(date1.contains("CDT")){
                whereDateIs = date1.indexOf("CDT");
            }else if(date1.contains("CST")){
                whereDateIs = date1.indexOf("CST");
            }else if(date1.contains("MDT")){
                whereDateIs = date1.indexOf("MDT");
            }else if(date1.contains("MST")){
                whereDateIs = date1.indexOf("MST");
            }else if(date1.contains("PDT")){
                whereDateIs = date1.indexOf("PDT");
            }else if(date1.contains("PST")){
                whereDateIs = date1.indexOf("PST");
            }else if(date1.contains("AKDT")){
                whereDateIs = date1.indexOf("AKDT");
            }else if(date1.contains("AKST")){
                whereDateIs = date1.indexOf("AKST");
            }else if(date1.contains("HST")){
                whereDateIs = date1.indexOf("HST");
            }else if(date1.contains("HDT")){
                whereDateIs = date1.indexOf("HDT");
            }else{
                whereDateIs = date1.indexOf("HDT");
            }
            if(date1.substring(whereDateIs-19,whereDateIs).startsWith("2")){
                dateOfOrder=date1.substring(whereDateIs-19,whereDateIs);
            }else if(date1.substring(whereDateIs-19,whereDateIs).startsWith("0")){
                dateOfOrder= date1.substring(whereDateIs-20,whereDateIs);
            }else{
                dateOfOrder = "5000-12-31,12:00 pm ";
            }
            SimpleDateFormat dateFormatting = new SimpleDateFormat("yyyy-MM-dd,h:m aaa ");

            if (myArrayList.get(i).toString().toLowerCase().contains(NameOfString.toLowerCase())&&
                    dateFormatting.parse(dateOfOrder).before(dateFormatting.parse(dateEndEB))){
                contains++;
                allI.add(i);
            }

        }
        for (int i = allI.size(); i > 0 ; i--) {
            myArrayList.remove((int)allI.get(i-1));
        }
        return NameOfString+" + Early Bird "+contains;
    }
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, ParseException {
        CSVToNums filechoose = new CSVToNums();
        System.out.println(filechoose.simpleName);
        guiTesting mainWindow = new guiTesting();
        mainWindow.setTitle("Zine To Easy To Read");
        mainWindow.setDefaultCloseOperation(3);
        mainWindow.setSize(450, 500);
        mainWindow.setLayout(new GridLayout(0,1));
        mainWindow.setVisible(true);
        while(!mainWindow.pressed){
            Thread.sleep(1);
        }
        PrintWriter out;
        int counter = 0;
        while(new File( filechoose.simpleName+counter+".txt").exists()){
            counter++;
        }
            out = new PrintWriter(filechoose.simpleName+counter+".txt");
            out.println("Numbers of Bundles:");

        if(mainWindow.heap2.contains("collab")&&mainWindow.heap2.contains("eb")){
            out.println();
            out.println("Numbers of Collabs + Early Birds:");
            int indexOfEB = mainWindow.heap2.indexOf("eb");
            for (String s: mainWindow.heap) {
                //System.out.println(filechoose.findingCollabs(s));
                out.println(filechoose.findingCollabsAndEB(s,mainWindow.heap2.get(indexOfEB-1)));
            }
        }

        if(mainWindow.heap2.contains("collab")){
            out.println();
            out.println("Numbers of Collabs:");
            for (String s: mainWindow.heap) {
                out.println(filechoose.findingCollabs(s));
            }
        }
        if(mainWindow.heap2.contains("eb")){
            out.println();
            out.println("Numbers of Early Birds:");
            int indexOfEB = mainWindow.heap2.indexOf("eb");
            for (String s: mainWindow.heap) {
                mainWindow.heap2.get(indexOfEB);
                //System.out.println(filechoose.findingEB(s,mainWindow.heap2.get(indexOfEB-1)));
                out.println(filechoose.findingEB(s,mainWindow.heap2.get(indexOfEB-1)));
            }
        }

        out.println();
        out.println("Numbers of Normal Orders:");
        for (String s: mainWindow.heap) {
            out.println(filechoose.finding_Name_Of_String_Nums(s));
        }
        out.close();
        Thread.sleep(10000);
        System.exit(0);
    }
}
