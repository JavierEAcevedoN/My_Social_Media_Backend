package c3.msmb.exceptions.publication;

public class PublicationByIdException extends RuntimeException {
    public PublicationByIdException(String message) {
        super(message);
    }
}