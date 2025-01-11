package Admin;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.lms.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.Arrays;
import java.util.List;


public class admin_select_campus extends AppCompatActivity {

    String[] getCampusNames() {
        String[] campuses = new String[]{"JT", "OPF", "1A1", "Paragon"};
        return campuses;
    }
    private String[] campuses = getCampusNames();

    private void createCampusCards(List<String> campuses) {
        LinearLayout buttonContainer = findViewById(R.id.campusButtonContainer);

        for (String campus : campuses) {

            MaterialCardView cardView = new MaterialCardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, dpToPx(16)); // 16dp bottom margin
            cardView.setLayoutParams(cardParams);
            cardView.setRadius(dpToPx(16));
            cardView.setElevation(dpToPx(4));
            cardView.setCardBackgroundColor(getColor(R.color.background_secondary));
            cardView.setClickable(true);
            cardView.setFocusable(true);
            cardView.setStrokeWidth(dpToPx(1));
//            cardView.setStrokeColor(getColor(R.color.stroke_color));

            // hori layout for card content
            LinearLayout contentLayout = new LinearLayout(this);
            contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            contentLayout.setOrientation(LinearLayout.HORIZONTAL);
            contentLayout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));

            // TODO campus icon
//            ShapeableImageView iconView = new ShapeableImageView(this);
//            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(dpToPx(64), dpToPx(64));
//            iconView.setLayoutParams(iconParams);
//            iconView.setBackgroundColor(getColor(R.color.icon_background));
//            iconView.setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8));
//            iconView.setImageResource(R.drawable.ic_campus);
//            ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel.Builder()
//                    .setAllCornerSizes(ShapeAppearanceModel.PILL)
//                    .build();
//            iconView.setShapeAppearanceModel(shapeAppearanceModel);

            // verti: text
            LinearLayout textLayout = new LinearLayout(this);
            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1
            );
            textLayoutParams.setMarginStart(dpToPx(16));
            textLayout.setLayoutParams(textLayoutParams);
            textLayout.setOrientation(LinearLayout.VERTICAL);
            textLayout.setGravity(Gravity.CENTER_VERTICAL);

            //-----------------------------------Name-----------------------------------
            TextView campusName = new TextView(this);
            campusName.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            campusName.setText(campus);
            campusName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            campusName.setTextColor(getColor(R.color.text_primary));
            campusName.setTypeface(null, Typeface.BOLD);

            //-----------------------------------Loc-----------------------------------
            TextView location = new TextView(this);
            LinearLayout.LayoutParams locationParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            locationParams.topMargin = dpToPx(4);
            location.setLayoutParams(locationParams);
            location.setText("Main Academic Block");
            location.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            location.setTextColor(getColor(R.color.text_secondary));


            //-----------------------------------arrow-----------------------------------
            ImageView arrowView = new ImageView(this);
            LinearLayout.LayoutParams arrowParams = new LinearLayout.LayoutParams(
                    dpToPx(24), dpToPx(24)
            );
            arrowView.setLayoutParams(arrowParams);
            arrowView.setImageResource(R.drawable.ic_arrow);
            arrowView.setColorFilter(getColor(R.color.text_primary));
            arrowView.setLayoutParams(arrowParams);


            textLayout.addView(campusName);
            textLayout.addView(location);


            //TODO
            // contentLayout.addView(iconView);
            contentLayout.addView(textLayout);
            contentLayout.addView(arrowView);

            cardView.addView(contentLayout);

            // click listeners
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(admin_select_campus.this, AdminDashboard.class);
                intent.putExtra("campusName", campus);
                startActivity(intent);
            });


            buttonContainer.addView(cardView);
        }
    }


    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select_campus);


        LinearLayout buttonContainer = findViewById(R.id.campusButtonContainer);

        //DB CALL TODO
        List<String> campuses = Arrays.asList("JT", "OPF", "1A1");
        createCampusCards(campuses);


    }
}