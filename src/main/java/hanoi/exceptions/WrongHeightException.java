package hanoi.exceptions;

public class WrongHeightException extends Exception{
    public WrongHeightException(int height){
        super(String.format("Wrong height of the Hanoi Tower! The height should be greater than 0. Your height is %d", height));
    }
}
