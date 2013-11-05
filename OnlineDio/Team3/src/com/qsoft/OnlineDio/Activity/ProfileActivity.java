package com.qsoft.OnlineDio.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.qsoft.OnlineDio.Fragment.HomeFragment;
import com.qsoft.OnlineDio.Model.Profile;
import com.qsoft.OnlineDio.R;
import com.qsoft.OnlineDio.SyncAdapter.ParseServerAccessor;

import java.util.List;

/**
 * User: Dell 3360
 * Date: 10/17/13
 * Time: 8:39 AM
 */
public class ProfileActivity extends Activity
{
    ImageView pr_imgAvatar, img;
    Button btTakePicture, btChoosePicture, btCancel, btGenderSelectLeft, btGenderSelectRight, pr_btCancel;

    EditText pr_edFullName, pr_edPhone, pr_edBirthday, pr_edCountry,pr_edDisplayName,pr_edDescription;
    ImageButton pr_ibDeleteFullName, pr_ibDeletePhone;
    RelativeLayout pr_rlBackGround;
    AlertDialog alertDialog, alertCountryDialog;
    private Profile profile;
    private String userId;
    private ListView listCountry;
    private List<HomeFragment> rowItems;

    static final int DATE_DIALOG_ID = 0;
    private static final int PICK_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    public int year, month, day;
    final Context context = this;

    String code;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        Bundle b = getIntent().getExtras();
        String token = b.getString("token");
        userId=b.getString("user_id");
        getComponentOnView();
        onclickListener();

        try
        {
          new Connection().execute(token);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void getComponentOnView()
    {
        pr_edDisplayName =(EditText)findViewById(R.id.pr_edDisplayName);
        pr_imgAvatar = (ImageView) findViewById(R.id.pr_imgAvatar);
        pr_edFullName = (EditText) findViewById(R.id.pr_edFullName);
        pr_edPhone = (EditText) findViewById(R.id.pr_edPhone);
        pr_ibDeleteFullName = (ImageButton) findViewById(R.id.pr_ibDeleteFullName);
        pr_ibDeletePhone = (ImageButton) findViewById(R.id.pr_ibDeletePhone);
        pr_edBirthday = (EditText) findViewById(R.id.pr_edBirthday);
        pr_edCountry = (EditText) findViewById(R.id.pr_edCountry);
        btGenderSelectLeft = (Button) findViewById(R.id.pr_btnSelectLeft_check);
        btGenderSelectRight = (Button) findViewById(R.id.pr_btnSelectRight_uncheck);
        pr_rlBackGround = (RelativeLayout) findViewById(R.id.pr_rlBackGround);
        pr_btCancel = (Button) findViewById(R.id.pr_btCancel);
        pr_edDescription=(EditText)findViewById(R.id.pr_edDesciprtion);
    }
    private void BindDataToProfileView(Profile p)
    {
        final String[] countries = getResources().getStringArray(
                R.array.country_array);

        pr_edFullName.setText(p.getFull_name());
        pr_edPhone.setText(p.getPhone());
        pr_edBirthday.setText(p.getBirthday());
        pr_edCountry.setText(countries[p.getCountry_id()].toString());
        if(p.getGender()==1){
            btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_left));
            btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_right));
        } else{
            btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_left));
            btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_right));
        }
        pr_edDisplayName.setText(p.getDisplay_name());
        pr_edDescription.setText(p.getDescription());
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
                    btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_left));
                    btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_right));
                    break;
                case R.id.pr_btnSelectRight_uncheck:
                    btGenderSelectLeft.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_unselect_left));
                    btGenderSelectRight.setBackgroundDrawable(getResources().getDrawable(R.drawable.pr_btn_select_right));
                    break;
                case R.id.pr_btCancel:
                    startActivity(new Intent(ProfileActivity.this, SlidebarActivity.class));
                    break;
            }
        }
    };

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
        private class Connection extends AsyncTask<String,Void,Profile>
    {


        @Override
        protected Profile doInBackground(String... param)
        {
            profile = null;
            try
            {
                //get userID of connectAccount
//                ParseComServerAuthenticate parseComServer=new ParseComServerAuthenticate();
//                parseComServer.userSignIn()
                ParseServerAccessor parseServerAccessor=new ParseServerAccessor();
                profile=  parseServerAccessor.getProfile(Integer.parseInt(userId), param[0]) ;

            }
            catch (Exception e)
            {
                if( e.getMessage().equals("cannot access my apis")){
                    //Todo: token expire->call login
                }

            }
            return profile;
        }

        @Override
        protected void onPostExecute(Profile p)
        {
            super.onPostExecute(p);
            if(p!=null){
                BindDataToProfileView(p);
            }

        }


    }

}