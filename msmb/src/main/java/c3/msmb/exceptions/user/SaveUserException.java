package c3.msmb.exceptions.user;

public class SaveUserException extends RuntimeException {
    public SaveUserException(String mensaje) {
        super(mensaje);
    }
}