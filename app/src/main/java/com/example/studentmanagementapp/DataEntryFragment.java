package com.example.studentmanagementapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DataEntryFragment extends Fragment {
    private EditText editTextName, editTextAge, editTextGrade, editTextMajor;
    private StudentViewModel studentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_entry, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextAge = view.findViewById(R.id.editTextAge);
        editTextGrade = view.findViewById(R.id.editTextGrade);
        editTextMajor = view.findViewById(R.id.editTextMajor);
        Button buttonSubmit = view.findViewById(R.id.buttonSubmit);

        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);

        buttonSubmit.setOnClickListener(v -> {
            if (validateInput()) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                int grade = Integer.parseInt(editTextGrade.getText().toString());
                String major = editTextMajor.getText().toString();

                studentViewModel.setStudent(new Student(name, age, grade, major));
                Toast.makeText(getActivity(), "Student data submitted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}