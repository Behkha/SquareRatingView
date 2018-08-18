package com.ratintech.behkha.squareratingviewexample;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ratintech.behkha.squareratingview.SquareRatingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SquareRatingView squareRatingView = findViewById(R.id.rating_view);
        squareRatingView.setRateEnable(true);
        squareRatingView.setRatedSquareColor(R.color.colorPrimary);
        squareRatingView.setRadius(2f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            squareRatingView.setElevation(0f);
        }
        squareRatingView.setRateChangeListener(new SquareRatingView.OnRateChangeListener() {
            @Override
            public void onRateChanged(int rate) {
                Toast.makeText(getApplicationContext(), String.valueOf(rate), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
