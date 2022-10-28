import java.util.ArrayList;

public class Snake {
    ArrayList<SnakeElement> snakeList = new ArrayList<SnakeElement>();//Création du tableau contenant le serpent
    String orientation = "W"; // North East South West
    int sizeToIncreaseLeft = 0;
    int mapSize;

    public Snake(int mapSize) {
        this.mapSize = mapSize;
        for (int i = 0; i < 2; i++) {
            SnakeElement tmp = new SnakeElement(5 + i);
            snakeList.add(tmp);
        }
    }

    public void displaySnakePositions() {
        System.out.println("Affichage des positions du serpent");
        for (int i = 0; i < snakeList.size(); i++) {
            System.out.println(i + " " + snakeList.get(i).getPosition());
        }
    }

    //Penser à ajouter le système de collision avec les bordures
    public void manageSnakePositionAndSize() {
        int snakeEnd = snakeList.get(snakeList.size()-1).getPosition();
        for (int i = snakeList.size() - 1; i >= 0; i--) {
            if (i == 0) {//Gestion du déplacement de la tête
                switch (orientation) {
                    case "N":
                        if(snakeList.get(i).getPosition() >= mapSize){
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() - mapSize);
                            snakeList.get(i).setDirection("N");
                        }
                        else{System.out.println("GAME OVER");}
                        break;
                    case "E":
                        if(snakeList.get(i).getPosition() % mapSize != 4){
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() + 1);
                            snakeList.get(i).setDirection("E");
                        }
                        else{System.out.println("GAME OVER");}
                        break;
                    case "S":
                        if(snakeList.get(i).getPosition() < mapSize * (mapSize - 1)){
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() + mapSize);
                            snakeList.get(i).setDirection("S");
                        }
                        else{System.out.println("GAME OVER");}
                        break;
                    case "W":
                        if(snakeList.get(i).getPosition() % mapSize != 0){
                            snakeList.get(i).setPosition(snakeList.get(i).getPosition() - 1);
                            snakeList.get(i).setDirection("W");
                        }
                        else{System.out.println("GAME OVER");}
                        break;
                }
            } else {//Avancement du corps
                snakeList.get(i).setPosition(snakeList.get(i - 1).getPosition());
                snakeList.get(i).setDirection(snakeList.get(i - 1).getDirection());
            }
        }
        //S'il faut agrandir le serpent, on créé une nouvelle case au serpent à la position de son ancienne dernière case
        if(sizeToIncreaseLeft > 0){
            SnakeElement newTile = new SnakeElement(snakeEnd);
            snakeList.add(newTile);
        }
    }

    public void increaseSize(int value) {
        sizeToIncreaseLeft += value;
    }
}
