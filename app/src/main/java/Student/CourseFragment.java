package Student;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.lms.R;

public class CourseFragment extends Fragment {

    public String[][] getCourseDetails() {
        String[][] courseDetails;

            courseDetails = new String[][]{
                    {"OOP", "Object-Oriented\nProgramming"},
                    {"Probability", "Statistics &\nProbability"}
            };

            return courseDetails;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_course_fragment, container, false);


        String[][] courseDetails = getCourseDetails();

        GridLayout gridLayout = rootView.findViewById(R.id.courses_grid);
        TextView headerText = rootView.findViewById(R.id.header);

        Context context = requireContext();

        // Set header text color
        headerText.setTextColor(ContextCompat.getColor(context, R.color.text_primary));

        // Screen dimensions for button layout
        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int buttonWidth = (screenWidth - (16 * 10)) / 2;
        int buttonHeight = buttonWidth;

        // Dynamically add course buttons
        for (String[] course : courseDetails) {
            Button button = new Button(context);
            button.setText(course[1]);

            // Set layout parameters for buttons
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = buttonWidth;
            params.height = buttonHeight;
            params.setMargins(16, 16, 16, 16);
            button.setLayoutParams(params);

            // Set button background and text properties
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setColor(ContextCompat.getColor(context, R.color.primary_color_dark));
            drawable.setCornerRadius(32f);

            button.setBackground(drawable);
            button.setTextColor(Color.WHITE);
            button.setAllCaps(false);

            button.setClickable(true);
            button.setFocusable(true);

            // Set onClick listener for buttons
            button.setOnClickListener(v -> {
                Intent intent;

                //intent = new Intent(context, SubjectView.class);

//                intent.putExtra("course_title", course[0]);
//                startActivity(intent);
            });

            gridLayout.addView(button);
        }

        return rootView;
    }
}