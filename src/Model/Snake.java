package Model;


import java.util.ArrayList;

public class Snake {
    ArrayList<SnakeElement> snakeList = new ArrayList<>();//Création du tableau contenant le serpent
    String orientation = "Left"; // Up Down Left Right
    int sizeToIncreaseLeft = 0;
    int mapSize;

    public Snake(int mapSize) {
        this.mapSize = mapSize;
        for (int i = 0; i < 2; i++) {
            SnakeElement tmp = new SnakeElement(5 + i);
            snakeList.add(tmp);
        }
    }

    public void displaySnakePositions() { //FONCTION DE TEST QUI SERA A SUPPRIMER
        System.out.println("Affichage des positions du serpent");
        for (int i = 0; i < snakeList.size(); i++) {
            System.out.println(i + " " + snakeList.get(i).getPosition());
        }
    }


    //Fonction qui s'occupe du déplacement du serpent et détecte les collisions avec la bordure
    public void manageSnakePositionAndSize() {
        int snakeEnd = snakeList.get(snakeList.size() - 1).getPosition();
        if (!detectCollisions()) {
            for (int i = snakeList.size() - 1; i >= 0; i--) {
                if (i == 0) {//Gestion du déplacement de la tête
                    switch (orientation) {
                        case "Up" -> {
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() - mapSize);
                            snakeList.get(i).setDirection("Up");
                        }
                        case "Right" -> {
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() + 1);
                            snakeList.get(i).setDirection("Right");
                        }
                        case "Down" -> {
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() + mapSize);
                            snakeList.get(i).setDirection("Down");
                        }
                        case "Left" -> {
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() - 1);
                            snakeList.get(i).setDirection("Left");
                        }
                    }


                } else {//Avancement du corps
                    snakeList.get(i).setPosition(snakeList.get(i - 1).getPosition());
                    snakeList.get(i).setDirection(snakeList.get(i - 1).getDirection());
                }
            }
            //S'il faut agrandir le serpent, on créé une nouvelle case au serpent à la position de son ancienne dernière case
            if (sizeToIncreaseLeft > 0) {
                SnakeElement newTile = new SnakeElement(snakeEnd);
                snakeList.add(newTile);
                sizeToIncreaseLeft--;
            }

        } else {//S'il y a collision
            System.out.println("GAME OVER");
        }
    }

    //Incrémente la variable comptant le nombre de cases à rajouter à la taille du serpent
    public void increaseSize(int value) {
        sizeToIncreaseLeft += value;
    }

    //Retourne les positions de toutes les cases du serpent sous forme de tableau de int
    public int[] getAllPositions(){
        int[] positions = new int[snakeList.size()];
        for(int i = 0; i < snakeList.size(); i++){
            positions[i] = snakeList.get(i).getPosition();
        }
        return positions;
    }

    //Retourne la tête du serpent
    private SnakeElement getSnakeHead(){
        return snakeList.get(0);
    }

    //Détecte les collisions du futur déplacement
    private boolean detectCollisions(){
        SnakeElement head = getSnakeHead();
        switch (head.getDirection()) {
            case "Up" -> {
                if (head.getPosition() < mapSize) {
                    System.out.println("Collision détectée up");
                    return true;
                }
                return false;
            }
            case "Down" -> {
                if (head.getPosition() >= mapSize * mapSize - mapSize) {
                    System.out.println("Collision détectée up");
                    return true;
                }
                return false;
            }
            case "Right" -> {
                if (head.getPosition() % mapSize == mapSize - 1) {
                    System.out.println("Collision détectée up");
                    return true;
                }
                return false;
            }
            case "Left" -> {
                if (head.getPosition() % mapSize == 0) {
                    System.out.println("Collision détectée up");
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}


