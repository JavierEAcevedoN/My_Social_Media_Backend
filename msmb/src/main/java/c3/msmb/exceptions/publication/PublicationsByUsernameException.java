package c3.msmb.exceptions.publication;

public class PublicationsByUsernameException extends RuntimeException{
    public PublicationsByUsernameException(String message) {
        super(message);
    }
}