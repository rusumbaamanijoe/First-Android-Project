package com.example.studentmanagementapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StudentViewModel extends ViewModel {
    private final MutableLiveData<Student> student = new MutableLiveData<>();

    public void setStudent(Student studentData) {
        student.setValue(studentData);
    }

    public LiveData<Student> getStudent() {
        return student;
    }
}
