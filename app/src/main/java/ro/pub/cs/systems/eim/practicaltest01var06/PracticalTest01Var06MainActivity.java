package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText number1, number2, number3;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private Button playButton;

    private String onHold1, onHold2, onHold3;

    private int countCheckBox;

    private int score = 0;

    private String[] values = {"1", "2", "3", "*"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);
        number1 = findViewById(R.id.editText1);
        number2 = findViewById(R.id.editText2);
        number3 = findViewById(R.id.editText3);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        playButton = findViewById(R.id.btn_invoke_activity);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String generatedNumber1 = generateNumber();
                String generatedNumber2 = generateNumber();
                String generatedNumber3 = generateNumber();
                countCheckBox = 0;

                if (!checkBox1.isChecked()) {
                    number1.setText(generatedNumber1);
                    onHold1 = generatedNumber1;
                } else {
                    countCheckBox++;
                }
                if (!checkBox2.isChecked()) {
                    number2.setText(generatedNumber2);
                    onHold2 = generatedNumber2;
                } else {
                    countCheckBox++;
                }
                if (!checkBox3.isChecked()) {
                    number3.setText(generatedNumber3);
                    onHold3 = generatedNumber3;
                } else {
                    countCheckBox++;
                }
                String message = "Generated numbers: " + generatedNumber1 + ", " + generatedNumber2 + ", " + generatedNumber3;
                Toast.makeText(PracticalTest01Var06MainActivity.this, message, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
                intent.putExtra("number1", onHold1);
                intent.putExtra("number2", onHold2);
                intent.putExtra("number3", onHold3);
                intent.putExtra("countCheckBox", countCheckBox);
                startActivityForResult(intent, 1);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            score += data.getIntExtra("result", 1);
            Toast.makeText(this, "Score is: " + score, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", score);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int score1 = savedInstanceState.getInt("score");
        Toast.makeText(this, "Score is: " + score1, Toast.LENGTH_SHORT).show();
    }

    private String generateNumber() {
        Random random = new Random();
        int number = random.nextInt(4);
        String numberString = values[number];
        return numberString;

    }
}