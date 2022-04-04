/*
This program asks the user for the name of the input file.
Once the user inputs the name of the input file, the program opens the input and gets the matrix.
After taking in the infomation, the program will parse it and store it in a 2D ArrayList for use.

The Floyd's Algorithm For All-Pairs Shortest Path creates the shortest path for the input matrix
and outputs the information to the terminal for the user to view.

Time Complexity of Algorithm = O(n^3)
*/

import java.io.*;
import java.util.*;

public class Floyd_Alg
{
    private static ArrayList<ArrayList<Integer>> inputMatrix = new ArrayList<ArrayList<Integer>>();
    
    public static void main(String[] args)
    {
        int count;
        count = ReadingInput();     // reading input function
        Algorithm(count);           // Algorithm function
        PrintOutput(count);         // Printing to screen function
    }

    private static int ReadingInput()
    {
        Integer count = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Input File Name: ");
        try
        {
            // open file to read
            Scanner scanner = new Scanner(new File(keyboard.nextLine()));

            // read until end of file (EOF)
            while (scanner.hasNextLine()) 
            {
                String[] data = scanner.nextLine().split(" ");
                for(int i = 0; i < data.length; i++)
                {
                    // The program cannot turn "-" into integers. Therefore the program detects when a "-" is present and transforms it
                    // into an int of 1000.
                    if(data[i].equals("-"))//Declaring the usage of "-" to perform the action of converting to 1000
                    {
                        data[i] = "1000";
                    }
                    int tempVar = Integer.parseInt(data[i]);

                    inputMatrix.add(new ArrayList<Integer>());
                    inputMatrix.get(count).add(tempVar);
                }

                count++;
            }

            // Printing out the initial input from the file
            System.out.println();
            System.out.println("Original Input");
            for(int i = 0; i < count; i++)
            {
                System.out.print(i + 1 + "|");
                for(int j = 0; j < inputMatrix.get(i).size(); j++)
                {
                    if(inputMatrix.get(i).get(j) == 1000)
                    {
                        System.out.print("- ");
                    }
                    else
                    {
                        System.out.print(inputMatrix.get(i).get(j) + " ");
                    }
                    
                }

                System.out.println();
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Experienced an error when reading the file.");
        }
        keyboard.close();
        return count;
        
    }// END ReadingInput

    // Floyd's Algorithm For All-Pairs Shortest Path at work and storing back into the same ArrayList.
    private static void Algorithm(int count)
    {
        for(int k = 0; k < count; k++)
        {
            for(int i = 0; i < count; i++)
            {
                for(int j = 0; j < inputMatrix.get(i).size(); j++)
                {
                    int value = Math.min(inputMatrix.get(i).get(j), (inputMatrix.get(i).get(k) + inputMatrix.get(k).get(j)));
                    inputMatrix.get(i).set(j, value); 
                }
            }
        }
    }// END Algorithm

    // Printing the modified ArrayList with the shortest path.
    private static void PrintOutput(int count)
    {

        System.out.println();
        System.out.println("Floyd's Algorithm For All-Pairs Shortest Path");
        for(int i = 0; i < count; i++)
        {
            System.out.print(i + 1 + "|");
            for(int j = 0; j < inputMatrix.get(i).size(); j++)
            {
                System.out.print(inputMatrix.get(i).get(j) + " ");
            }
           
            System.out.println();
        }
    
    }// END PrintOutput

}// END Flyod_Alg Class
