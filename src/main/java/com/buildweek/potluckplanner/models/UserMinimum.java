package com.buildweek.potluckplanner.models;

/**
 * A model used to create a new user. The minimum information needed to create a user.
 * Note the role will default to USER.
 */
public class UserMinimum
{
    /**
     * The username (String)
     */
    private String username;

    /**
     * The user's password (String)
     */
    private String password;

    /**
     * The user's primary email address (String)
     */
    private String fullname;

    /**
     * Getter for the username
     *
     * @return the username (String) associated with this user
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Setter for the username
     *
     * @param username the new username (String) associated with this user
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Getter for the password of this user
     *
     * @return the password (String) for this user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter for the password of this user. This object is a temporary model used to create a new user.
     * The password must remain in clear text until saved into the database.
     *
     * @param password the new password (String in clear texts) for this user
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Getter for email for this user
     *
     * @return the email address (String) for this user
     */
    public String getFullname()
    {
        return fullname;
    }

    /**
     * Setter for email for this user
     *
     * @param fullname the new email address (String) for this user.
     */
    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }
}