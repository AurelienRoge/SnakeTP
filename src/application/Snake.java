package application;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake extends Circle{
	private List<Circle> tails;
	private int length = 0;
	private Direction direction;
	private static final int Step = 8;

	
	public Snake(double posX, double posY, double radius) {
		super(posX, posY, radius);
		tails = new ArrayList<>();
		direction = Direction.UP;
		this.setFill(Color.GREEN);
	}
	
	//on fait bouger tout le serpent
	public void step() {
		for(int i = length -1; i>=0;i--) {
			if(i == 0) {//Tête du serpent
				tails.get(i).setCenterX(getCenterX());
				tails.get(i).setCenterY(getCenterY());
			}
			else {//Suite du corps
				tails.get(i).setCenterX(tails.get(i-1).getCenterX());
				tails.get(i).setCenterY(tails.get(i-1).getCenterY());
			}
		}
		
		if(direction == Direction.UP) {
			setCenterY(getCenterY()-Step);
		}
		if(direction == Direction.DOWN) {
			setCenterY(getCenterY()+Step);
		}
		if(direction == Direction.LEFT) {
			setCenterX(getCenterX()-Step);
		}
		if(direction == Direction.RIGHT) {
			setCenterX(getCenterX()+Step);
		}
	}
	
	public Direction getDirection () {
		return direction;
	}
	
	public void setdirection(Direction direction) {
		this.direction = direction;
	}
	
	//permet de connaitre sa endofTail
	private Circle endofTail() {
		if(length == 0) {
			return this;
		}
		return tails.get(length - 1);
	}
	
	//permet d'agrandir le serpent quand il mange un cercle
	public void eat(Circle food) {
		Circle tail = endofTail();
		food.setCenterX(tail.getCenterX());
		food.setCenterY(tail.getCenterY());
		food.setFill(Color.GREEN);
		tails.add(length++,food);

	}


	public int getLength() {

		return length;
	}
	
	public Circle getSnakeHead() {
		return tails.get(0);
	}
}
