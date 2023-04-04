package battleship;

public abstract class Ship {
    private final int length;
    private final int points;
    private final Field field;
    private final char letter;
    
    private Location start;
    private ShipDirection dir;

    public Ship(int length, int points, Field field, char letter) {
        strength = length;
        this.length = length;
        this.points = points;
        this.field = field;
        this.letter = letter;
    }
    public void hit(){
        strength--;
    }
    private int strength ;

    public int getLength() {
        return length;
    }

    public int getPoints() {
        return points;
    }

    public Field getField() {
        return field;
    }

    public char getLetter() {
        return letter;
    }

    public Location getStart() {
        return start;
    }

    public ShipDirection getDir() {
        return dir;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public void setDir(ShipDirection dir) {
        this.dir = dir;
    }
    
    public boolean isSinking() {
        return strength == 0;
    }
    
    public abstract String getSinkMessage();
    
    public String getHitMessage(){
        return "A ship is hit!";
    }
}
