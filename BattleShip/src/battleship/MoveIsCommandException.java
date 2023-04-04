package battleship;

public class MoveIsCommandException extends InvalidLocationException{
    private Command cmd;
    
    public MoveIsCommandException(Command cmd){
        this.cmd=cmd;
    }
    
    public MoveIsCommandException(String msg ){
        super(msg);
    }
    
    public Command getCommand(){
        return cmd;
    }
}
