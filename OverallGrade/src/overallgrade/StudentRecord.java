/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overallgrade;

/**
 *
 * @author alexiscruz
 */
public class StudentRecord {
    private int quiz1;
    private int quiz2;
    private int quiz3;
    private double quizPer;
    private int midterm;
    private double midPer;
    private int finalTest;
    private double finalPer;
    private double oGrade;
    String letterGrade;

    public int getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(int quiz1) {
        this.quiz1 = quiz1;
    }

    public int getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(int quiz2) {
        this.quiz2 = quiz2;
    }

    public int getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(int quiz3) {
        this.quiz3 = quiz3;
    }

    public int getMidterm() {
        return midterm;
    }

    public void setMidterm(int midterm) {
        this.midterm = midterm;
    }

    public int getFinalTest() {
        return finalTest;
    }

    public void setFinalTest(int finalTest) {
        this.finalTest = finalTest;
    }
    public void QuizPercent(){
        int t = quiz1 + quiz2+ quiz3;
        double per = (t/3)*.25;
        this.quizPer = per;
    }  
    public void MidPer(){
        double per = midterm * .35;
        this.midPer = per;
    }
    public void finalPer(){
        double per = finalTest * .40;
        this.finalPer = per;
    }
    public void overallGrade(){
        this.oGrade = quizPer + midPer + finalPer;
    }
    public double getOverallGrade(){
        return oGrade;
    }
    public void Letter(double overallGrade){
        if((overallGrade >= 90) && (overallGrade <100))
            letterGrade = "A";
        else if((overallGrade >=80) && (overallGrade <90))
            letterGrade = "B";
        else if((overallGrade >=70 ) && (overallGrade <80))
            letterGrade = "C";
        else if((overallGrade >=60) && (overallGrade < 70))
            letterGrade =  "D";
        else
            letterGrade = "F";
    }
    public String getLetter(){
        return letterGrade;
    }
    public String toString(StudentRecord x){
        return ("Qiuz 1: "+ x.getQuiz1() + " Quiz 2: " + x.getQuiz2() + " Quiz 3: "+ x.getQuiz3()+ " Midterm: " + x.getMidterm() + " Final "+ x.getFinalTest() + " Overall Grade: "+ x.getOverallGrade()+ " Letter Grade " + x.getLetter());
    }
    public double getQPer(){
        return quizPer;
    }
}
