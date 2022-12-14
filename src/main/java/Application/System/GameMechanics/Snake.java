package Application.System.GameMechanics;

import java.util.List;
import java.util.ArrayList;

import Application.Game;
import Application.System.ScoreAndFile.ScoreManager;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake extends Circle{
	private final List<Circle> body;
	private int length = 0;
	private Direction direction;
	private static final int Step = 5;

	
	public Snake(double posX, double posY, double radius) {
		super(posX, posY, radius);
		body = new ArrayList<>();
		direction = Direction.UP;
		this.setFill(Color.GREEN);
	}
	
	//on fait bouger tout le serpent
	public void step() {
		for(int i = length -1; i>=0;i--) {
			if(i == 0) {//Tête du serpent
				body.get(i).setCenterX(getCenterX());
				body.get(i).setCenterY(getCenterY());
			}
			else {//Suite du corps
				body.get(i).setCenterX(body.get(i-1).getCenterX());
				body.get(i).setCenterY(body.get(i-1).getCenterY());
			}
		}
		
		if(direction == Direction.UP) {
			setCenterY(getCenterY()-2*Step);
		}
		if(direction == Direction.DOWN) {
			setCenterY(getCenterY()+2*Step);
		}
		if(direction == Direction.LEFT) {
			setCenterX(getCenterX()-2*Step);
		}
		if(direction == Direction.RIGHT) {
			setCenterX(getCenterX()+2*Step);
		}
	}

	//permet de connaitre le bout de sa queue
	private Circle endOfTail() {
		if(length == 0) {
			return this;
		}
		return body.get(length - 1);
	}
	
	//permet de modifier le serpent quand il mange un cercle
	public void eat(Circle food, String fruitEaten, Pane root, ScoreManager scoreManager) {
		Circle tail = endOfTail();
		switch (fruitEaten) {
			case "Apple" -> {
				tail = endOfTail();
				food.setCenterX(tail.getCenterX());
				food.setCenterY(tail.getCenterY());

				//Systeme serpent bicolore
				if (length % 4 < 2) {
					food.setFill(Color.GREEN);
				} else {
					food.setFill(Color.LIGHTGREEN);
				}
				body.add(length++, food);
				scoreManager.increaseScore(5);
			}
			case "Blueberry" -> {
				root.getChildren().remove(food);
				if (length > 3) {
					root.getChildren().remove(endOfTail());
					body.remove(--length);//On retire le dernier cercle

				}
				scoreManager.increaseScore(3);
			}
			case "Orange" -> {
				root.getChildren().remove(food);
				scoreManager.increaseScore(15);
			}
		}
	}

	//Initialisation du serpent : on lui ajoute un deuxieme cercle représentant le début de son corps
	public void initializeSnake(Game game, Pane root){
		Circle tail = endOfTail();

		Circle firstCircle = new Circle(tail.getCenterX(),tail.getCenterY(), game.getRadius());//creation du cercle
		Platform.runLater(()->{
			root.getChildren().add(firstCircle); //on ajoute le cercle pour pouvoir l'afficher
		});

		//Systeme serpent bicolore
		if(length % 4 < 2){
			firstCircle.setFill(Color.GREEN);
		}
		else{
			firstCircle.setFill(Color.LIGHTGREEN);
		}

		body.add(length++,firstCircle);
	}


	public Direction getDirection () {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getLength() {

		return length;
	}
	
	public Circle getSnakeHead() {
		return body.get(0);
	}

	public List<Circle> getBody() {
		return body;
	}
}
