package Application;



import javafx.application.Application;
import javafx.application.Platform;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


public class Game extends Application {
	//variables
	private static final int width = 400;
	private static final int height = 400;
	private static final int radius = 12;

	private Pane root;
	private Circle food;
	private Random random;
	private Snake snake;
	private Scene scene;

	private Text scoreText;
	private Text bestScoreText;
	private final CollisionsManager collisionsManager = CollisionsManager.getCollisionsManager(height,width,radius);//Singleton gestionnaire de collisions
	private final FruitManager fruitManager = FruitManager.getFruitManager();//Singleton gestionnaire des fruits

	private final ScoreManager scoreManager = ScoreManager.getScoreManager();
	
	//on crée des cercles que le serpent devra manger pour grandir
	private void newFood() {
		fruitManager.generateNewFruit();
		food = new Circle(random.nextInt(width),random.nextInt(height),radius);//creation de cercles à des positions randoms
		food.setFill(fruitManager.getFruitColor()); //on attribue la couleur du fruit actuel
		root.getChildren().add(food); //on ajoute le cercle pour pouvoir l'afficher
	}
	
	//On crée un nouveau serpent 
	private void newSnake() {
		snake = new Snake(width/2,height/2, radius+1);//on positionne le serpent au milieu
		root.getChildren().add(snake);//on ajoute le serpent pour pouvoir l'afficher
		snake.eat(food,"Apple", root, scoreManager);//On augmente sa taille de 1
		collisionsManager.setSnake(snake);//On met à jour le serpent dans le gestionnaire de collisions
		
	}
	
	//permet de savoir si on touche un cercle
	private boolean hit() {
		return food.intersects(snake.getBoundsInLocal());
	}
	
	//move
	private void move() {
		if(!collisionsManager.detectBorderCollisions() && !collisionsManager.detectSelfCollision()) {
			Platform.runLater(()->{
				snake.step();
				if(hit()) {
					snake.eat(food, fruitManager.getCurrentFruit(), root, scoreManager);//on fait disparaitre le cercle et on augmente la taille du serpent de 1
					newFood();//on génère un nouveau cercle
				}
			});
			displayScore();
		}
		else {
			restartGame();
		}
	}


	private void restartGame(){
		Platform.runLater(()->{
			root.getChildren().clear();//On supprime tous les éléments graphiques

		});

		food = new Circle(random.nextInt(width),random.nextInt(height),radius);//creation de cercles à des positions randoms
		food.setFill(Color.RED); //on attribue la couleur rouge

		Platform.runLater(()->{
			root.getChildren().add(food); //on ajoute le cercle pour pouvoir l'afficher.
		});

		snake = new Snake(width/2,height/2, radius+1);//on positionne le serpent au milieu

		Platform.runLater(()->{
			root.getChildren().add(snake);//on ajoute le serpent pour pouvoir l'afficher
		});
		snake.eat(food, "Apple", root, scoreManager);//On augmente sa taille de 1
		collisionsManager.setSnake(snake);//On met à jour le serpent dans le gestionnaire de collisions

		scoreManager.resetScoreValue();//Reset le score
	}

	private void displayScore(){

		Text textToDelete = scoreText;
		Text textToDelete2 = bestScoreText;
		Platform.runLater(()->{
			if(textToDelete != null){
				root.getChildren().remove(textToDelete);
			}
			if(textToDelete2 != null){
				root.getChildren().remove(textToDelete2);
			}
		});


		scoreText = new Text(5,20,"Score:" + scoreManager.getScoreValue());
		scoreText.setFont(Font.font("Arial", 17));

		bestScoreText = new Text(5,40,"Best:" + scoreManager.getBestScore());
		bestScoreText.setFont(Font.font("Arial", 17));

		Platform.runLater(()->{
			if(!root.getChildren().contains(scoreText)){
				root.getChildren().add(scoreText);
			}
			if(!root.getChildren().contains(bestScoreText)){
				root.getChildren().add(bestScoreText);
			}
		});


	}
	
	
    @Override
    public void start(Stage primaryStage) {
    	
    	root = new Pane();
    	root.setPrefSize(width, height);
    	random = new Random();
    	
    	
    	newFood();
    	newSnake();
    	//on crée un thread
    	Runnable r = ()->{
    		try {
    			for(;;) {
        			move();
        			Thread.sleep(40);//thread qui permet de modifier la vitesse du jeu
        		}
    		}catch (InterruptedException ie) {
    			
    		}
    		
    	};
    	
    	scene = new Scene(root);
    	scene.setFill(Color.LIGHTGREY);
    	//controls
    	scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
    		KeyCode code = event.getCode();
    		if(code == KeyCode.UP || code == KeyCode.Z && snake.getDirection() != Direction.DOWN) {
    			snake.setDirection(Direction.UP);
    		}
    		if(code == KeyCode.DOWN || code == KeyCode.S && snake.getDirection() != Direction.UP) {
    			snake.setDirection(Direction.DOWN);
    		}
    		if(code == KeyCode.LEFT || code == KeyCode.Q && snake.getDirection() != Direction.RIGHT) {
    			snake.setDirection(Direction.LEFT);
    		}
    		if(code == KeyCode.RIGHT || code == KeyCode.D && snake.getDirection() != Direction.LEFT) {
    			snake.setDirection(Direction.RIGHT);
    		}
    	});
    	
    	primaryStage.setTitle("Snake");
    	primaryStage.setScene(scene);
    	primaryStage.setResizable(false);
    	primaryStage.show();
    	Thread th = new Thread(r);
    	th.setDaemon(true);
    	th.start();
    }

    public static void main(String[] args){
        launch(args);
    }
}