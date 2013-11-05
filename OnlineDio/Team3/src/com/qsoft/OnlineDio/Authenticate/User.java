package com.qsoft.OnlineDio.Authenticate;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/29/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable
{

    private String access_token;
    private String client_id;
    private String user_id;
    private String expires;
    private String scope;

    public String getAccess_token()
    {
        return access_token;
    }

    public void setAccess_token(String access_token)
    {
        this.access_token = access_token;
    }

    public String getClient_id()
    {
        return client_id;
    }

    public void setClient_id(String client_id)
    {
        this.client_id = client_id;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public void setUser_id(String user_id)
    {
        this.user_id = user_id;
    }

    public String getExpires()
    {
        return expires;
    }

    public void setExpires(String expires)
    {
        this.expires = expires;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }
}