package com.example.BooksHome;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class codeverification extends AppCompatActivity {
    EditText codeV;
    Button bnt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeverification);

        bnt2=(Button)findViewById(R.id.button2);
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(codeverification.this,Confirmation.class);
                startActivity(intent);
            }
        });
    }}