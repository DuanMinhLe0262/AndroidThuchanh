package com.example.bmiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtCC, edtCN, edtBMI, edtCD;
    Button btnTinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtTen = findViewById(R.id.edtTen);
        edtCC = findViewById(R.id.edtCC);
        edtCN = findViewById(R.id.edtCN);
        edtBMI = findViewById(R.id.edtBMI);
        edtCD = findViewById(R.id.edtCD);
        btnTinh = findViewById(R.id.btnTinh);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble(edtCC.getText().toString());
                double W = Double.parseDouble(edtCN.getText().toString());
                double BMI = W/Math.pow(H,2);
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                String chandoan = "";
                if (BMI < 18){
                    chandoan = "Bạn gầy";
                }
                else if(BMI <= 24.9){
                    chandoan = "Bạn bình thường";
                } else if (BMI <= 29.9) {
                    chandoan = "Bạn béo phì mức độ 1";
                } else if (BMI <= 34.9) {
                    chandoan = "Bạn béo phì mức độ 2";
                } else {
                    chandoan = "Bạn beo phì mức độ 3";
                }

                edtCD.setText(chandoan + "");
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}