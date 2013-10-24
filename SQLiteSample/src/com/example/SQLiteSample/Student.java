package com.example.SQLiteSample;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/22/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Student
{
    private int studentId;
    private String studentName;
    private int studentAge;

    //Constructor
    public Student()
    {
    }

    public Student(String studentName, int studentAge)
    {
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    public Student(int studentId, String studentName, int studentAge)
    {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
    }
    //getter & setter


    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public int getStudentAge()
    {
        return studentAge;
    }

    public void setStudentAge(int studentAge)
    {
        this.studentAge = studentAge;
    }
}
