/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author itsaf
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int destination;
        int source;
        Elevator el = new Elevator();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of passenger: ");
        int num = input.nextInt();
        Passenger[] pass = new Passenger[num];
        createPassenger(num, pass);
        Random rand = new Random();
        for(int i=0; i<num; i++){
            
            System.out.println("");
            el.elevatorStatus();
            System.out.print("Where are you from: ");
            source = pass[i].getSource();
            System.out.println(source);
            System.out.println();
            System.out.println("");
            
            if(source < 0 ){
                endSimulator(el, pass);
                break;
            }
            
            el.sourceFloor(source);
            el.passengerIn();
            
            System.out.println("");
            System.out.print("Where are you going: ");
            destination = pass[i].getDestination();
            System.out.println(destination);
            System.out.println("");
            
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
        System.out.println("");
        System.out.println("Simulator ended");
        el.displayReport();
        el.display();
        displayPassengerData(pass);
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
