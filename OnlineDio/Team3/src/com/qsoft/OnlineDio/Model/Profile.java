package com.qsoft.OnlineDio.Model;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 11/5/13
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class Profile
{
    private int id;
    private int facebook_id;
    private String username;
    private String password;
    private String avatar;
    private String cover_image;
    private String display_name;
    private String full_name;
    private String phone;
    private String birthday;
    private int gender;
    private int country_id;
    private int storage_plan_id;
    private String description;
    private String created_at;
    private String updated_at;
    private int sounds;
    private int favorites;
    private int likes;
    private int followings;
    private int audiences;

    public Profile(int facebook_id, String username, String password, String avatar, String cover_image, String display_name, String full_name, String phone, String birthday, int gender, int country_id, int storage_plan_id, String description, String created_at, String updated_at, int sounds, int favorites, int likes, int followings, int audiences)
    {
        this.facebook_id = facebook_id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.cover_image = cover_image;
        this.display_name = display_name;
        this.full_name = full_name;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.country_id = country_id;
        this.storage_plan_id = storage_plan_id;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.sounds = sounds;
        this.favorites = favorites;
        this.likes = likes;
        this.followings = followings;
        this.audiences = audiences;
    }

    public int getFacebook_id()
    {
        return facebook_id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public String getCover_image()
    {
        return cover_image;
    }

    public String getDisplay_name()
    {
        return display_name;
    }

    public String getFull_name()
    {
        return full_name;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public int getGender()
    {
        return gender;
    }

    public int getCountry_id()
    {
        return country_id;
    }

    public int getStorage_plan_id()
    {
        return storage_plan_id;
    }

    public String getDescription()
    {
        return description;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public String getUpdated_at()
    {
        return updated_at;
    }

    public int getSounds()
    {
        return sounds;
    }

    public int getFavorites()
    {
        return favorites;
    }

    public int getLikes()
    {
        return likes;
    }

    public int getFollowings()
    {
        return followings;
    }

    public int getAudiences()
    {
        return audiences;
    }
}
