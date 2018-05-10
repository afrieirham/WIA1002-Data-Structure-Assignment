/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
//        Scanner input = new Scanner(System.in);
        String test = JOptionPane.showInputDialog("Enter the number of passenger: ");
        int num = Integer.parseInt(test);
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
        el.display();
//        displayPassengerData(pass);
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
