package battleship;

public class Destroyer extends Ship {

    public Destroyer( Field field) {
        super(3, 2,  field, 'D');
    }
    
    
    public String getSinkMessage(){
        return "A destroyer is sinking!";
    }
    /*public String getHitMessage(){
        return "A destroyer is hitted!";
    }*/
}
