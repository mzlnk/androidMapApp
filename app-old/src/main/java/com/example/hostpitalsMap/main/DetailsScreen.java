package com.example.hostpitalsMap.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostpitalsMap.R;
import com.example.hostpitalsMap.models.Hospital;
import com.example.hostpitalsMap.models.HospitalKind;

public class DetailsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);

        final Hospital hospital = (Hospital) getIntent().getSerializableExtra("HOSPITAL");
        assert hospital != null;

        TextView textView = findViewById(R.id.name);
        textView.setText(hospital.getName());

        textView = findViewById(R.id.address);
        textView.setText(hospital.getAddress());

        textView = findViewById(R.id.reviewCount);
        textView.setText(String.valueOf(hospital.getReviewsCount()));

        textView = findViewById(R.id.info);
        textView.setText((hospital.getInfo().length() == 0) ? "brak" : hospital.getInfo());

        textView = findViewById(R.id.kinds);
        String tmp = "";
        for(HospitalKind kind : hospital.getKindsAvailable()){
            tmp = tmp.concat(", ").concat(kind.getDisplayName());
        }
        textView.setText(tmp.substring(2));

        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float) hospital.getMark());
    }

    public void rateClick(View view){
        final RatingBar ratingBar = findViewById(R.id.ratingBar);

        String rating = "Twoja ocena: " + ratingBar.getRating();
        Toast.makeText(DetailsScreen.this, rating, Toast.LENGTH_LONG).show();

        Intent output = new Intent();
        output.putExtra("mark", (int)ratingBar.getRating());
        setResult(RESULT_OK, output);

    }

    public void onClick(View view){
        this.finish();
    }
}