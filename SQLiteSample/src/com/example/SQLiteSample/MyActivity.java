package com.example.SQLiteSample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.util.ArrayList;

public class MyActivity extends Activity
{
    Button btAddNewStudent, btShowStudents, btDelete;
    ListView listStudents;
    EditText edName, edAge;

    private StudentOperation studentDBOperation;
    ArrayList<Student> studentList;
    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        edName = (EditText) findViewById(R.id.edName);
        edAge = (EditText) findViewById(R.id.edAge);
        btAddNewStudent = (Button) findViewById(R.id.btAddNewStudent);
        btShowStudents = (Button) findViewById(R.id.btShowStudents);
        btDelete = (Button) findViewById(R.id.btDelete);
        listStudents = (ListView) findViewById(R.id.listStudents);

        btAddNewStudent.setOnClickListener(onClickListener);
        btShowStudents.setOnClickListener(onClickListener);
        btDelete.setOnClickListener(onClickListener);
        studentDBOperation = new StudentOperation(this);
        studentDBOperation.open();

        studentList = studentDBOperation.getAllStudents();
        setUpDataToStudentListView(context, studentList);
    }

    private void setUpDataToStudentListView(Context context, ArrayList<Student> _studentList)
    {
        listStudents.setAdapter(new CustomerAdapter(this, R.layout.item, _studentList));
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            switch (v.getId())
            {
                case R.id.btAddNewStudent:
                    studentDBOperation.addStudent(edName.getText().toString(), Integer.parseInt(edAge.getText().toString()));
                    try
                    {
                        copyFile("/data/data/com.example.SQLiteSample/databases/Students",
                                Environment.getExternalStorageDirectory() + "/Students2");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    Toast.makeText(getApplicationContext(), "add successfull", Toast.LENGTH_LONG).show();
                    break;
                case R.id.btShowStudents:
                    ArrayList<Student> studentList = studentDBOperation.getAllStudents();
                    listStudents.setAdapter(new CustomerAdapter(MyActivity.this, R.layout.item, studentList));
                    break;
                case R.id.btDelete:
                    deleteFirstUser();
                    break;
            }
        }
    };

    public void deleteFirstUser()
    {

        Student stud = null;

        if (studentList.size() > 0)
        {
            stud = studentList.get(0);
            studentDBOperation.deleteStudent(stud);
            listStudents.setAdapter(new CustomerAdapter(MyActivity.this, R.layout.item, studentList));
        }

    }

    private void copyFile(String fromFile, String toFile) throws IOException
    {
        try
        {
            Log.e("fromFile", " " + fromFile);
            Log.e("toFile", " " + toFile);
            File f1 = new File(fromFile);
            File f2 = new File(toFile);
            InputStream in = new FileInputStream(f1);

            // For Append the file.
            // OutputStream out = new FileOutputStream(f2,true);
            // For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            Log.e("============Okkk1111", "File copied.");
        }
        catch (FileNotFoundException ex)
        {
            Log.e("============FileNotFoundException", ex.getMessage()
                    + " in the specified directory.");
        }
        catch (IOException e)
        {
            Log.e("============IOException", e.getMessage());
        }
    }
}
