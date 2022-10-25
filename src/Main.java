import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Snake snake = new Snake();
        snake.displaySnakePositions();
        snake.manageSnakePositionAndSize(10);
        snake.displaySnakePositions();
        snake.increaseSize(1);
        snake.manageSnakePositionAndSize(10);
        snake.displaySnakePositions();

    }
}

