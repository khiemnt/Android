package com.qsoft.OnlineDio.Validate;

public enum Constant
{
    EMAIL("test@gmail.com"),
    PASSWORD("123456"),
    NOT_CONNECTED_TO_INTERNET("Not connected to Internet"),
    TITLE_MESSAGE("Error Signing In"),
    MESSAGE_CONNECTION_INTERNET("There is no connection to the internet"),
    EMAIL_INVALID("Invalid email address"),
    EMAIL_OR_PASSWORD_NOT_CORRECT("Email address or password is incorrect.");

    private String value;

    public String getValue()
    {
        return value;
    }

    Constant(String value)
    {
        this.value = value;
    }
}