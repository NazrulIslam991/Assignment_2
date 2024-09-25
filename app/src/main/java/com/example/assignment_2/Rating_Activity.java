package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.view.View;

public class Rating_Activity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = findViewById(R.id.ratingBar);
        submit = findViewById(R.id.submit);

        //submit button click
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the total rating given by the user
                float userRating = ratingBar.getRating();

                // Show a thank you message
                Toast.makeText(Rating_Activity.this, "Thank you for giving a rating of: " + userRating, Toast.LENGTH_LONG).show();

                // Navigate to MainActivity
                Intent intent = new Intent(Rating_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
