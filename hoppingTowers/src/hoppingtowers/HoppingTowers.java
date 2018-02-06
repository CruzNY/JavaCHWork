/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoppingtowers;

/**
 *
 * @author CONSUMER
 */
public class HoppingTowers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //int[] towers = {4,2,0,3,2,1,2,1};
        int[] towers = {1,0,3,0,0,0};
        System.out.println(isHoppable(towers));
    }
    
    static boolean isHoppable(int[] arr){
        int limit = arr.length;
        for(int i=arr.length-1; i >= 0; i--){
            if((arr[i] + i) >= limit){
                limit = i;
                if(i == 0){
                    return true;
                }
            }
        }
        return false;
    }
    
}
