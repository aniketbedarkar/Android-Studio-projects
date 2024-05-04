package com.aniket.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView luckyNumberTextView, welcomeTextView;
    Button shareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        luckyNumberTextView = findViewById(R.id.luckyNumber);
        welcomeTextView = findViewById(R.id.textView);
        shareButton = findViewById(R.id.shareButton);

        //receiving data from main activity
        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        int randomNumber = genarateRandomNumber();
        welcomeTextView.setText(String.format("%s your lucky number is:", userName));
        luckyNumberTextView.setText(String.format(String.valueOf(randomNumber)));
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,randomNumber);
            }
        });
    }

    private void shareData(String userName, int randomNumber) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,userName+" got lucky today");
        intent.putExtra(Intent.EXTRA_TEXT, "His lucky number is: "+randomNumber);
        startActivity(Intent.createChooser(intent, "Choose a Platform"));
    }

    public Integer genarateRandomNumber(){
        Random random = new Random();
        return random.nextInt(1000);
    }
}