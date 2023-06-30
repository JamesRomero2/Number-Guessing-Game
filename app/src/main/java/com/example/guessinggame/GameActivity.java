package com.example.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView lastGuess, remainingGuess, hint;
    private EditText guess;
    private Button guessBtn;
    boolean twoDigits, threeDigits, fourDigits;
    Random r = new Random();
    int random;
    int remainingAttempts = 10;
    ArrayList<Integer> guessList = new ArrayList<>();
    int userAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        guessBtn = (Button) findViewById(R.id.button3);
        guess = (EditText) findViewById(R.id.editTextNumber);
        lastGuess = (TextView) findViewById(R.id.textView5);
        remainingGuess = (TextView) findViewById(R.id.textView7);
        hint = (TextView) findViewById(R.id.textView9);
        twoDigits = (Boolean) getIntent().getBooleanExtra("two", false);
        threeDigits = (Boolean) getIntent().getBooleanExtra("three", false);
        fourDigits = (Boolean) getIntent().getBooleanExtra("four", false);

        if (twoDigits) {
            random = r.nextInt(90) + 10;
        }
        if (threeDigits) {
            random = r.nextInt(90) + 100;
        }
        if (fourDigits) {
            random = r.nextInt(90) + 1000;
        }

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guessStr = guess.getText().toString();
                if (guessStr.equals(String.valueOf(random))) {
                    AlertDialog.Builder builder = new
                            AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("Number Guessing Game");
                    builder.setCancelable(false);
                    builder.setMessage(
                            "Congrats! My guess was " + random +
                                    "\n \nNumber of Guesses: " + userAttempts + " attemps" +
                            "\n\nYour Guesses: " + guessList + "\n\nWould you like to Play again ?"
                    );
                    builder.setPositiveButton("YES", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface,
                                                    int i) {
                                    Intent intent = new Intent(GameActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                    builder.setNegativeButton("NO", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface,
                                                    int i) {
                                    moveTaskToBack(true);

                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            });
                    builder.create().show();
                } else {
                    lastGuess.setVisibility(View.VISIBLE);
                    remainingGuess.setVisibility(View.VISIBLE);
                    hint.setVisibility(View.VISIBLE);
                    userAttempts++;
                    remainingAttempts--;
                    lastGuess.setText(guessStr);
                    String rAttempt = String.valueOf(remainingAttempts);
                    remainingGuess.setText(rAttempt);
                    int userGuess = Integer.parseInt(guessStr);
                    guessList.add(userGuess);

                    if (random == userGuess) {
                        hint.setText("CORRECT !");
                    }
                    if (random < userGuess) {
                        hint.setText("Guess Lower");
                    }
                    if (random > userGuess) {
                        hint.setText("Guess Higher");
                    }

                    if (remainingAttempts == 0) {
                        AlertDialog.Builder builder = new
                                AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage(
                                "Sorry, attempts runs out." + "\n\nMy guess was" + + random +
                                "\n \nNumber of Guesses: " +
                                        userAttempts + " attemps" +
                                        "Your Guesses: " + guessList +
                                        "\n\nWould you like to Play again ?"
                        );
                        builder.setPositiveButton("YES", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface
                                                                dialogInterface, int i) {
                                        Intent intent = new Intent(GameActivity.this,
                                                MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                        builder.setNegativeButton("NO", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface
                                                                dialogInterface, int i) {
                                        moveTaskToBack(true);

                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                });
                        builder.create().show();
                    }
                }
            }
        });
    }
}