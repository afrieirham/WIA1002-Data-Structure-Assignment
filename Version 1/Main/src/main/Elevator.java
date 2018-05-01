/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author itsaf
 */
public class Elevator {
    private int time;
    private int currentFloor;
    private int currentPassenger;
    private int totalPassenger;
    
    Elevator(){
        time = 0;
        currentFloor = 0;
    }
    
    private void openDoor(){
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": Door opening...");
        time += 5;
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": Door opened");
    }
    
    private void closeDoor(){
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": Door closing...");
        time += 5;
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": Door closed");
    }
    
    public void passengerIn(){
        openDoor();
        totalPassenger += 1;
        currentPassenger += 1;
        time += 4;
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": " + currentPassenger + " passenger(s) entered the elevator");
        closeDoor();
    }
    
    public void passengerOut(){
        openDoor();
        currentPassenger -= 1;
        time += 4;
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": " + currentPassenger + " passenger(s) remained in the elevator");
        closeDoor();
    }
    
    public void toFloor(int des){
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": Heading to floor " + floorDisplay(des));
        int floorDifference = Math.abs(des - currentFloor);
        time += floorDifference;
        currentFloor = des;
        System.out.println("[" + floorDisplay(currentFloor) + "] " + time + ": Floor " + floorDisplay(currentFloor) + " reached");
    }
    
    public void sourceFloor(int src){
        if(currentFloor != src){
            toFloor(src);
            currentFloor = src;
        }
    }
    
    public void displayReport(){
        System.out.println("_____________________________________________");
        System.out.println("Time elapsed: " + time);
        System.out.println("Current floor: " + floorDisplay(currentFloor));
        System.out.println("Total passenger served: " + totalPassenger);
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

}

