package com.example.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnTang, btnGiam;
    EditText edtBillAmount, edtPercent, edtTip, edtTotal;
    float billAmount, percent, tip, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnTang = findViewById(R.id.btnTang);
        btnGiam = findViewById(R.id.btnGiam);

        edtBillAmount = findViewById(R.id.edtBillAmount);
        edtPercent = findViewById(R.id.edtPercent);
        edtTip = findViewById(R.id.edtTip);
        edtTotal = findViewById(R.id.edtTotal);

        tip = 0;
        total = 0;
        percent = 0;

        edtTotal.setFocusable(false);
        edtPercent.setFocusable(false);
        edtTip.setFocusable(false);

        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBillAmount();
                if (percent < 1) {
                    percent += 0.05;
                    calculateAndDisplayTipAndTotal();
                } else {
                    btnTang.setEnabled(false);
                }
            }
        });

        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBillAmount();
                if (percent > 0) {
                    percent -= 0.05;
                    calculateAndDisplayTipAndTotal();
                } else {
                    btnGiam.setEnabled(false);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateBillAmount() {
        try {
            billAmount = Float.parseFloat(edtBillAmount.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid bill amount", Toast.LENGTH_SHORT).show();
            billAmount = 0;
        }
    }

    private void calculateAndDisplayTipAndTotal() {
        tip = billAmount * percent;
        edtTip.setText(String.format("$%.2f", tip));
        edtPercent.setText(Math.round(percent * 100.0) + "%");
        total = billAmount + tip;
        edtTotal.setText(String.format("$%.2f", total));
    }
}
