package fe.orleans.webservices.livedemosecurity.config.Exceptions;

public class MauvaisTokenException extends Exception {
    public MauvaisTokenException() {
        super();
    }

    public MauvaisTokenException(String message) {
        super(message);
    }
}
