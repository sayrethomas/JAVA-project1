/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.lang.String;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sayre
 */
public class RunnableDemoTest {
    
    public RunnableDemoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class RunnableDemo.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        ArrayList<String> test2 = new ArrayList();
        String test = "Thread: A 3";
        try {
         for(int i = 4; i > 0; i--) {
            test2.add("Thread: A " + i);
             System.out.println("Thread: A " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
      }catch (InterruptedException e) {
         System.out.println("Thread: A interrupted.");
    }
        assertEquals(test, test2.get(1));
    }
    
}
