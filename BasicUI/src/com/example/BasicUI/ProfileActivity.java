package com.example.BasicUI;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/9/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileActivity extends Activity
{
    private AlertDialog levelDialog;
    private TextView lblName;
    private TextView tvLogout;
    private TextView lblAddress;
    private TextView tvAddress;
    private TextView lblBirthday;
    private TextView tvBirthday;
    private TextView lblOnline;
    private TextView tvOnline;
    private TextView tvGender;
    private RadioGroup radioGroup;
    final Context context = this;

    private int mYear, mMonth, mDay, mHour, mMinute;
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;
    public int year, month, day, hour, minute;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        Intent i = getIntent();

        showUserName(i);
        showAddress();
        showBirthday();
        showOnlineTime();
        selectGender();
        eventLogout();


    }

    private void selectGender()
    {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        TextView lblGender = (TextView) findViewById(R.id.lblGender);
        lblGender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                radioGroup.setVisibility(view.getVisibility());
            }
        });
    }

    private void showUserName(Intent i)
    {
        String username = i.getStringExtra("username");

        lblName = (TextView) findViewById(R.id.lblName);
        lblName.setText("Hi, " + username);
    }

    private void eventLogout()
    {
        //logout
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void showOnlineTime()
    {
        //process Time
        lblOnline = (TextView) findViewById(R.id.lblOnline);
        tvOnline = (TextView) findViewById(R.id.tvOnline);
        lblOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                showDialog(TIME_DIALOG_ID);
            }
        });
    }

    private void showBirthday()
    {
        //   process birhtday
        lblBirthday = (TextView) findViewById(R.id.lblBirthday);
        tvBirthday = (TextView) findViewById(R.id.tvBirthday);
        lblBirthday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DATE_DIALOG_ID);

            }
        });
    }

    private void showAddress()
    {
        //         process address
        lblAddress = (TextView) findViewById(R.id.lblAddress);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        lblAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final CharSequence[] items = {" Ha Noi ", " Ha Nam ", " Ha Tay ", " Quang Ninh ", " Bac Ninh "};

                // Creating and Building the Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Address");
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int item)
                    {
                        switch (item)
                        {
                            case 0:
                                tvAddress.setText(items[0].toString());
                                break;
                            case 1:
                                tvAddress.setText(items[1].toString());
                                break;
                            case 2:
                                tvAddress.setText(items[2].toString());
                                break;
                            case 3:
                                tvAddress.setText(items[3].toString());
                                break;
                            case 4:
                                tvAddress.setText(items[4].toString());
                                break;

                        }
                        levelDialog.dismiss();
                    }
                });
                levelDialog = builder.create();
                levelDialog.show();
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
            // create a new TimePickerDialog with values you want to show
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinute, false);

        }
        return null;
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

            // set selected date into textview
            tvBirthday.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
        }
    };
    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener()
            {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min)
                {
                    String atClock = " AM";
                    hour = hourOfDay;
                    if (hourOfDay > 12)
                    {
                        hour = hourOfDay - 12;
                        atClock = " PM";
                    }

                    minute = min;
                    if (0 <= min && min < 10)
                    {
                        tvOnline.setText(hour + ": " + "0" + minute + atClock);
                    }
                    tvOnline.setText(hour + ": " + minute + atClock);
                }
            };

    public void onRbGenderClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        tvGender = (TextView) findViewById(R.id.tvGender);
        String radioButtonSelected = "";
        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.radio_male:
                if (checked)
                {
                    radioButtonSelected = "Male";
                }
                break;
            case R.id.radio_female:
                if (checked)
                {
                    radioButtonSelected = "Female";
                }

                break;
        }

        tvGender.setText(radioButtonSelected);
        radioGroup.setVisibility(View.INVISIBLE);
    }

    public void onCheckboxClicked(View view)
    {

        boolean checked = ((CheckBox) view).isChecked();
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String[]> arrayAdapter = null;
        final List<String[]> friendList = new LinkedList<String[]>();
        // Check which checkbox was clicked
        switch (view.getId())
        {
            case R.id.cboFriend:
                if (checked)
                {
                    listView.setVisibility(1);
                    friendList.add(new String[]{"Name: Peter", "Phone: 123456789"});
                    friendList.add(new String[]{"Name: Marry", "Phone: 789456123"});
                    friendList.add(new String[]{"Name: Peter", "Phone: 789456124"});
                    friendList.add(new String[]{"Name: Marry", "Phone: 789456125"});
                    arrayAdapter = new ArrayAdapter<String[]>(this, android.R.layout.simple_list_item_2, android.R.id.text1, friendList)
                    {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent)
                        {

                            View view = super.getView(position, convertView, parent);
                            String[] entry = friendList.get(position);
                            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                            TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                            text1.setText(entry[0]);
                            text1.setTextColor(Color.BLACK);
                            text2.setText(entry[1]);
                            text2.setTextColor(Color.BLACK);
                            return view;
                        }
                    };
                    listView.setAdapter(arrayAdapter);
                    break;
                }

                else
                {
                    listView.setAdapter(null);
                    break;
                }
        }
    }
}