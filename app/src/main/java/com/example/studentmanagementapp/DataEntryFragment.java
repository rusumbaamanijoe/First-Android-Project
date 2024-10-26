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

        // Initialize EditText fields with correct IDs
        editTextName = view.findViewById(R.id.editTextName);
        editTextAge = view.findViewById(R.id.editTextAge);
        editTextGrade = view.findViewById(R.id.editTextGrade);
        editTextMajor = view.findViewById(R.id.editTextMajor);

        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);

        // Set up the submit button click listener
        Button submitButton = view.findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(v -> submitDataIfValid());

        return view;
    }

    private void submitDataIfValid() {
        if (validateInput()) {
            String name = editTextName.getText().toString();
            int age = Integer.parseInt(editTextAge.getText().toString());
            int grade = Integer.parseInt(editTextGrade.getText().toString());
            String major = editTextMajor.getText().toString();

            studentViewModel.setStudent(new Student(name, age, grade, major));
            Toast.makeText(getActivity(), "Student data submitted", Toast.LENGTH_SHORT).show();

            // Navigate to DisplayFragment after submitting data
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_display, new DisplayFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(editTextName.getText())) {
            editTextName.setError("Name cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(editTextAge.getText()) || !TextUtils.isDigitsOnly(editTextAge.getText())) {
            editTextAge.setError("Invalid age");
            return false;
        }
        int age = Integer.parseInt(editTextAge.getText().toString());
        if (age < 0 || age > 120) {
            editTextAge.setError("Invalid age");
            return false;
        }
        if (TextUtils.isEmpty(editTextGrade.getText()) || !TextUtils.isDigitsOnly(editTextGrade.getText())) {
            editTextGrade.setError("Invalid grade");
            return false;
        }
        int grade = Integer.parseInt(editTextGrade.getText().toString());
        if (grade < 0 || grade > 100) {
            editTextGrade.setError("Grade must be between 0 and 100");
            return false;
        }
        return true;
    }
}
