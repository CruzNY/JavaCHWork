/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addtoonetoarray;
import java.util.Arrays;
/**
 *
 * @author CONSUMER
 */

public class AddToOneToArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] array = {3,3,3,9};
        /*
        the int array{1,3,2,4} respresents the number 1,324
        we need to write a method that adds one to this array.
        
        the return value of the array should be {1,3,2,5}
        */
        System.out.println("Before use of method " + Arrays.toString(array));
        
        System.out.println("After the use of the method "+Arrays.toString(addOneToArray(array)));
        
        int head =0;
        
    }

    public static int[] addOneToArray(int[] arr){
        // creating a new array that is the same length as the input array 
        int[] returnArray = new int[arr.length];
        // creating the carry for when the number is 9
        int add = 1;
        
        /*
        for loop for iterating through the loop backwards,
        since we adding to the rightmost digit, we need to go from right
        to left.
        */
        for(int i =arr.length-1; i>=0; i--){
            /*
            sum is going to be placed into the array,
            on the first loop arr[i] is added by 1 since 
            the loop is going in reverse this would be the 
            right most element meaning the element we need to add 1 too.
            */
            int sum = arr[i] + add;
            
            /*
            now we change the value of the add depending on sum
            - if sume equals 10, then we are going to keep add as 1 since we
            need to add 1 / carry the the 1 to the next left element.
            */
            if(sum == 10)
                add = 1;
            /*
            if sum equals 0-9 we change the add to 0 since we dont need to
            add 1 / carry 1 to the next element.
            */
            else
                add = 0;
            /*
            now that we have set the grounds for the next left number, we now
            set returnArray to the sum after we get the modulus.
            
            if works such that if sum = 3, then 3%10 = 3
            if sum = 10 then 10%10 = 0
            */
            returnArray[i] = sum % 10;
        }
        /*
        after coming out of the for floop, the array should be 
        added by one. ie {1,2,3,4} would be {1,2,3,5} after this loop.
        In the case that the final left element sum is 10 then this
        if statement would run, since 10 was the sum then add remained 1.
        
        this if statement creates an array that is greater in length
        to acommadate the carried number.
        
        returnArray[0]=1 beacuse the final left element as 10 and is
        0 in the array so this 1 is carried in front of it.
        */
        if(add == 1){
            returnArray = new int[arr.length+1];
            returnArray[0] = 1;
        }
        // returns the new array
        return returnArray;
    }
    
}
