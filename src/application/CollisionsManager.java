package application;


public class CollisionsManager {
	
	int windowHeight;
	int windowWidth;
	Snake snake;
	
	public CollisionsManager(int windowHeight, int windowWidth, Snake snake) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.snake = snake;
	}
	
	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	//Détecte les collisions du futur déplacement
	public boolean detectBorderCollisions(){
		if(snake != null) {
			switch (snake.getDirection()) {
		        case UP -> {
		            if (snake.getSnakeHead().getCenterY() < 25) {
		                return true;
		            }
		            return false;
		        }
		        case DOWN -> {
		            if (snake.getSnakeHead().getCenterY() >= windowHeight - 25) {
		                return true;
		            }
		            return false;
		        }
		        case RIGHT -> {
		            if (snake.getSnakeHead().getCenterX() > windowWidth - 25) {
		                return true;
		            }
		            return false;
		        }
		        case LEFT -> {
		            if (snake.getSnakeHead().getCenterX() < 25) {
		                return true;
		            }
		            return false;
		        }
			}
	    return false;
		}
	    return false;
	}

}

