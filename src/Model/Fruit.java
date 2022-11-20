package Model;

public class Fruit extends Spawnable{
    private int position;
    private int lengthIncrease;
    public Fruit(int position, int lengthIncrease){
        this.position = position;
        this.lengthIncrease = lengthIncrease;
    }

    public int getLengthIncrease() {
        return lengthIncrease;
    }
}
