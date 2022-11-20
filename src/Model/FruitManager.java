package Model;

import java.util.Random;

public class FruitManager {
    private Fruit currentFruit = new Fruit(0,0);//Pour éviter une erreur
    int mapSize;

    public FruitManager(int mapSize){
        this.mapSize = mapSize;
    }

    public Fruit getCurrentFruit(){
        return currentFruit;
    }

    public void generateNewRandomFruit(){
        Random random = new Random();
        int randomNumberPosition = random.nextInt(mapSize*mapSize-1);//Int entre 0 et l'index max pour la position;
        //On créé une boucle for qui s'effectue aléatoirement entre 1 et 10 fois pour générer un nombre, cela permet d'avoir un pseudo aléatoire un peu plus aléatoire
        for(int i = 0; i < random.nextInt(10) + 1; i++){
            randomNumberPosition = random.nextInt(mapSize*mapSize-1);//Int entre 0 et l'index max pour la position
        }
        //10 % de chance d'être un fruit qui augmente la longueur de 2, 90% de 1
        if(random.nextInt(100) >= 90){
            currentFruit = new Fruit(randomNumberPosition,2);
        }
        else{
            currentFruit = new Fruit(randomNumberPosition,1);
        }
    }

}
