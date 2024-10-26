package com.example.studentmanagementapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DisplayFragment extends Fragment {
    private TextView textViewName, textViewAge, textViewGrade, textViewMajor;
    private StudentViewModel studentViewModel;
    private View detailsLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        textViewName = view.findViewById(R.id.textViewName);
        textViewAge = view.findViewById(R.id.textViewAge);
        textViewGrade = view.findViewById(R.id.textViewGrade);
        textViewMajor = view.findViewById(R.id.textViewMajor);

        detailsLayout = view.findViewById(R.id.details_layout);
        detailsLayout.setVisibility(View.GONE);

        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        studentViewModel.getStudent().observe(getViewLifecycleOwner(), student -> {
            if (student != null) {
                textViewName.setText("Name: " + student.getName());
                textViewAge.setText("Age: " + student.getAge());
                textViewGrade.setText("Grade: " + student.getGrade());
                textViewMajor.setText("Major: " + student.getMajor());
            }
        });

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> {
            detailsLayout.setVisibility(View.VISIBLE);
        });

        return view;
    }
}
