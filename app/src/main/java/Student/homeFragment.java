package Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;

public class homeFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home_fragment, container, false);
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