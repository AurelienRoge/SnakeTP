package Application;


import javafx.scene.shape.Circle;

public class CollisionsManager {
	
	private final int windowHeight;
	private final int windowWidth;
	
	private static CollisionsManager instance;
	Snake snake;
	
	private CollisionsManager(int windowHeight, int windowWidth, Snake snake) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.snake = snake;
	}
	
	public static CollisionsManager getCollisionsManager(int windowHeight, int windowWidth, Snake snake) {
		if(instance == null) {
			return new CollisionsManager(windowHeight, windowWidth, snake);
		}
		return instance;
	}
	
	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	//Détecte les collisions du futur déplacement
	public boolean detectBorderCollisions(){
		if(snake != null) {
			switch (snake.getDirection()) {
		        case UP -> {
					return snake.getSnakeHead().getCenterY() < 25;
				}
		        case DOWN -> {
					return snake.getSnakeHead().getCenterY() >= windowHeight - 25;
				}
		        case RIGHT -> {
					return snake.getSnakeHead().getCenterX() > windowWidth - 25;
				}
		        case LEFT -> {
					return snake.getSnakeHead().getCenterX() < 25;
				}
			}
	    return false;
		}
	    return false;
	}

	public boolean detectSelfCollision(){
		Circle head = snake.getSnakeHead();

		//Modifier la méthode de détection parce que les cercles de bases sont emboités dans eux même
		for(int i = 2; i < snake.getLength() - 1; i++){
			if(head.getCenterX() == snake.getBody().get(i).getCenterX() && head.getCenterY() == snake.getBody().get(i).getCenterY()){
				return true;
			}
		}
		System.out.println("No collisions with itself");

		return false;
	}

}

