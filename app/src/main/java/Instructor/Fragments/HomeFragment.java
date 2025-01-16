package Instructor.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;

import Student.ViewAllAnnounc;


public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_inst, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupAnnouncementsButton(view);
    }

    private void setupAnnouncementsButton(View view) {
        MaterialButton viewAllButton = view.findViewById(R.id.btn_view_all_announcements);
        if (viewAllButton != null) {
            viewAllButton.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), ViewAllAnnounc.class);
                startActivity(intent);
            });
        }
    }
}