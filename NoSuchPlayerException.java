package mancala;

public class NoSuchPlayerException extends Exception{

    public NoSuchPlayerException(String message){

        System.out.println(message);
        
    }

    public NoSuchPlayerException(){

        super("No such player");

    }
}