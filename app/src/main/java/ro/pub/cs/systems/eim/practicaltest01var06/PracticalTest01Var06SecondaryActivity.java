package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    private TextView gained;

    private Button okBtn;

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);
        gained = findViewById(R.id.text2);
        okBtn = findViewById(R.id.ok_button);

        Bundle extras = getIntent().getExtras();
        String number1 = extras.getString("number1");
        String number2 = extras.getString("number2");
        String number3 = extras.getString("number3");
        int nrBife = extras.getInt("countCheckBox");
        int castig = 0;

        if(number1.equals("*")) {
            if(number2.equals(number3) || number2.equals("*") || number3.equals("*")) {
                castig = calculeazaCastig(nrBife);
                System.out.println("Star nr 1");
                sb.append("Gained: " + castig);
            }
        } else {
            if(number2.equals("*")) {
                if(number1.equals(number3) || number3.equals("*")) {
                    castig = calculeazaCastig(nrBife);
                    System.out.println("star nr 2");
                    sb.append("Gained: " + castig);
                }
            } else {
                if(number3.equals("*")) {
                    if(number1.equals(number2)) {
                        System.out.println("star nr 3");
                        castig = calculeazaCastig(nrBife);
                        sb.append("Gained: " + castig);
                    }
                } else {
                    if(number1.equals(number2) && number2.equals(number3)) {
                        castig = calculeazaCastig(nrBife);
                        System.out.println("fara star");
                        sb.append("Gained: " + castig);
                    }
                }
            }
        }

        gained.setText(sb);

        int finalCastig = castig;
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", finalCastig);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    private int calculeazaCastig(int nrBife) {
        switch (nrBife) {
            case 0:
                return 100;
            case 1:
                return 50;
            case 2:
                return 10;
            default:
                return 0;
        }
    }
}