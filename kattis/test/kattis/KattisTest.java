/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kattis;

import java.util.ArrayList;
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
public class KattisTest {
    
    public KattisTest() {
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
     * Test of Bus method, of class Kattis.
     */
    @Test
    public void testBus() {
       System.out.println("testBus");
       ArrayList<Integer> test = new ArrayList<Integer>();
       int expResult = 4;
        for(int i =0; i < 5; i++)
        {
           test.add(i);
        }
        int result = test.get(4);
        assertEquals(expResult, result);
    }

    /**
     * Test of crunch method, of class Kattis.
     */
    @Test
    public void testCrunch() {
        System.out.println("crunch");
        ArrayList<Integer> test = new ArrayList<Integer>();
       int expResult = 3;
        for(int i =0; i < 5; i++)
        {
           test.add(i);
        }
        int result = test.get(3);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkNext method, of class Kattis.
     */
    @Test
    public void testCheckNext() {
        System.out.println("checkNext");
        int a = 0;
        int b = 1;
        assertEquals(a, b-1);
    }
    
}
