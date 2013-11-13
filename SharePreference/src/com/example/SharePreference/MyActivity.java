package com.example.SharePreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity
{
    EditText edString;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        edString = (EditText) findViewById(R.id.edString);

        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(this);
        String savingString = setting.getString("savingString", " ");
        edString.setText(savingString);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = setting.edit();
        List<Person> list=new ArrayList<Person>();
        editor.putString("savingString", edString.getText().toString());
        editor.commit();
    }
}
