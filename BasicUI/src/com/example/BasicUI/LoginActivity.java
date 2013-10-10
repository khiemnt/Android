package com.example.BasicUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * User: Dell 3360
 * Date: 10/9/13
 * Time: 4:09 PM
 */
public class LoginActivity extends Activity
{
    EditText editTextUserName;
    EditText editTextPassword;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        editTextUserName = (EditText) findViewById(R.id.editUserName);
        editTextPassword = (EditText) findViewById(R.id.editPassword);

        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (editTextUserName.getText().toString().equals("android") && editTextPassword.getText().toString().equals("1234"))
                {
                    Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                    i.putExtra("username",editTextUserName.getText().toString()) ;
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}