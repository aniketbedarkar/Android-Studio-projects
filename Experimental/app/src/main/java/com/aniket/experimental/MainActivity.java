package com.aniket.experimental;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        imageView = findViewById(R.id.imageView);
        GradientDrawable gradientDrawable = new GradientDrawable();

        // Define the colors for the gradient
        int[] colors = {Color.parseColor("#FF6F61"), Color.parseColor("#007EA7"), Color.parseColor("#00274C")};
        gradientDrawable.setColors(colors);

        // Set the gradient type to linear
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        for(GradientDrawable.Orientation orientation : GradientDrawable.Orientation.values()){
            gradientDrawable.setOrientation(orientation);
            imageView.setBackground(gradientDrawable);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}