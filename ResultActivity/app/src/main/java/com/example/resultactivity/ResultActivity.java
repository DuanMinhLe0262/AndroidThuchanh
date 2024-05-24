package com.example.resultactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    Button btnBack;
    TextView txtKq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        btnBack = findViewById(R.id.btnBack);
        txtKq = findViewById(R.id.txtKq);
        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getBundleExtra("mypackage");
        int a = myBundle.getInt("soa");
        int b = myBundle.getInt("sob");

        String nghiem = "";
        if(a == 0 && b == 0){
            nghiem = "PT vô số nghiệm!";
        }
        else if (a == 0 && b  != 0){
            nghiem = "PT vô nghiệm!";
        }
        else {
            nghiem = "Nghiệm của PT =" + (-1.0)*b/a;
        }
        txtKq.setText(nghiem);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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