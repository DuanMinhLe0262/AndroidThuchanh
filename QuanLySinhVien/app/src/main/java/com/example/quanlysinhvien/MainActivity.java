package com.example.quanlysinhvien;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtMaLop, edtTenLop, edtSiSo;
    Button btnThem, btnSua, btnXoa;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
//    String maLopCu = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtMaLop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        edtSiSo = findViewById(R.id.edtSiSo);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mylist);
        lv.setAdapter(myadapter);

        mydatabase = openOrCreateDatabase("quanlysinhvien.db", MODE_PRIVATE, null);
        try {
                String sql = "CREATE TABLE tbllop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
                mydatabase.execSQL(sql);
        }
        catch (Exception e){
            Log.e("Error", "Bảng đã tồn tại!");
        }
        loaddata();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMaLop.getText().toString().isEmpty() || edtTenLop.getText().toString().isEmpty() || edtSiSo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                String malop = edtMaLop.getText().toString();
                String tenlop = edtTenLop.getText().toString();
                int siso = Integer.parseInt(edtSiSo.getText().toString());
                if (siso <= 0){
                    Toast.makeText(getApplicationContext(), "Sĩ số phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                ContentValues myvalue = new ContentValues();
                myvalue.put("malop", malop);
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);
                String msg = "";
                if (mydatabase.insert("tbllop", null, myvalue) == -1){
                    msg = "Lỗi thêm bản ghi";
                }
                else {
                    msg = "Thêm thành công";
                    loaddata();
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMaLop.getText().toString().isEmpty() || edtTenLop.getText().toString().isEmpty() || edtSiSo.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                int siso = Integer.parseInt(edtSiSo.getText().toString());
                if (siso <= 0){
                    Toast.makeText(getApplicationContext(), "Sĩ số phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                String malop = edtMaLop.getText().toString();
                String tenlop = edtTenLop.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("malop", malop);
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);
                String ms = "";
                int n = mydatabase.update("tbllop", myvalue, "malop = ?", new String[]{malop});
                if (n == 0){
                    ms = "Sửa không thành công";
                }
                else {
                    ms = "Sửa thành công";
                    loaddata();
                }
                Toast.makeText(MainActivity.this, ms,Toast.LENGTH_SHORT).show();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtMaLop.getText().toString();
                int n = mydatabase.delete("tbllop", "malop = ?", new String[]{malop});
                String msq = "";
                if (n == 0){
                    msq = "Không có bản ghi nào để xóa";
                }
                else {
                    msq = "Xóa thành công";
                    loaddata();
                }
            Toast.makeText(MainActivity.this, msq, Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] arr = mylist.get(position).split(" - ");
                edtMaLop.setText(arr[0]);
                edtTenLop.setText(arr[1]);
                edtSiSo.setText(arr[2]);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void loaddata(){
        mylist.clear();
        Cursor c = mydatabase.query("tbllop", null,null, null, null, null, null);
        c.moveToNext();
        String data = "";
        while (c.isAfterLast() == false){
            data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
            c.moveToNext();
            mylist.add(data);
        }
        c.close();
        myadapter.notifyDataSetChanged();
    }
}