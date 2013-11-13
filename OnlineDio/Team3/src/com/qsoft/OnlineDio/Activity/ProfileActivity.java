package com.qsoft.OnlineDio.Activity;

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.googlecode.androidannotations.annotations.*;
import com.qsoft.OnlineDio.DB.GenericContract;
import com.qsoft.OnlineDio.ImageCache.Image;
import com.qsoft.OnlineDio.Model.Profile;
import com.qsoft.OnlineDio.R;
import com.qsoft.OnlineDio.SyncAdapter.ParseServerAccessor;
import com.qsoft.OnlineDio.Util.ConvertImage;
import com.qsoft.OnlineDio.Util.STATUS;

/**
 * User: Dell 3360
 * Date: 10/17/13
 * Time: 8:39 AM
 */
@EActivity(R.layout.profile_layout)
public class ProfileActivity extends Activity
{
    @ViewById(R.id.pr_imgAvatar)
    ImageView pr_imgAvatar;

    @ViewById(R.id.pr_ivCoverImage)
    ImageView pr_ivCoverImage;

    @ViewById(R.id.dg_btTakePicture)
    Button btTakePicture;

    @ViewById(R.id.dg_btChoosePicture)
    Button btChoosePicture;

    @ViewById(R.id.pr_btCancel)
    Button btCancel;

    @ViewById(R.id.pr_btnSelectLeft_check)
    Button btGenderSelectLeft;

    @ViewById(R.id.pr_btnSelectRight_check)
    Button btGenderSelectRight;

    @ViewById(R.id.pr_btCancel)
    Button pr_btCancel;

    @ViewById(R.id.pr_btSave)
    Button pr_btSave;

    @ViewById(R.id.pr_edFullName)
    EditText pr_edFullName;

    @ViewById(R.id.pr_edPhone)
    EditText pr_edPhone;

    @ViewById(R.id.pr_edBirthday)
    EditText pr_edBirthday;

    @ViewById(R.id.pr_edCountry)
    EditText pr_edCountry;

    @ViewById(R.id.pr_edDisplayName)
    EditText pr_edDisplayName;

    @ViewById(R.id.pr_edDesciprtion)
    EditText pr_edDescription;

    @ViewById(R.id.pr_ibDeleteFullName)
    ImageButton pr_ibDeleteFullName;

    @ViewById(R.id.pr_ibDeletePhone)
    ImageButton pr_ibDeletePhone;

    @ViewById(R.id.pr_rlBackGround)
    RelativeLayout pr_rlBackGround;

    AlertDialog alertDialog, alertCountryDialog;

    private String userId;
    private Account mConnectAccount;
    private static final String TAG = "ProfileSyncAdapter";
    public String[] countries;
    String[] country_codes;
    private Boolean isCheckedRdbLeft = false;
    private ConvertImage convertImage = new ConvertImage();

    static final int DATE_DIALOG_ID = 0;
    private static final int PICK_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    public int year, month, day;
    final Context context = this;
    private String token;
    private String code;
    private String status;

