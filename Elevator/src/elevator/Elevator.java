/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;

/**
 *
 * @author CONSUMER
 */
public class Elevator {
int startingFloor;
Elevator(){
    startingFloor = 1;
}

public static void main(String[] args) {
    // TODO code application logic here\
    Elevator vator = new Elevator();
    vator.upButton(5);
    System.out.println();
    vator.downButton(2);
    
}
public void upButton(int floor){
    System.out.println("Starting Floor "+ startingFloor);
    for(int i = startingFloor +1; i<=floor;i++)
        System.out.println("Going Up - now at floor "+ i);
    startingFloor=floor;
    System.out.println("Stopping on "+startingFloor);
}
public void downButton(int floor){
    System.out.println("Starting at floor "+ startingFloor);
    for(int i = startingFloor-1;i>=floor;i--)
        System.out.println("Going Down - now at floor "+ i); 
    startingFloor=floor;
    System.out.println("Stopping on "+startingFloor);
}
    
}
