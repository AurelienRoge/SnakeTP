package Model;


public class SnakeElement {
    int position;
    String direction = "Left";

    public SnakeElement(int position){
        this.position = position;

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
