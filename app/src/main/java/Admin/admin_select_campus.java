package Admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lms.R;



public class admin_select_campus extends AppCompatActivity {

    String[] getCampusNames() {
        String[] campuses = new String[]{"Campus A", "Campus B", "Campus C", "Campus D"};
        return campuses;
    }
    private String[] campuses = getCampusNames();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_campus);


        LinearLayout buttonContainer = findViewById(R.id.campusButtonContainer);


        for (String campus : campuses) {
            Button button = new Button(this);
            button.setText(campus);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create intent to navigate to CampusView activity
                    Intent intent = new Intent(admin_select_campus.this, AdminCampusView.class);
                    intent.putExtra("campusName", campus);
                    startActivity(intent);
                }
            });

            buttonContainer.addView(button);
        }
    }
}