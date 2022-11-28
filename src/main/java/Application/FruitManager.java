package Application;

import javafx.scene.paint.Color;
import java.util.Random;

public class FruitManager {
    private static FruitManager instance;
    public FruitManager(){
        currentFruit = "Apple";
    }

    public static FruitManager getFruitManager() {
        if(instance == null) {
            return new FruitManager();
        }
        return instance;
    }


    String currentFruit;
    Random random = new Random();


    public void generateNewFruit(){
        int tmpRand = random.nextInt(101);
        if(tmpRand < 60){
            currentFruit = "Apple";
        } else if (tmpRand >= 90) {
            currentFruit = "Blueberry";
        }
        else{
            currentFruit = "Orange";
        }
    }

    public Color getFruitColor(){
        switch (currentFruit){
            case "Apple":
                return Color.RED;
            case "Blueberry":
                return Color.BLUE;
            case "Orange":
                return Color.ORANGE;
        }
        return Color.BLACK;//Si erreur
    }

    public String getCurrentFruit(){
        return currentFruit;
    }


}
