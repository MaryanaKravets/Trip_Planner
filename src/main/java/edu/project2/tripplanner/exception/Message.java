package edu.project2.tripplanner.exception;

public interface Message {

    String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User with id '%s' not found";

    String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "User with name '%s' not found";

    String COMMENT_USER_PLACE_NOT_FOUND_EXCEPTION_MESSAGE = "Comment with id '%s' not found, or user with id '%s' not found, or place with id '%s' not found";

    String USER_PLACE_NOT_FOUND_EXCEPTION_MESSAGE = "User with id '%s' not found, or place with id '%s' not found";

    String PLACE_NOT_FOUND_EXCEPTION_MESSAGE = "Place with id '%s' not found";

    String TRIP_USER_NOT_FOUND_EXCEPTION_MESSAGE = "Trip with id '%s' not found, or user with id '%s' not found";

    String COMMENT_USER_NOT_FOUND_EXCEPTION_MESSAGE = "Comment with id '%s' not found, or user with id '%s' not found";
}
