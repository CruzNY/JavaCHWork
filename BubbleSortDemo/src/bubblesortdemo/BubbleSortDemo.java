/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblesortdemo;

/**
 *
 * @author CONSUMER
 */
public class BubbleSortDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] intArray = {9,8,7,5,6};
        System.out.println("Before sorting");
        for(int i : intArray){
            System.out.println(i);
        }
        bubbleSort(intArray);
        System.out.println("After Sorting");
        for(int i : intArray){
            System.out.println(i);
        }
    }
    public static void bubbleSort(int[] arr){
        int temp = 0;
        for(int i = 0; i<arr.length; i++){
            for(int j = 1; j<(arr.length)-i; j++){
                if(arr[j-1]> arr[j]){
                    // swap the elements
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
}
