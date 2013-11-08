package com.qsoft.OnlineDio.Model;

import android.content.ContentValues;
import android.database.Cursor;
import com.qsoft.OnlineDio.DB.DbHelper;

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
    private String country_id;
    private int storage_plan_id;
    private String description;
    private String created_at;
    private String updated_at;
    private int sounds;
    private int favorites;
    private int likes;
    private int followings;
    private int audiences;

    public Profile(){}
    public Profile(int facebook_id, String username, String password, String avatar, String cover_image, String display_name, String full_name, String phone, String birthday, int gender, String country_id, int storage_plan_id, String description, String created_at, String updated_at, int sounds, int favorites, int likes, int followings, int audiences)
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Profile profile = (Profile) o;

        if (audiences != profile.audiences)
        {
            return false;
        }
        if (facebook_id != profile.facebook_id)
        {
            return false;
        }
        if (favorites != profile.favorites)
        {
            return false;
        }
        if (followings != profile.followings)
        {
            return false;
        }
        if (gender != profile.gender)
        {
            return false;
        }
        if (id != profile.id)
        {
            return false;
        }
        if (likes != profile.likes)
        {
            return false;
        }
        if (sounds != profile.sounds)
        {
            return false;
        }
        if (storage_plan_id != profile.storage_plan_id)
        {
            return false;
        }
        if (!avatar.equals(profile.avatar))
        {
            return false;
        }
        if (!birthday.equals(profile.birthday))
        {
            return false;
        }
        if (!country_id.equals(profile.country_id))
        {
            return false;
        }
        if (!cover_image.equals(profile.cover_image))
        {
            return false;
        }
        if (!created_at.equals(profile.created_at))
        {
            return false;
        }
        if (!description.equals(profile.description))
        {
            return false;
        }
        if (!display_name.equals(profile.display_name))
        {
            return false;
        }
        if (!full_name.equals(profile.full_name))
        {
            return false;
        }
        if (!password.equals(profile.password))
        {
            return false;
        }
        if (!phone.equals(profile.phone))
        {
            return false;
        }
        if (!updated_at.equals(profile.updated_at))
        {
            return false;
        }
        if (!username.equals(profile.username))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + facebook_id;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + avatar.hashCode();
        result = 31 * result + cover_image.hashCode();
        result = 31 * result + display_name.hashCode();
        result = 31 * result + full_name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + gender;
        result = 31 * result + country_id.hashCode();
        result = 31 * result + storage_plan_id;
        result = 31 * result + description.hashCode();
        result = 31 * result + created_at.hashCode();
        result = 31 * result + updated_at.hashCode();
        result = 31 * result + sounds;
        result = 31 * result + favorites;
        result = 31 * result + likes;
        result = 31 * result + followings;
        result = 31 * result + audiences;
        return result;
    }

    public static Profile fromCursor(Cursor cursorProfile)
    {
        cursorProfile.moveToFirst();
        int facebook_id=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_FACEBOOK_ID));
        String username=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_USERNAME));
        String password=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_PASSWORD));
        String avatar=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_AVATAR));
        String cover_image=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_COVER_IMAGE));
        String display_name=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_DISPLAY_NAME));
        String full_name=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_FULL_NAME));
        String phone=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_PHONE));
        String birthday=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_BIRTHDAY));
        int gender=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_GENDER));
        String country_id=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_COUNTRY_ID));
        int storage_plan_id=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_STORAGE_PLAN_ID));
        String description=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_DESCRIPTION));
        String created_at=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_CREATE_AT));
        String updated_at=cursorProfile.getString(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_UPDATE_AT));
        int sounds=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_SOUNDS));
        int favorites=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_FAVORITES));
        int likes=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_LIKES));
        int followings=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_FOLLOWINGS));
        int audiences=cursorProfile.getInt(cursorProfile.getColumnIndex(DbHelper.PROFILE_COL_AUDIENCES));
        return new Profile(facebook_id,username,password,avatar,cover_image,display_name,full_name,phone,birthday,gender,country_id,storage_plan_id,description,created_at,updated_at,sounds,favorites,likes,followings,audiences);
    }
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DbHelper.PROFILE_COL_FACEBOOK_ID, facebook_id);
        values.put(DbHelper.PROFILE_COL_USERNAME, username);
        values.put(DbHelper.PROFILE_COL_PASSWORD, password);
        values.put(DbHelper.PROFILE_COL_AVATAR, avatar);
        values.put(DbHelper.PROFILE_COL_COVER_IMAGE, cover_image);
        values.put(DbHelper.PROFILE_COL_DISPLAY_NAME, display_name);
        values.put(DbHelper.PROFILE_COL_FULL_NAME, full_name);
        values.put(DbHelper.PROFILE_COL_PHONE, phone);
        values.put(DbHelper.PROFILE_COL_BIRTHDAY, birthday);
        values.put(DbHelper.PROFILE_COL_GENDER, gender);
        values.put(DbHelper.PROFILE_COL_COUNTRY_ID, country_id);
        values.put(DbHelper.PROFILE_COL_STORAGE_PLAN_ID, storage_plan_id);
        values.put(DbHelper.PROFILE_COL_DESCRIPTION, description);
        values.put(DbHelper.PROFILE_COL_CREATE_AT, created_at);
        values.put(DbHelper.PROFILE_COL_UPDATE_AT, updated_at);
        values.put(DbHelper.PROFILE_COL_SOUNDS, sounds);
        values.put(DbHelper.PROFILE_COL_FAVORITES, favorites);
        values.put(DbHelper.PROFILE_COL_LIKES, likes);
        values.put(DbHelper.PROFILE_COL_FOLLOWINGS, followings);
        values.put(DbHelper.PROFILE_COL_AUDIENCES, audiences);
        return values;
    }

    //setter & getter

    public void setId(int id)
    {
        this.id = id;
    }

    public void setFacebook_id(int facebook_id)
    {
        this.facebook_id = facebook_id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public void setCover_image(String cover_image)
    {
        this.cover_image = cover_image;
    }

    public void setDisplay_name(String display_name)
    {
        this.display_name = display_name;
    }

    public void setFull_name(String full_name)
    {
        this.full_name = full_name;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    public void setCountry_id(String country_id)
    {
        this.country_id = country_id;
    }

    public void setStorage_plan_id(int storage_plan_id)
    {
        this.storage_plan_id = storage_plan_id;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at)
    {
        this.updated_at = updated_at;
    }

    public void setSounds(int sounds)
    {
        this.sounds = sounds;
    }

    public void setFavorites(int favorites)
    {
        this.favorites = favorites;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public void setFollowings(int followings)
    {
        this.followings = followings;
    }

    public void setAudiences(int audiences)
    {
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

    public String getCountry_id()
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
