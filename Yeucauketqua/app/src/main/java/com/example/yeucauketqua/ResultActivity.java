
package com.example.yeucauketqua;

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

public class ResultActivity extends AppCompatActivity {
    Button btnTong, btnHieu;
    TextView edtA, edtB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        btnTong = findViewById(R.id.btnTong);
        btnHieu = findViewById(R.id.btnHieu);
        Intent myIntent = getIntent();
        int a = myIntent.getIntExtra("soa", 0);
        int b = myIntent.getIntExtra("sob", 0);
        edtA.setText(a + "");
        edtB.setText(b + "");

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a + b;
                myIntent.putExtra("kq", sum);
                setResult(33, myIntent);
                finish();
            }
        });

        btnHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sub = a - b;
                myIntent.putExtra("kq", sub);
                setResult(34, myIntent);
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}