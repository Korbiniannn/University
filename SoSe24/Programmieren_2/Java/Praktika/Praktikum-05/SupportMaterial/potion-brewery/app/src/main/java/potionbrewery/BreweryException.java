package potionbrewery;

public class BreweryException extends RuntimeException{

    public BreweryException(String message){
        super(message);
    }

    public BreweryException(Throwable cause){
        super(cause);
    }

    public BreweryException(String message, Throwable cause){
        super(message, cause);
    }
}
