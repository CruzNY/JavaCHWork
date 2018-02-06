/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overallgrade;
import java.util.Scanner;
/**
 *
 * @author alexiscruz
 */
public class OverallGrade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StudentRecord alex = new StudentRecord();
        Scanner input = new Scanner(System.in);        
        int grade;
        System.out.println("Enter Quiz 1 Grade");
        grade = input.nextInt();
        alex.setQuiz1(grade);
        System.out.println("Enter Quiz 2 Grade");
        grade = input.nextInt();
        alex.setQuiz2(grade);
        System.out.println("Enter Quiz 3 Grade");
        grade = input.nextInt();
        alex.setQuiz3(grade);
        System.out.println("Enter Midterm Grade");
        grade = input.nextInt();
        alex.setMidterm(grade);
        System.out.println("Enter Final Grade");
        grade = input.nextInt();
        alex.setFinalTest(grade);
        
        alex.QuizPercent();
        alex.MidPer();
        alex.finalPer();
        alex.overallGrade();
        alex.Letter(alex.getOverallGrade());
        
        System.out.println(alex.toString(alex));
        System.out.println(alex.getQPer());
    }
    
}
