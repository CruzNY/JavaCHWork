// Print Numbers 1 - 10 without using any loops
package printnumbersonethroughten;

/**
 *
 * @author CONSUMER
 */
public class PrintNumbersOneThroughTen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printNumbers(1);
    }
    public static void printNumbers(int n){
         if(n<= 10){
             System.out.println(n);
             printNumbers(n+1);
         }
    }
}
