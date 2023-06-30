package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioButton twoDigitRadio, threeDigitRadio, fourDigitRadio;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoDigitRadio = (RadioButton) findViewById(R.id.radioButton);
        threeDigitRadio = (RadioButton) findViewById(R.id.radioButton4);
        fourDigitRadio = (RadioButton) findViewById(R.id.radioButton5);
        startButton = (Button) findViewById(R.id.button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        GameActivity.class);
                if (!twoDigitRadio.isChecked() &&
                        !threeDigitRadio.isChecked() && !fourDigitRadio.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please Select a number of Digits", Toast.LENGTH_SHORT).show();
                } else {
                    if (twoDigitRadio.isChecked()) {
                        intent.putExtra("two", true);
                    }
                    if (threeDigitRadio.isChecked()) {
                        intent.putExtra("three", true);
                    }
                    if (fourDigitRadio.isChecked()) {
                        intent.putExtra("four", true);
                    }
                    startActivity(intent);
                }
            }
        });
    }
}