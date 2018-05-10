/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;

/**
 *
 * @author itsaf
 */
public class Passenger {
    
    private final int source;
    private final int destination;
    private final int time;
    
    Random rand = new Random();
    
    public Passenger(){
        source = rand.nextInt(11);
        destination = rand.nextInt(11);
        time = rand.nextInt(10001);
    }
    
    public int getSource(){
        return source;
    }
    
    public int getDestination(){
        return destination;
    }
    
}
