package c3.msmb.exceptions.follow;

import jakarta.persistence.EntityNotFoundException;

public class FollowUserException extends EntityNotFoundException{
    public FollowUserException(String message) {
        super(message);
    }
}