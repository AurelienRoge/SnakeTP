package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    static FileManager instance;

    private FileManager(){

    }

    public static FileManager getFileManager(){
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }



    public void createFile(){
        try {
            File myObj = new File("snakeData.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    public static void writeToFile(String toWrite) {
        try {
            FileWriter myWriter = new FileWriter("snakeData.txt");
            myWriter.write(toWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int readFile(){
        try {
            File myObj = new File("snakeData.txt");
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                int data = Integer.parseInt(myReader.nextLine());
                System.out.println(data);
                return data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return 0;
    }

}
