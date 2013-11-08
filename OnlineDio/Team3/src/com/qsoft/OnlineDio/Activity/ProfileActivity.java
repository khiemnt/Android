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
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.qsoft.OnlineDio.DB.GenericContract;
import com.qsoft.OnlineDio.ImageCache.Image;
import com.qsoft.OnlineDio.Model.Profile;
import com.qsoft.OnlineDio.R;
import com.qsoft.OnlineDio.SyncAdapter.ParseServerAccessor;
import com.qsoft.OnlineDio.Util.STATUS;

/**
 * User: Dell 3360
 * Date: 10/17/13
 * Time: 8:39 AM
 */
public class ProfileActivity extends Activity
{
    ImageView pr_imgAvatar;
    Button btTakePicture, btChoosePicture, btCancel, btGenderSelectLeft, btGenderSelectRight, pr_btCancel, pr_btSave;

    EditText pr_edFullName, pr_edPhone, pr_edBirthday, pr_edCountry, pr_edDisplayName, pr_edDescription;
    ImageButton pr_ibDeleteFullName, pr_ibDeletePhone;
    RelativeLayout pr_rlBackGround;
    AlertDialog alertDialog, alertCountryDialog;
    private String userId;
    private Account mConnectAccount;
    private static final String TAG = "ProfileSyncAdapter";
    public String[] countries;
    String[] country_codes;
    private Boolean isCheckedRdbLeft = false;

    static final int DATE_DIALOG_ID = 0;
    private static final int PICK_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    public int year, month, day;
    final Context context = this;
    String token;
    String code;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        userId = b.getString("user_id");
        mConnectAccount=b.getParcelable("connectAccount")   ;
        getComponentOnView();
        onclickListener();

        try
        {
            new Connection().execute(token, STATUS.INSERT.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void getComponentOnView()
    {
        pr_edDisplayName = (EditText) findViewById(R.id.pr_edDisplayName);
        pr_imgAvatar = (ImageView) findViewById(R.id.pr_imgAvatar);
        pr_edFullName = (EditText) findViewById(R.id.pr_edFullName);
        pr_edPhone = (EditText) findViewById(R.id.pr_edPhone);
        pr_ibDeleteFullName = (ImageButton) findViewById(R.id.pr_ibDeleteFullName);
        pr_ibDeletePhone = (ImageButton) findViewById(R.id.pr_ibDeletePhone);
        pr_edBirthday = (EditText) findViewById(R.id.pr_edBirthday);
        pr_edCountry = (EditText) findViewById(R.id.pr_edCountry);
        btGenderSelectLeft = (Button) findViewById(R.id.pr_btnSelectLeft_check);
        btGenderSelectRight = (Button) findViewById(R.id.pr_btnSelectRight_check);
        pr_rlBackGround = (RelativeLayout) findViewById(R.id.pr_rlBackGround);
        pr_btCancel = (Button) findViewById(R.id.pr_btCancel);
        pr_edDescription = (EditText) findViewById(R.id.pr_edDesciprtion);
        pr_btSave = (Button) findViewById(R.id.pr_btSave);
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

        Image imageCache=new Image(context);
        imageCache.DisplayImage(p.getAvatar(),pr_imgAvatar);
      //  imageCache
    }

    private void onclickListener()
    {
        pr_imgAvatar.setOnClickListener(onclickListener);
        pr_edFullName.setOnClickListener(onclickListener);
        pr_edPhone.setOnClickListener(onclickListener);
        pr_edBirthday.setOnClickListener(onclickListener);
        pr_edCountry.setOnClickListener(onclickListener);
        btGenderSelectLeft.setOnClickListener(onclickListener);
        btGenderSelectRight.setOnClickListener(onclickListener);
        pr_rlBackGround.setOnClickListener(onclickListener);
        pr_btCancel.setOnClickListener(onclickListener);
        pr_btSave.setOnClickListener(onclickListener);
    }

    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
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
                case R.id.pr_edFullName:
                    onTextChange();
                    break;
                case R.id.pr_ibDeleteFullName:
                    pr_edFullName.setText("");
                    pr_ibDeleteFullName.setVisibility(View.INVISIBLE);
                    break;
                case R.id.pr_edPhone:
                    onTextChange();
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
                    Intent i=new Intent(ProfileActivity.this, SlidebarActivity.class);
                    Bundle b=new Bundle();
                    b.putString("token",token);
                    b.putString("user_id",userId);
                    b.putParcelable("connectAccount",mConnectAccount);
                    i.putExtras(b);
                    startActivity(i);
                    break;
                case R.id.pr_btSave:
                    updateProfileToServer();
                    break;
            }
        }
    };

    private void updateProfileToServer()
    {
        try
        {
            new Connection().execute(token, STATUS.UPDATE.toString());

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
        p.setCountry_id(country_codes[index]);
        p.setDescription(pr_edDescription.getText().toString());
        return p;
    }

    private void onTextChange()
    {
        //textChange edit text FullName
        pr_edFullName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                if (!pr_edFullName.getText().equals(" "))
                {
                    pr_ibDeleteFullName.setVisibility(View.VISIBLE);
                    pr_ibDeleteFullName.setOnClickListener(onclickListener);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        //textChange edit text Phone
        pr_edPhone.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                if (!pr_edPhone.getText().equals(" "))
                {
                    pr_ibDeletePhone.setVisibility(View.VISIBLE);
                    pr_ibDeletePhone.setOnClickListener(onclickListener);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });
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

        pr_edCountry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
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
        });
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

            circleBitmap = resizeBitMap(bitmap);
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
            circleBitmap = resizeBitMap(bmp);
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

    private Bitmap resizeBitMap(Bitmap bitmap)
    {
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        return circleBitmap;
    }

    private class Connection extends AsyncTask<String, Void, Void>
    {
        String status;

        @Override
        protected Void doInBackground(String... param)
        {
            status = param[1];
            executeProfile(param[0], param[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
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