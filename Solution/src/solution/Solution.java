/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;
import java.util.*;
/**
 *
 * @author CONSUMER
 */
public class Solution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(solution("2-4A0r7-4k", 4));
        System.out.println(solution("2-4A0r7-4k", 4));
    }
//    public static String solution(String S, int K){
//        S = S.replaceAll("-", "");
//        StringJoiner joiner = new StringJoiner("-");
//        for (int i = S.length() - 1; i >= 0; ) {
//            StringBuilder builder = new StringBuilder();
//            for (int j = i; j > i - K && j >= 0; j--)
//                builder.append(Character.toUpperCase(S.charAt(j)));
//            i -= K;
//            joiner.add(builder.toString());
//        }
//        return new StringBuilder(joiner.toString()).reverse().toString();
//    }
    public static String solution(String S, int K){
        S=S.replaceAll("-","");
        StringJoiner join = new StringJoiner("-");
        for(int i = S.length()-1; i>=0;){
            StringBuilder newKey = new StringBuilder();
            for(int j = i; j>i -K && j >= 0; j--){
                newKey.append(S.charAt(j));
            }
            i -= K;
            join.add(newKey.toString());
        }
        return new StringBuilder(join.toString()).reverse().toString();
    }
}
