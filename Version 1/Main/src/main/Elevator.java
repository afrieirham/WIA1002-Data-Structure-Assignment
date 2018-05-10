/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itsaf
 */
public class Elevator extends JFrame {
    private int time;
    private int currentFloor;
    private int currentPassenger;
    private int totalPassenger;
    private String reportFile;
    
    
    LinkedList<Integer> sourceFloor = new LinkedList<>();
    LinkedList<Integer> destinationFloor = new LinkedList<>();
    
    LinkedList<String> logFile = new LinkedList<>();
    
    Elevator(){
        time = 0;
        currentFloor = 0;
    }
    
    private void openDoor(){
        String string1 = "[" + floorDisplay(currentFloor) + "] " + time + ": Door opening...";
        logFile.add(string1);
        time += 5;
        String string2 = "[" + floorDisplay(currentFloor) + "] " + time + ": Door opened";
        logFile.add(string2);
    }
    
    private void closeDoor(){
        String string1 = "[" + floorDisplay(currentFloor) + "] " + time + ": Door closing...";
        logFile.add(string1);
        time += 5;
        String string2 = "[" + floorDisplay(currentFloor) + "] " + time + ": Door closed";
        logFile.add(string2);
    }
    
    public void passengerIn(){
        openDoor();
        totalPassenger += 1;
        currentPassenger += 1;
        time += 4;
        String string1 = "[" + floorDisplay(currentFloor) + "] " + time + ": " + currentPassenger + " passenger(s) entered the elevator";
        logFile.add(string1);
        closeDoor();
    }
    
    public void passengerOut(){
        openDoor();
        currentPassenger -= 1;
        time += 4;
        String string1 = "[" + floorDisplay(currentFloor) + "] " + time + ": " + currentPassenger + " passenger(s) remained in the elevator";
        logFile.add(string1);
        closeDoor();
    }
    
    public void toFloor(int des){
        destinationFloor.add(des);
        String string1 = "[" + floorDisplay(currentFloor) + "] " + time + ": Heading to floor " + floorDisplay(des);
        logFile.add(string1);
        int floorDifference = Math.abs(des - currentFloor);
        time += floorDifference;
        currentFloor = des;
        String string2 = "[" + floorDisplay(currentFloor) + "] " + time + ": Floor " + floorDisplay(currentFloor) + " reached";
        logFile.add(string2);
    }
    
    public void sourceFloor(int src){
        sourceFloor.add(src);
        if(currentFloor != src){
            toFloor(src);
            destinationFloor.removeLast();
            currentFloor = src;
        }
    }
    
    public void displayReport(){
        
        JOptionPane.showMessageDialog(null, 
                "Time elapsed: " + time + 
                "\nCurrent floor: " + floorDisplay(currentFloor) + 
                "\nTotal passenger served: " + totalPassenger, 
                "Report File", JOptionPane.INFORMATION_MESSAGE);
        
        reportFile = 
                "===============================REPORT FILE===============================" +
                "\nTime elapsed: " + time + " second(s)" +
                "\nCurrent floor: " + floorDisplay(currentFloor) + 
                "\nTotal passenger served: " + totalPassenger +
                "\n==========================================================================";

    }
    
    public void elevatorStatus(){
        System.out.println("Current floor: " + floorDisplay(currentFloor));
    }
    
    private String floorDisplay(int floor){
        if(floor != 0){
            return Integer.toString(floor) ;
        }
        else{
            return "G";
        }
    }
    
    public void display() throws FileNotFoundException, DocumentException{

        String logReport =
                "            =================LOG REPORT FILE=================\n";

        for(int i=0;  i<logFile.size() - 1; i++){
            logReport += "            " + logFile.get(i) + "\n";
        }

        logReport += "            =======================END=======================";

        JFrame window = new JFrame("Log Report File");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea textArea = new JTextArea(10, 20);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setText(logReport);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        window.add(scroll);
        window.setSize(500, 500);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we) {
                int ans = JOptionPane.showConfirmDialog(scroll, "Do you want to save the record into PDF file?", "Saving file", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(ans == JOptionPane.YES_OPTION){
                    String logReport = "\n=============================LOG REPORT FILE=============================\n";
                    
                    for(int i=0;  i<logFile.size() - 1; i++){
                        logReport += logFile.get(i) + "\n";
                    }

                    logReport += "==========================================================================";
                    logReport += "\n\n\n===================================END====================================";
                    try {
                        saveToPDF(logReport);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    we.getWindow().dispose();
                }
            }
        
        });
        
    }
    
    private void saveToPDF(String data) throws FileNotFoundException, DocumentException{
        
        Document document = new Document();
        String fileName = JOptionPane.showInputDialog("File name?");
        fileName += ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        
        document.open();
        document.add(new Paragraph(reportFile));
        document.add(new Paragraph(data));
        document.close();

    } 
    

}

