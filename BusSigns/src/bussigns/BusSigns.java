/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussigns;

import java.io.*;
import java.util.Arrays;

/**
 *
 * @author sayre
 */
public class BusSigns {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // The name of the file to open.
        String fileName = "sample-busnumbers.in";
        String fileOutName = "sample-busnumbers.txt";
        String line = "";
        String splitBy = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while ((line = br.readLine()) != null){
                int i = 0;
                String[] bus = line.split(splitBy);
                    Arrays.sort(bus);
                    while(bus[i] != null){
                        if(Integer.parseInt(bus[i]) != Integer.parseInt(bus[i])-1){
                            System.out.println(bus[i]);
                        }
                    }
                    
                    

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
