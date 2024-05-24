package com.example.thongtincanhanapp;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtHoTen, edtCMND, edtBoSung;
    Button btnGui;
    RadioGroup idGroup;
    CheckBox cbDocSach, cbDocBao, cbDocCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtCMND = findViewById(R.id.edtCMND);
        edtBoSung = findViewById(R.id.edtBoSung);
        btnGui = findViewById(R.id.btnGui);
        idGroup = findViewById(R.id.idGroup);
        cbDocBao = findViewById(R.id.cbDocBao);
        cbDocSach = findViewById(R.id.cbDocSach);
        cbDocCode = findViewById(R.id.cbDocCode);

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten =  edtHoTen.getText().toString();
                if (hoten.length() <3 ){
                    Toast.makeText(MainActivity.this,"Họ tên phải lớn hơn 3 ký tự!", Toast.LENGTH_LONG).show();
                    edtHoTen.requestFocus();
                    edtHoTen.selectAll();
                    return;
                }

                String CMND =  edtCMND.getText().toString();
                if (CMND.length() != 9 ){
                    Toast.makeText(MainActivity.this,"CMND phải đúng 9 số", Toast.LENGTH_LONG).show();
                    edtCMND.requestFocus();
                    edtCMND.selectAll();
                    return;
                }

                int idselect = idGroup.getCheckedRadioButtonId();
                RadioButton rdoselect = findViewById(idselect);
                String  bangcap = rdoselect.getText().toString();

                String sothich = "";
                if (cbDocBao.isChecked()){
                    sothich += cbDocBao.getText().toString() + "-";
                }
                if (cbDocSach.isChecked()){
                    sothich += cbDocSach.getText().toString() + "-";
                }
                if (cbDocCode.isChecked()){
                    sothich += cbDocCode.getText().toString() + "-";
                }

                String bosung = edtBoSung.getText().toString();
                String tonghop = hoten+ "\n" + CMND + "\n" + bangcap + "\n" + sothich + "\n";
                tonghop += bosung + "\n";

                AlertDialog.Builder myDialog = new AlertDialog.Builder(MainActivity.this);

                View customTitleView = getLayoutInflater().inflate(R.layout.custom_title_layout, null);
                myDialog.setCustomTitle(customTitleView);

                myDialog.setMessage(tonghop);
                myDialog.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                myDialog.create().show();

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}