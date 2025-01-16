package Instructor;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.example.lms.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ClassworkFragment extends Fragment {
    private FloatingActionButton addSubjectFab;
    private CardView selectionPopup;
    private boolean isPopupVisible = false;

    private RecyclerView assignmentsRecyclerView;
    private RecyclerView monthlyRecyclerView;
    private RecyclerView sendupsRecyclerView;
    private RecyclerView mocksRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classwork, container, false);

        initializeViews(view);
        setupRecyclerViews();
        setupListeners();
        loadData();

        return view;
    }

    private void initializeViews(View view) {
        addSubjectFab = view.findViewById(R.id.addSubjectFab);
        selectionPopup = view.findViewById(R.id.selectionPopup);

        assignmentsRecyclerView = view.findViewById(R.id.assignmentsRecyclerView);
        monthlyRecyclerView = view.findViewById(R.id.monthlyRecyclerView);
        sendupsRecyclerView = view.findViewById(R.id.sendupsRecyclerView);
        mocksRecyclerView = view.findViewById(R.id.mocksRecyclerView);

        // Setup individual option clicks
        view.findViewById(R.id.assignmentOption).setOnClickListener(v -> handleOptionClick("Assignment"));
        view.findViewById(R.id.monthlyOption).setOnClickListener(v -> handleOptionClick("Monthly"));
        view.findViewById(R.id.sendupsOption).setOnClickListener(v -> handleOptionClick("Send-ups"));
        view.findViewById(R.id.mocksOption).setOnClickListener(v -> handleOptionClick("Mocks"));
    }

    private void setupRecyclerViews() {
        // Setup for Assignments RecyclerView
        assignmentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        assignmentsRecyclerView.setAdapter(new ClassworkAdapter());

        // Setup for Monthly RecyclerView
        monthlyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        monthlyRecyclerView.setAdapter(new ClassworkAdapter());

        // Setup for Send-ups RecyclerView
        sendupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sendupsRecyclerView.setAdapter(new ClassworkAdapter());

        // Setup for Mocks RecyclerView
        mocksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mocksRecyclerView.setAdapter(new ClassworkAdapter());
    }

    private void setupListeners() {
        addSubjectFab.setOnClickListener(v -> togglePopupVisibility());

        // Close popup when clicking outside
        View rootView = getView();
        if (rootView != null) {
            rootView.setOnTouchListener((v, event) -> {
                if (isPopupVisible) {
                    hidePopup();
                    return true;
                }
                return false;
            });
        }
    }

    private void loadData() {
        // Load data for each section from your data source
        // Example:
        // assignmentsRecyclerView.getAdapter().submitList(assignmentsList);
        // monthlyRecyclerView.getAdapter().submitList(monthlyList);
        // etc.
    }

    private void togglePopupVisibility() {
        if (isPopupVisible) {
            hidePopup();
        } else {
            showPopup();
        }
    }

    private void showPopup() {
        selectionPopup.setVisibility(View.VISIBLE);
        isPopupVisible = true;
    }

    private void hidePopup() {
        selectionPopup.setVisibility(View.GONE);
        isPopupVisible = false;
    }

    private void handleOptionClick(String option) {
        // Handle the option click based on the type
        Toast.makeText(getContext(), "Creating new " + option, Toast.LENGTH_SHORT).show();
        hidePopup();

        // Example: Start appropriate activity based on selection
        Intent intent;
        switch (option) {
            case "Assignment":
               // intent = new Intent(getContext(), CreateAssignmentActivity.class);
                break;
            case "Monthly":
                //intent = new Intent(getContext(), CreateMonthlyActivity.class);
                break;
            case "Send-ups":
                //intent = new Intent(getContext(), CreateSendUpsActivity.class);
                break;
            case "Mocks":
                //intent = new Intent(getContext(), CreateMocksActivity.class);
                break;
            default:
                return;
        }
       // startActivity(intent);
    }

    // Example adapter class (you should create a proper adapter with your data model)
    private static class ClassworkAdapter extends RecyclerView.Adapter<ClassworkAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Create and return your ViewHolder
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // Bind your data to the ViewHolder
        }

        @Override
        public int getItemCount() {
            // Return your data size
            return 0;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}