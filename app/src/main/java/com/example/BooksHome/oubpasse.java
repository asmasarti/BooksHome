package com.example.BooksHome;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BooksHome.Model.users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class oubpasse extends AppCompatActivity {
    EditText name;
    EditText las;
    Button  bn1;
    EditText Num;
    private ProgressDialog gg;
    private String parentDbName="users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oubpasse);
        name=(EditText) findViewById(R.id.nom);
        las=(EditText) findViewById(R.id.las);
        Num=(EditText) findViewById(R.id.NT);
        bn1=(Button) findViewById(R.id.btn_oubpa);

        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                verif();
            }
            private void verif()
            {
                String nom=name.getText().toString();
                String prenom=las.getText().toString();
                String numtel=Num.getText().toString();
                if(TextUtils.isEmpty(nom))
                {
                    Toast.makeText(oubpasse.this, "Ecrivez votre nom..", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(prenom))
                {
                    Toast.makeText(oubpasse.this, "Ecrivez votre prenom..", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(numtel))
                {
                    Toast.makeText(oubpasse.this, "Ecrivez votre numero de telephone..", Toast.LENGTH_SHORT).show();
                }
                else {
                    codeverif(numtel);
                     }

            }

            private void codeverif(String numtel) {
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();
                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(parentDbName).child(numtel).exists()) {
                            Toast.makeText(oubpasse.this, "Ce compte n'existe pas!", Toast.LENGTH_SHORT).show();

                        } else {

                            users userbasedo = snapshot.child(parentDbName).child(numtel).getValue(users.class);

                            if (userbasedo.getNumerotel().equals(numtel)) {


                                Intent intent = new Intent(oubpasse.this, codeverification.class);
                                startActivity(intent);


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });

    }



}