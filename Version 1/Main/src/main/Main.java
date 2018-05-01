/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
        
        while(true){
            
            System.out.println("");
            el.elevatorStatus();
            Scanner input = new Scanner(System.in);
            System.out.print("Where are you from: ");
            source = input.nextInt();
            System.out.println("");
            
            if(source < 0 ){
                System.out.println("Simulator ended");
                el.displayReport();
                break;
            }
            
            el.sourceFloor(source);
            el.passengerIn();
            
            System.out.println("");
            System.out.print("Where are you going: ");
            destination = input.nextInt();
            System.out.println("");
            
            if(destination < 0){
                System.out.println("Simulator ended");
                el.displayReport();
                break;
            }
            
            el.toFloor(destination);
            el.passengerOut(); 
        }

    
    }
    
}
