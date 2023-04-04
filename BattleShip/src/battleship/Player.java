package battleship;

public abstract class Player {
    private final String name;
    private Field field;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public int getScore() {
        return score;
    }
    
    public boolean hasWon(){
        return score == 22;
    }
    
    
    public void initField(int row, int col){
        field = new Field(row, col);
        field.setPlayer(this);
    }
    
    public void addToScore(int points){
        score += points;
        System.out.println("Your score is: " + score);
    }
    
    public abstract void placeShips(Field otherField);
    public abstract Location selectMove() throws MoveIsCommandException;
    public abstract void play();
}
