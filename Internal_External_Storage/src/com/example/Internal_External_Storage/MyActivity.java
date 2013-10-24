package com.example.Internal_External_Storage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;

public class MyActivity extends Activity
{
    private String filename = "StorageFile.txt";
    private String filepath = "FileStorage";
    File myInternalFile;
    File myExternalFile;
    String myData = "";
    EditText myInputText;
    TextView responseText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myInputText = (EditText) findViewById(R.id.InputText);
        responseText = (TextView) findViewById(R.id.tvResponse);

        ContextWrapper contextWrapper = new ContextWrapper(
                getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        myInternalFile = new File(directory, filename);

        Button btSaveToInternalStorage = (Button) findViewById(R.id.btInternalStorageSave);
        btSaveToInternalStorage.setOnClickListener(onClickListener);

        Button btReadFromInternalStorage = (Button) findViewById(R.id.btInternalStorageGet);
        btReadFromInternalStorage.setOnClickListener(onClickListener);

        Button btSaveToExternalStorage = (Button) findViewById(R.id.btExternalStorageSave);
        btSaveToExternalStorage.setOnClickListener(onClickListener);

        Button btReadFromExternalStorage = (Button) findViewById(R.id.btExternalStorageGet);
        btReadFromExternalStorage.setOnClickListener(onClickListener);
        // check if external storage is available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly())
        {
            btSaveToExternalStorage.setEnabled(false);
        }
        else
        {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageAvailable()
    {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState))
        {
            return true;
        }
        return false;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener()
    {


        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.btInternalStorageSave:
                    try
                    {
                        FileOutputStream fos = new FileOutputStream(myInternalFile); // save
                        fos.write(myInputText.getText().toString().getBytes());
                        fos.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    myInputText.setText("");
                    responseText.setText("Saved to Internal Storage.(StorageFile.txt)");
                    break;
                case R.id.btInternalStorageGet:
                    FileInputStream fis = null;
                    try
                    {
                        fis = new FileInputStream(myInternalFile);
                        DataInputStream in = new DataInputStream(fis);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String strLine;
                        while ((strLine = br.readLine()) != null)
                        {
                            myData = myData + strLine;
                        }
                        in.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    myInputText.setText(myData);
                    responseText
                            .setText("Data retrieved from Internal Storage.(StorageFile.txt)");

                    break;
                case R.id.btExternalStorageSave:
                    try
                    {
                        FileOutputStream fos = new FileOutputStream(myExternalFile);
                        fos.write(myInputText.getText().toString().getBytes());
                        fos.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    myInputText.setText("");
                    responseText.setText("Saved to External Storage.(StorageFile.txt)");
                    break;

                case R.id.btExternalStorageGet:
                    try
                    {
                        fis = new FileInputStream(myExternalFile);
                        DataInputStream in = new DataInputStream(fis);
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(in));
                        String strLine;
                        while ((strLine = br.readLine()) != null)
                        {
                            myData = myData + strLine;
                        }
                        in.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    myInputText.setText(myData);
                    responseText
                            .setText("Data retrieved from Internal Storage.(StorageFile.txt)");
                    break;

            }
        }

    };


}
