/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author itsaf
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int destination;
        int source;
        Elevator el = new Elevator();
        String input = JOptionPane.showInputDialog("Enter the number of passenger: ");
        int num = Integer.parseInt(input);
        Passenger[] pass = new Passenger[num];
        createPassenger(num, pass);
        Random rand = new Random();
        
        
        
        for(int i=0; i<num; i++){

            source = pass[i].getSource();
            
            if(source < 0 ){
                endSimulator(el, pass);
                break;
            }
            
            el.sourceFloor(source);
            el.passengerIn();

            destination = pass[i].getDestination();
            
            if(destination < 0){
                endSimulator(el, pass);
                break;
            }
            
            el.toFloor(destination);
            el.passengerOut(); 
            
            if(i==num-1){
                endSimulator(el, pass);
            }
        }

    
    }
    
    public static void endSimulator(Elevator el, Passenger[] pass){        
        el.displayReport();
        try {
            el.display();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createPassenger(int passengerNumber, Passenger[] pass){
        for(int i=0; i<passengerNumber; i++){
            pass[i] = new Passenger();
        }
    }
    
    public static void displayPassengerData(Passenger[] pass){
        System.out.println("");
        System.out.println("_____________________________________________");
        for(int i=0; i<pass.length; i++){
            System.out.println("Passenger " + (i+1));
            System.out.println("Source: " + pass[i].getSource());
            System.out.println("Destination: " + pass[i].getDestination());
            System.out.println("");
        }
    }
  }
