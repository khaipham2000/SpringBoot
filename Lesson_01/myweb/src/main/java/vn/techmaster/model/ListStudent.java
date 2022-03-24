package vn.techmaster.model;

import java.util.ArrayList;

public class ListStudent {
    static ArrayList<Student> student = new ArrayList<>();
    public static ArrayList<Student> infor(){
        student.add(new Student(1, "John",21));
        student.add(new Student(2, "Jane",20));
        student.add(new Student(3, "Josh",19));
        return student;
}
