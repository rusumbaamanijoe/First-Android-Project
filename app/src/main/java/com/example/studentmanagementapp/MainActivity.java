package com.example.studentmanagementapp;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ViewModel
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        // Load fragments dynamically if they are not already loaded
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_data_entry, new DataEntryFragment())
                    .replace(R.id.fragment_container_display, new DisplayFragment())
                    .commit();
        }
    }
}