    @AfterViews
    void afterViews()
    {
        Bundle b = getIntent().getExtras();
        token = b.getString(FirstLaunchActivity.AUTHEN_TOKEN);
        userId = b.getString(LoginActivity.USER_ID);
        mConnectAccount = b.getParcelable(FirstLaunchActivity.ACCOUNT_CONNECTED);
        try
        {
            connectToProfileServer(token, STATUS.INSERT.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void bindDataToProfileView(Profile p)
    {
        countries = getResources().getStringArray(R.array.country_array);
        country_codes = getResources().getStringArray(R.array.country_code);

        pr_edFullName.setText(p.getFull_name());
        pr_edPhone.setText(p.getPhone());
        pr_edBirthday.setText(p.getBirthday());

        int index = 0;
        for (int i = 0; i < country_codes.length; i++)
        {
            if (p.getCountry_id().equals(country_codes[i]))
            {
                index = i;
                break;
            }
        }
        pr_edCountry.setText(countries[index]);
        if (p.getGender() == 1)
        {
            btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_left));
            btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_right));
        }
        else
        {
            btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_left));
            btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_right));
        }
        pr_edDisplayName.setText(p.getDisplay_name());
        pr_edDescription.setText(p.getDescription());

        Image imageCache = new Image(context);
        imageCache.DisplayImage(p.getAvatar(), pr_imgAvatar);
        imageCache.DisplayImage(p.getCover_image(), pr_ivCoverImage);
    }

    @Click({R.id.pr_imgAvatar, R.id.pr_rlBackGround, R.id.pr_ibDeleteFullName,
            R.id.pr_ibDeletePhone, R.id.pr_edBirthday, R.id.pr_edCountry, R.id.pr_btnSelectLeft_check,
            R.id.pr_btnSelectRight_check, R.id.pr_btCancel, R.id.pr_btSave})
    public void onClickListener(View view)
    {
        switch (view.getId())
        {
            case R.id.pr_imgAvatar:
                code = "cover_image";
                showDialogSelectImage();
                break;
            case R.id.pr_rlBackGround:
                code = "background_image";
                showDialogSelectImage();
                break;
            case R.id.pr_ibDeleteFullName:
                pr_edFullName.setText("");
                pr_ibDeleteFullName.setVisibility(View.INVISIBLE);
                break;
            case R.id.pr_ibDeletePhone:
                pr_edPhone.setText("");
                pr_ibDeletePhone.setVisibility(View.INVISIBLE);
                break;
            case R.id.pr_edBirthday:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.pr_edCountry:
                showDialogCountry();
                break;
            case R.id.pr_btnSelectLeft_check:
                isCheckedRdbLeft = true;
                btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_left));
                btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_right));
                break;
            case R.id.pr_btnSelectRight_check:
                isCheckedRdbLeft = false;
                btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_left));
                btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_right));
                break;
            case R.id.pr_btCancel:
                Intent i = new Intent(ProfileActivity.this, SlidebarActivity_.class);
                Bundle b = new Bundle();
                b.putString(FirstLaunchActivity.AUTHEN_TOKEN, token);
                b.putString(LoginActivity.USER_ID, userId);
                b.putParcelable(FirstLaunchActivity.ACCOUNT_CONNECTED, mConnectAccount);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.pr_btSave:
                updateProfileToServer();
                break;
        }
    }


    private void updateProfileToServer()
    {
        try
        {
            connectToProfileServer(token, STATUS.UPDATE.toString());
        }
        catch (Exception e)

        {
            e.printStackTrace();
        }

    }

    private Profile getProfileFromView(Profile p)
    {
        p.setDisplay_name(pr_edDisplayName.getText().toString());
        p.setFull_name(pr_edFullName.getText().toString());
        p.setPhone(pr_edPhone.getText().toString());
        p.setBirthday(pr_edBirthday.getText().toString());
        if (isCheckedRdbLeft == true)
        {
            p.setGender(1);
        }
        else
        {
            p.setGender(2);
        }
        int index = 0;
        for (int i = 0; i < countries.length; i++)
        {
            if (pr_edCountry.getText().toString().equals(countries[i]))
            {
                index = i;
                break;
            }
        }
        p.setDescription(pr_edDescription.getText().toString());
        p.setCountry_id(country_codes[index]);

        return p;
    }

    @AfterTextChange({R.id.pr_edFullName, R.id.pr_edPhone})
    void afterTextChangedOnTextViews(Editable s)
    {
        if (!pr_edFullName.getText().equals(" "))
        {
            pr_ibDeleteFullName.setVisibility(View.VISIBLE);
        }
        if (!pr_edPhone.getText().equals(" "))
        {
            pr_ibDeletePhone.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private void showDialogCountry()
    {
        final String[] countries = getResources().getStringArray(
                R.array.country_array);
        final String[] country_code = getResources().getStringArray(
                R.array.country_code);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select Country");

        builder.setSingleChoiceItems(countries, -1, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int item)
            {
                pr_edCountry.setText(countries[item].toString());
                alertCountryDialog.dismiss();
            }
        });
        alertCountryDialog = builder.create();
        alertCountryDialog.show();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener()
    {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay)
        {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            pr_edBirthday.setText(new StringBuilder().append(month + 1)
                    .append("/").append(day).append("/").append(year).append(" "));

        }
    };

    private void showDialogSelectImage()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View yourCustomView = inflater.inflate(R.layout.dg_choose_image_fragment, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(yourCustomView);
        alertDialog = builder.create();
        alertDialog.setTitle("Profile Image");
        alertDialog.show();
        btTakePicture = (Button) yourCustomView.findViewById(R.id.dg_btTakePicture);
        btChoosePicture = (Button) yourCustomView.findViewById(R.id.dg_btChoosePicture);
        btCancel = (Button) yourCustomView.findViewById(R.id.dg_btCancel);

        btTakePicture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                takePhoto(view);
            }
        });
        btChoosePicture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                alertDialog.cancel();
            }
        });
    }

    public void takePhoto(View v)
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap circleBitmap;
        if (requestCode == CAMERA_REQUEST)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            circleBitmap = convertImage.resizeBitMap(bitmap);
            if (code.equals("cover_image"))
            {
                pr_imgAvatar.setImageBitmap(circleBitmap);
            }
            else if (code.equals("background_image"))
            {
                Drawable cover = new BitmapDrawable(bitmap);
                pr_rlBackGround.setBackgroundDrawable(cover);
            }

        }
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bmp = BitmapFactory.decodeFile(filePath);
            circleBitmap = convertImage.resizeBitMap(bmp);
            if (code.equals("cover_image"))
            {
                pr_imgAvatar.setImageBitmap(circleBitmap);
            }
            else if (code.equals("background_image"))
            {
                Drawable cover = new BitmapDrawable(bmp);
                pr_rlBackGround.setBackgroundDrawable(cover);
            }

        }
        alertDialog.cancel();
    }

    @Background
    void connectToProfileServer(String token, String _status)
    {
        this.status = _status;
        executeProfile(token, status);
        getResult();
    }

    @UiThread
    void getResult()
    {
        Profile p = readFromContentProvider();
        if (p != null)
        {
            bindDataToProfileView(p);
        }
        if (status.equals(STATUS.UPDATE.toString()))
        {
            Toast.makeText(ProfileActivity.this, "Update Profile success !!!", Toast.LENGTH_LONG).show();
        }

    }

    private void executeProfile(String token, String status)
    {
        try
        {
            //get Profile from server

            ParseServerAccessor parseServerAccessor = new ParseServerAccessor();
            Profile profileGetFromServer = parseServerAccessor.getProfile(Integer.parseInt(userId), token);

            //get Profile from local
            Profile localProfile = null;

            Cursor cursorProfile = getContentResolver().query(GenericContract.PROFILE_CONTENT_URI, null, null, null, null);

            ContentValues showToLocalValues;
            if (cursorProfile != null && cursorProfile.getCount() > 0)
            {
                localProfile = Profile.fromCursor(cursorProfile);
                cursorProfile.close();

                //update lai local
                if (profileGetFromServer.equals(localProfile))
                {
                    Log.d("profile", TAG + "> No server changes to update local database");
                }
                else
                {
                    if (status.equals(STATUS.INSERT.toString()))
                    {
                        Log.d("profile", TAG + "> Updating local database with remote changes");
                        //updating local profile
                        int i = 0;

                        showToLocalValues = profileGetFromServer.getContentValues();
                        getContentResolver().update(GenericContract.PROFILE_CONTENT_URI, showToLocalValues, null, null);
                    }
                    else if (status.equals(STATUS.UPDATE.toString()))
                    {
                        Log.d("profile", TAG + "> Updating  remote changes");
                        //put to remote profile
                        Profile profile = getProfileFromView(localProfile);
                        ParseServerAccessor parseServer = new ParseServerAccessor();
                        parseServer.putProfile(token, userId, profile);
                        //update lai local
                        showToLocalValues = profile.getContentValues();
                        getContentResolver().update(GenericContract.PROFILE_CONTENT_URI, showToLocalValues, null, null);

                    }
                }
            }
            else
            {
                //insert new
                showToLocalValues = profileGetFromServer.getContentValues();
                getContentResolver().insert(GenericContract.PROFILE_CONTENT_URI, showToLocalValues);
            }


            Log.d("profile", TAG + "> Finished.");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private Profile readFromContentProvider()
    {
        Cursor curProfile = getContentResolver().query(GenericContract.PROFILE_CONTENT_URI, null, null, null, null);
        Profile _profile = null;
        if (curProfile != null)
        {
            _profile = Profile.fromCursor(curProfile);
            curProfile.close();
        }
        return _profile;
    }
}