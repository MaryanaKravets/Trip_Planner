package edu.project2.tripplanner.exception;

public interface Message {

     String USER_N_F="User with id '%s' not found";

     String COMMENT_USER_PLACE_N_F="Comment with id '%s' not found, or user with id '%s' not found, or place with id '%s' not found";

     String USER_PLACE_N_F="User with id '%s' not found, or place with id '%s' not found";

     String PLACE_N_F="Place with id '%s' not found";

     String TRIP_USER_N_F="Trip with id '%s' not found, or user with id '%s' not found";

     String COMMENT_USER_N_F="Comment with id '%s' not found, or user with id '%s' not found";
}
