public class Main {

    public static void main(String[] args) {

        //Des test
        GameManager game = new GameManager(10);
        game.getSnake().displaySnakePositions();
        game.updateAndDisplayMap();
        game.getSnake().manageSnakePositionAndSize();
        game.getSnake().displaySnakePositions();
        game.getSnake().increaseSize(1);
        game.getSnake().manageSnakePositionAndSize();
        game.getSnake().displaySnakePositions();
        game.updateAndDisplayMap();

    }
}

