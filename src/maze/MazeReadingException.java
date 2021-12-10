package maze;

public class MazeReadingException extends Exception {

    private final String message;
    private final String fileName;
    private final int line;
    
    public MazeReadingException(String message,String fileName, int line) {
        super();
        this.message = message;
        this.fileName = fileName;
        this.line = line;
    }
    
    @Override
    public final String toString()
    {
        return String.format("%s, line %d: %s",fileName,line,message);
    }

}
