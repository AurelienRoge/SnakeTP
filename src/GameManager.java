//A FAIRE : FONCTION DE LOOP INFINIE QUI GERE TOUT (déplacement, affichage, spawn des fruits)
//          SYSTEME DE CONTROLES
public class GameManager {
    Snake snake;
    int mapSize;

    public GameManager(int mapSize){
        this.snake = new Snake(mapSize);
        this.mapSize = mapSize;
    }

    public Snake getSnake(){
        return snake;
    }

    public void updateAndDisplayMap(){
        int[] map = new int[mapSize*mapSize];
        //Initialisation du tableau : on remplit de 0
        for(int i = 0; i < mapSize*mapSize; i++){
            map[i] = 0;
        }

        //On récupère la position de toutes les cases du serpents et on ajoute des 1 là où elles se trouvent
        int[] snakePositions = snake.getAllPositions();
        for (int snakePosition : snakePositions) {
            map[snakePosition] = 1;
        }

        //On affiche toute la carte
        for(int i = 0; i < mapSize*mapSize;i++){
            if(i % mapSize == 0){
                System.out.println();
            }
            System.out.print(map[i]);
        }
    }
}
