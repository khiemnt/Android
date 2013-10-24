package com.example.SQLiteSample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/22/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentOperation
{
    private DataBaseWrapper dbHelper;
    private String[] STUDENT_TABLE_COLUMNS = {DataBaseWrapper.STUDENT_ID, DataBaseWrapper.STUDENT_NAME, DataBaseWrapper.STUDENT_AGE};
    private SQLiteDatabase database;

    public StudentOperation(Context context)
    {
        dbHelper = new DataBaseWrapper(context);
    }

    public void open() throws SQLiteException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Student addStudent(String name, int age)
    {
        ContentValues values = new ContentValues();
        values.put(DataBaseWrapper.STUDENT_NAME, name);
        values.put(DataBaseWrapper.STUDENT_AGE, age);
        long studId = database.insert(DataBaseWrapper.STUDENTS, null, values);
        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,
                STUDENT_TABLE_COLUMNS, DataBaseWrapper.STUDENT_ID + " = " + studId, null, null, null, null);
        cursor.moveToFirst();
        Student newStudent = parseStudent(cursor);
        cursor.close();
        return newStudent;
    }

    private Student parseStudent(Cursor cursor)
    {
        Student student = new Student();
        student.setStudentId((cursor.getInt(0)));
        student.setStudentName(cursor.getString(1));
        student.setStudentAge(Integer.parseInt(cursor.getString(2)));
        return student;
    }

    public ArrayList<Student> getAllStudents()
    {
        ArrayList<Student> students = new ArrayList<Student>();
        Cursor cursor = database.query(DataBaseWrapper.STUDENTS,
                STUDENT_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Student student = parseStudent(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        cursor.close();
        return students;
    }

    public void deleteStudent(Student student)
    {
        long id = student.getStudentId();
        System.out.println("Student deleted with id: " + id);
        database.delete(DataBaseWrapper.STUDENTS, DataBaseWrapper.STUDENT_ID
                + " = " + id, null);
    }


}
