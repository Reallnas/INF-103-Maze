package tp07;

public class MazeReadingException extends Exception {

    private final String fileName;
    private final int errorLine;
    
    public MazeReadingException(String message,String fileName, int errorLine) {
        super(message);
        this.fileName = fileName;
        this.errorLine = errorLine;
    }

    
    
}
