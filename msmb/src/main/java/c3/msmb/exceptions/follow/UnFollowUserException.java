package c3.msmb.exceptions.follow;

import jakarta.persistence.EntityNotFoundException;

public class UnFollowUserException extends EntityNotFoundException{
    public UnFollowUserException(String message) {
        super(message);
    }
}