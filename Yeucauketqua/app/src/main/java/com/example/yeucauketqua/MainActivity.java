package com.example.yeucauketqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnKq;
    TextView edtA, edtB, edtKq;
    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKq = findViewById(R.id.edtKq);
        btnKq = findViewById(R.id.btnKq);
        btnKq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                myIntent = new Intent(MainActivity.this, ResultActivity.class);
                myIntent.putExtra("soa", a);
                myIntent.putExtra("sob", b);
                startActivityForResult(myIntent, 99);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 &&  resultCode == 33){
            int kq = data.getIntExtra("kq", 0);
            edtKq.setText(kq + "");
        }
        if(requestCode == 99 &&  resultCode == 34){
            int kq = data.getIntExtra("kq", 0);
            edtKq.setText(kq + "");
        }
    }
}