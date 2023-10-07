package Matrices;

public class IncorrectRowSizeException extends Exception {
    public IncorrectRowSizeException(){
        super("The provided row size does not match with the Matrix instance column numbers.");
    }

}
