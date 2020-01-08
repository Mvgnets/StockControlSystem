/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcontrol;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static stockcontrol.StockControl.hMap;

/**
 *
 * @author EchoY
 */
public class StockControlTest {

    public StockControlTest() {
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
     * Test of buildHashMap method, of class StockControl.
     */
    @Test
    public void testBuildHashMap() {
        System.out.println("Testing buildHashMap");
        try {
            Scanner sc = new Scanner(new File("StockData.txt"));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                hMap.put(line[0], line[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        String expResult = "20";
        String result = hMap.get("001");
        assertEquals(expResult, result);
    }

    /**
     * Test of getOldStockData method, of class StockControl.
     */
    @Test
    public void testGetOldStockData() {

    }

    /**
     * Test of getOldProdData method, of class StockControl.
     */
    @Test
    public void testGetOldProdData() {

    }

    /**
     * Test of readProdData method, of class StockControl.
     */
    @Test
    public void testReadProdData() {

    }

    /**
     * Test of newProdCode method, of class StockControl.
     */
    @Test
    public void testNewProdCode() {

    }

    /**
     * Test of writeProdData method, of class StockControl.
     */
    @Test
    public void testWriteProdData() {

    }

    /**
     * Test of appendStock method, of class StockControl.
     */
    @Test
    public void testAppendStock() {

    }

    /**
     * Test of oWStock method, of class StockControl.
     */
    @Test
    public void testOWStock() {

    }

    /**
     * Test of appendProd method, of class StockControl.
     */
    @Test
    public void testAppendProd() {

    }

    /**
     * Test of changeLog method, of class StockControl.
     */
    @Test
    public void testChangeLog() {

    }

    /**
     * Test of getTime method, of class StockControl.
     */
    @Test
    public void testGetTime() {

    }

    /**
     * Test of searchStock method, of class StockControl.
     */
    @Test
    public void testSearchStock() {

    }

}
