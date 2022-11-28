package Application.System.ScoreAndFile;

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
            File snakeDataFile = new File("snakeData.txt");
            if (snakeDataFile.createNewFile()) {
                System.out.println("Fichier créé: " + snakeDataFile.getName());
            } else {
                System.out.println("Le fichier existe déjà");
            }
        } catch (IOException e) {
            System.out.println("Il y a eu une erreur");
            e.printStackTrace();
        }
    }



    public static void writeToFile(String toWrite) {
        try {
            FileWriter myWriter = new FileWriter("snakeData.txt");
            myWriter.write(toWrite);
            myWriter.close();
            System.out.println("Écriture dans le fichier réussie");
        } catch (IOException e) {
            System.out.println("Il y a eu une erreur");
            e.printStackTrace();
        }
    }

    public static int readFile(){
        try {
            File snakeDataFile = new File("snakeData.txt");
            Scanner myReader = new Scanner(snakeDataFile);
            if (myReader.hasNextLine()) {//On lit la première ligne
                int data = Integer.parseInt(myReader.nextLine());
                System.out.println(data);
                return data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Il y a eu une erreur");
            e.printStackTrace();
        }
        return 0;//Si on arrive pas à lire, on retourne 0;
    }
}
