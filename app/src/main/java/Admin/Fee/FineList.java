package Admin.Fee;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

// Fine.java
class Fine {
    private String studentName;
    private String studentId;
    private double amount;
    private boolean isWaived;

    public Fine(String studentName, String studentId, double amount) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.amount = amount;
        this.isWaived = false;
    }

    // Getters and setters
    public String getStudentName() { return studentName; }
    public String getStudentId() { return studentId; }
    public double getAmount() { return amount; }
    public boolean isWaived() { return isWaived; }
    public void setWaived(boolean waived) { isWaived = waived; }
}

// FineAdapter.java
class FineAdapter extends RecyclerView.Adapter<FineAdapter.FineViewHolder> {
    private List<Fine> fines;
    private List<Fine> filteredFines;
    private OnFineWaiverClickListener waiverClickListener;

    public interface OnFineWaiverClickListener {
        void onWaiverClick(Fine fine, int position);
    }

    public FineAdapter(List<Fine> fines, OnFineWaiverClickListener listener) {
        this.fines = fines;
        this.filteredFines = new ArrayList<>(fines);
        this.waiverClickListener = listener;
    }

    @NonNull
    @Override
    public FineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fine_item, parent, false);
        return new FineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FineViewHolder holder, int position) {
        Fine fine = filteredFines.get(position);
        holder.bind(fine, position);
    }

    @Override
    public int getItemCount() {
        return filteredFines.size();
    }

    public void filter(String query) {
        filteredFines.clear();
        if (query.isEmpty()) {
            filteredFines.addAll(fines);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Fine fine : fines) {
                if (fine.getStudentName().toLowerCase().contains(lowerCaseQuery) ||
                        fine.getStudentId().toLowerCase().contains(lowerCaseQuery)) {
                    filteredFines.add(fine);
                }
            }
        }
        notifyDataSetChanged();
    }

    class FineViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName;
        private TextView studentId;
        private TextView fineAmount;
        private MaterialButton waiverButton;

        FineViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            studentId = itemView.findViewById(R.id.studentId);
            fineAmount = itemView.findViewById(R.id.fineAmount);
            waiverButton = itemView.findViewById(R.id.waiverButton);
        }

        void bind(Fine fine, int position) {
            studentName.setText(fine.getStudentName());
            studentId.setText(fine.getStudentId());
            fineAmount.setText(String.format("$%.2f", fine.getAmount()));

            waiverButton.setEnabled(!fine.isWaived());
            waiverButton.setText(fine.isWaived() ? "Waived" : "Waiver");

            waiverButton.setOnClickListener(v -> {
                if (waiverClickListener != null) {
                    waiverClickListener.onWaiverClick(fine, position);
                }
            });
        }
    }
}

// FineListActivity.java
public class FineList extends AppCompatActivity implements FineAdapter.OnFineWaiverClickListener {
    private RecyclerView recyclerView;
    private FineAdapter adapter;
    private List<Fine> fines;
    private TextInputEditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_list);

        // Initialize views
        recyclerView = findViewById(R.id.fineRecyclerView);
        searchEditText = findViewById(R.id.searchEditText);
        FloatingActionButton addFineButton = findViewById(R.id.addFine);

        // Initialize fines list and adapter
        fines = new ArrayList<>();
        adapter = new FineAdapter(fines, this);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup FAB click listener
        addFineButton.setOnClickListener(v -> showAddFineDialog());
    }

    private void showAddFineDialog() {
        // Implement your dialog to add new fine
    }

    @Override
    public void onWaiverClick(Fine fine, int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Waiver")
                .setMessage("Are you sure you want to waive this fine?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    fine.setWaived(true);
                    adapter.notifyItemChanged(position);
                })
                .setNegativeButton("No", null)
                .show();
    }
}