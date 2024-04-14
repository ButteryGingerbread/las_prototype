package com.example.libraryadminstrationsystem;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

public class ActivityBorrow extends AppCompatActivity {

    EditText etStudentID, etBookID;
    Button btnConfirm;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_borrow);
        etStudentID = findViewById(R.id.etStudentID);
        etBookID = findViewById(R.id.etBookID);
        btnConfirm = findViewById(R.id.btnConfirm);
        dbHelper = new DBHelper(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("BUTTON CLICKED");
                String student, book;
                student = etStudentID.getText().toString();
                book = etBookID.getText().toString();

                if (TextUtils.isEmpty(student) || TextUtils.isEmpty(book)) {
                    Toast.makeText(ActivityBorrow.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean registeredSuccess = dbHelper.insertData(student, book);
                    if (registeredSuccess) {
                        Toast.makeText(ActivityBorrow.this, "Borrow Confirmed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ActivityBorrow.this, "Borrow Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

