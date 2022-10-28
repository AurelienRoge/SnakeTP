import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Snake snake = new Snake(10);
        snake.displaySnakePositions();
        snake.manageSnakePositionAndSize();
        snake.displaySnakePositions();
        snake.increaseSize(1);
        snake.manageSnakePositionAndSize();
        snake.displaySnakePositions();

    }
}

