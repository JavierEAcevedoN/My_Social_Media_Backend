package c3.msmb.exceptions.user;

import jakarta.persistence.EntityNotFoundException;

public class GetByUsernameException extends EntityNotFoundException{
    public GetByUsernameException(String mensaje) {
        super(mensaje);
    }
}