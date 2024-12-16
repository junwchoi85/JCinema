package com.jcinema.users.constants;

public final class UsersConstants {
    private UsersConstants() {
        // restrict instantiation
    }

    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_DELETED = "User deleted successfully";

    public static final String STATUS_200 = "200";
    public static final String MSG_200_UPDATE = "Request processed successfully";
    public static final String STATUS_201 = "201";
    public static final String MSG_201_CREATE = "User created successfully";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_CREATE = "Create operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
}
