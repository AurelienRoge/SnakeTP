import Model.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //Des test
        GameManager game = new GameManager(10);
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();
        game.getSnake().manageSnakePositionAndSize();//Déplacement du serpent
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();
        game.getSnake().increaseSize(1);
        game.getSnake().manageSnakePositionAndSize();//Déplacement du serpent
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();
        game.getSnake().manageSnakePositionAndSize();//Déplacement du serpent
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();
        game.getSnake().manageSnakePositionAndSize();//Déplacement du serpent
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();
        game.getSnake().manageSnakePositionAndSize();//Déplacement du serpent
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();
        game.getSnake().manageSnakePositionAndSize();//Déplacement du serpent
        System.out.println("\nAFFICHAGE DE LA CARTE");
        game.displayMap();


    }
}

