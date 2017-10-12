/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sayre
 */
  public class Collections {
    String [] args;
    Collections(String[] _args) {
        args = _args;
    }
    public static void main(String[] args) throws Exception{
        Collections app = new Collections(args);
        app.run();
    }
    
   ArrayList<String> bag1(int size){
       ArrayList < String > bag1 = new ArrayList < String > (size);
        for (int i=0; i<size; ++i) {
            bag1.add("Number: " + i);
        }
        return bag1;
   }
    ArrayList<String> bag2(int size){
       ArrayList < String > bag2 = new ArrayList < String > ();
        bag2.addAll(bag1(size/2));
        return bag2;
   }
    ArrayList<ArrayList> bigBag(int size){
       ArrayList <ArrayList> bigBag = new ArrayList <ArrayList> ();
        for(int i=0; i<size; ++i)
        {
            bigBag.add(bag1(size));
            bigBag.add(bag2(size*2));
        }
        return bigBag;
   }
     void run() throws Exception {
         int num = 4;
         System.out.println(bag1(num));
          System.out.println(bag2(num));
        System.out.println(bigBag(num));
    }
}
