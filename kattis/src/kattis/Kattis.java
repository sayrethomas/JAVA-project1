/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kattis;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/*
 * @author sayre
 */
public class Kattis {
   String [] args;
    Kattis(String[] _args) {
        args = _args;
    }
    public static void main(String[] _args) throws Exception {
        Kattis app = new Kattis(_args);
        app.run();
    }
    
    void Bus(int nums[]){
        ArrayList<Integer> sign = new ArrayList<Integer>();
        for(int i =0; i < nums.length; i++)
        {
           sign.add(nums[i]);
        }
        Collections.sort(sign);
        System.out.println(sign);
        crunch(sign, nums.length);
    }
    
    void crunch(ArrayList<Integer> sign, int size){
        ArrayList<Integer> finalSign = new ArrayList<Integer>();
        int temp = sign.get(0);
        int i= 1;
        int j = 0;
        finalSign.add(temp);
        while(j != size && temp != sign.get(size-1)){
            while(i != size && checkNext(temp, sign.get(i))){
                temp = sign.get(i);
                i++;
                }
            finalSign.add(temp);
            j = i;
            if(i != size){
                temp = sign.get(i);
                finalSign.add(temp);
                i++;
            }           
        }
            
        System.out.println(finalSign);
    }
    boolean checkNext(int a, int b){
        if(a == b-1) return true;
        else return false;
    }
    
    void run() throws Exception {
        int[] nums = new int[]{180,141,174,143,142,175};
        Bus(nums); 
        //Bus(nums1); Bus(nums2);
    }
}
