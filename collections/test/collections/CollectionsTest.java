/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

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
public class CollectionsTest {
    
    public CollectionsTest() {
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
     * Test of main method, of class Collections.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Collections.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bag1 method, of class Collections.
     */
    @Test
    public void testBag1() {
        System.out.println("bag1");
        int size = 0;
        Collections instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.bag1(size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bag2 method, of class Collections.
     */
    @Test
    public void testBag2() {
        System.out.println("bag2");
        int size = 0;
        Collections instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.bag2(size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bigBag method, of class Collections.
     */
    @Test
    public void testBigBag() {
        System.out.println("bigBag");
        int size = 0;
        Collections instance = null;
        ArrayList<ArrayList> expResult = null;
        ArrayList<ArrayList> result = instance.bigBag(size);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Collections.
     */
    @Test
    public void testRun() throws Exception {
        System.out.println("run");
        Collections instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
