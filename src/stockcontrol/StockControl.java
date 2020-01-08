/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockcontrol;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author N3023685
 */
public class StockControl {

    static HashMap<String, String> hMap = new HashMap<String, String>();

    /**
     * @param args the command line arguments
     */
    public static void buildHashMap() {
        //read through the stock file and build a hashmap from it
        try {
            Scanner sc = new Scanner(new File("StockData.txt"));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                hMap.put(line[0], line[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String readStockData(String prodCode) {
        //return the stock count for the given item
        String output = hMap.get(prodCode);
        return output;
    }

    public static String getOldStockData() {
        //build and return a string from the StockData file
        String oldText = "";
        try {
            Scanner sc = new Scanner(new File("StockData.txt"));
            while (sc.hasNextLine()) {
                oldText += System.getProperty("line.separator") + sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return oldText;
    }

    public static String getOldProdData() {
        //build and return a string from the ProdData file
        String oldText = "";
        try {
            Scanner sc = new Scanner(new File("ProdData.txt"));
            while (sc.hasNextLine()) {
                oldText += System.getProperty("line.separator") + sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return oldText;
    }

    public static String readProdData() {
        //read and format the product data
        String output = "";
        try {
            Scanner sc = new Scanner(new File("ProdData.txt"));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                output += parts[0] + " | " + parts[1] + " | " + parts[2] + " | " + readStockData(parts[0]) + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return output;
    }

    public static String newProdCode() {
        //read the last product code and increment it to generate a new one
        int increment;
        String output = "";
        try {
            Scanner sc = new Scanner(new File("ProdData.txt"));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                output = parts[0];
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        increment = Integer.parseInt(output) + 1;
        output = "0" + Integer.toString(increment);
        return output;
    }

    public static void writeProdData(String prodCode, String desc, String price, String quantity) {
        String prodOutput = prodCode + "," + desc + "," + price;
        String stockOutput = prodCode + "," + quantity;
        String oldQuant = hMap.get(prodCode);
        String oldText = getOldStockData();
        String newText;
        String changeLog = prodCode + "," + desc + "," + price + "," + quantity + " | " + getTime();
        int calc;
        //if the product code already exists, re write the line for the product code in question nad update the hashmap
        if (hMap.containsKey(prodCode)) {
            calc = Integer.parseInt(oldQuant) + Integer.parseInt(quantity);
            newText = oldText.replaceAll(prodCode + "," + hMap.get(prodCode), prodCode + "," + calc);
            oWStock(newText);
            hMap.replace(prodCode, Integer.toString(calc));
            changeLog(changeLog);
        // if it does not exist, append the files with a new line and update the hashmap
        } else {
            appendStock(stockOutput);
            appendProd(prodOutput);
            changeLog(changeLog);
            hMap.put(prodCode, quantity);
        }
    }

    public static void appendStock(String stockOutput) {
        try {
            FileWriter stockFW = new FileWriter("StockData.txt", true);
            PrintWriter stockPrinter = new PrintWriter(stockFW);
            //add the item to the end of the file
            stockPrinter.print(System.getProperty("line.separator") + stockOutput);
            stockPrinter.close();
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public static void oWStock(String stockOutput) {
        try {
            FileWriter stockFW = new FileWriter("StockData.txt", false);
            PrintWriter stockPrinter = new PrintWriter(stockFW);
            //overwrite the file with the new data
            stockPrinter.print(stockOutput);
            stockPrinter.close();
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public static void appendProd(String prodOutput) {
        try {
            FileWriter prodFW = new FileWriter("ProdData.txt", true);
            PrintWriter prodPrinter = new PrintWriter(prodFW);
            //add the item to the end of the file
            prodPrinter.print(System.getProperty("line.separator") + prodOutput);
            prodPrinter.close();
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public static void changeLog(String changeLog) {
        try {
            FileWriter changeFW = new FileWriter("ChangeData.txt", true);
            PrintWriter changePrinter = new PrintWriter(changeFW);
            //add the item to the end of hte change log file
            changePrinter.print(System.getProperty("line.separator") + changeLog);
            changePrinter.close();
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public static String getTime() {
        // this generates a time stamp and formats it
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss z");
        return sdf.format(cal.getTime());
    }

    public static String searchStock(String prodCode) {
        String output = null;
        try {
            Scanner sc = new Scanner(new File("ProdData.txt"));
            //search each line for the product code in question
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts[0].equals(prodCode)) {
                    output = parts[0] + "," + parts[1] + "," + parts[2] + "," + hMap.get(prodCode);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return output;
    }
}
