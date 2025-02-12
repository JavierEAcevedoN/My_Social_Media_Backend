package c3.msmb.exceptions.user;

public class GetUsersException extends RuntimeException{
    public GetUsersException(String mensaje) {
        super(mensaje);
    }
}