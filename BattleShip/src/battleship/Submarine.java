package battleship;

public class Submarine extends Ship {

    public Submarine( Field field) {
        super(1, 3, field, 'S' );
    }
    
    
    public String getSinkMessage(){
        return "A submarine is sinking!";
    }
    /*public String getHitMessage(){
        return "A submarine is hitted!";
    }*/
}
