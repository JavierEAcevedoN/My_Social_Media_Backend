package c3.msmb.exceptions.user;

import jakarta.persistence.EntityNotFoundException;

public class PatchUserException extends EntityNotFoundException{
    public PatchUserException(String message) {
        super(message);
    }
}