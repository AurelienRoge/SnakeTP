import java.util.ArrayList;

public class Snake {
    ArrayList<SnakeElement> snakeList = new ArrayList<SnakeElement>();//Création du tableau contenant le serpent
    String orientation = "W"; // North East South West
    int sizeToIncreaseLeft = 0;

    public Snake() {
        for (int i = 0; i < 5; i++) {
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
    public void manageSnakePositionAndSize(int mapSize) {
        int snakeEnd = snakeList.get(snakeList.size()-1).getPosition();
        for (int i = snakeList.size() - 1; i >= 0; i--) {
            if (i == 0) {
                switch (orientation) {
                    case "N":
                        snakeList.get(i).setPosition(snakeList.get(i).getPosition() - mapSize);
                        snakeList.get(i).setDirection("N");
                        break;
                    case "E":
                        snakeList.get(i).setPosition(snakeList.get(i).getPosition() + 1);
                        snakeList.get(i).setDirection("E");
                        break;
                    case "S":
                        snakeList.get(i).setPosition(snakeList.get(i).getPosition() + mapSize);
                        snakeList.get(i).setDirection("S");
                        break;
                    case "W":
                        snakeList.get(i).setPosition(snakeList.get(i).getPosition() - 1);
                        snakeList.get(i).setDirection("W");
                        break;
                }
            } else {
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
