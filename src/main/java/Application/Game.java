package Application;



import javafx.application.Application;
import javafx.application.Platform;
import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;


public class Game extends Application {
	//variables
	private static final int width = 500;
	private static final int height = 500;
	private static final int radius = 10;

	private Pane root;
	private Circle food;
	private Random random;
	private Snake snake;
	private final CollisionsManager collisionsManager = CollisionsManager.getCollisionsManager(height,width,radius);//Singleton gestionnaire de collisions
	
	public static int getWindowWidth() {
		return width;
	}
	
	public static int getWindowHeight() {
		return height;
	}
	
	//on crée des cercles que le serpent devra manger pour grandir
	private void newFood() {
		food = new Circle(random.nextInt(width),random.nextInt(height),radius);//creation de cercles à des positions randoms
		food.setFill(Color.RED); //on attribue la couleur rouge
		root.getChildren().add(food); //on ajoute le cercle pour pouvoir l'afficher
	}
	
	//On crée un nouveau serpent 
	private void newSnake() {
		snake = new Snake(width/2,height/2, radius+1);//on positionne le serpent au milieu
		root.getChildren().add(snake);//on ajoute le serpent pour pouvoir l'afficher
		snake.eat(food);
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
					snake.eat(food);//on fait disparaitre le cercle et on augmente la taille du serpent de 1
					newFood();//on génère un nouveau cercle
				}
			});
		}
		else {
			//System.out.println("Collision detected, can't move !");
		}
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
        			Thread.sleep(50);//thread qui permet de modifier la vitesse du jeu
        		}
    		}catch (InterruptedException ie) {
    			
    		}
    		
    	};
    	
    	Scene scene = new Scene(root);
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