package Application.System.GameMechanics;

import javafx.scene.paint.Color;
import java.util.Random;

public class FruitManager {
    private static FruitManager instance;
    public FruitManager(){
        currentFruit = "Apple";
    }

    public static FruitManager getFruitManager() {
        if(instance == null) {
            instance = new FruitManager();
        }
        return instance;
    }


    String currentFruit;
    Random random = new Random();


    public void generateNewFruit(){
        int tmpRand = random.nextInt(101);
        if(tmpRand < 75){
            currentFruit = "Apple";
        } else if (tmpRand >= 92) {
            currentFruit = "Blueberry";
        }
        else{
            currentFruit = "Orange";
        }
    }

    public Color getFruitColor(){
        return switch (currentFruit) {
            case "Apple" -> Color.RED;
            case "Blueberry" -> Color.BLUE;
            case "Orange" -> Color.ORANGE;
            default -> Color.BLACK;
        };
    }

    public String getCurrentFruit(){
        return currentFruit;
    }


}
