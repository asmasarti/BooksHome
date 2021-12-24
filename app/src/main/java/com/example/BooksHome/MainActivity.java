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
import android.widget.TextView;
import android.widget.Toast;

import com.example.BooksHome.Model.users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button creeCompte;
    private Button connecter;
    private EditText numtel,code;
    private ProgressDialog LoadingBar;
    private String parentDbName="users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connecter=(Button)findViewById(R.id.seconnecter);
        numtel=(EditText)findViewById(R.id.username);
        code=(EditText)findViewById(R.id.motDePass);
        LoadingBar=new ProgressDialog(this);


//Lorsqu'on click sur le button se connecter pour s'inscrire une premiere fois :
        creeCompte=(Button)findViewById(R.id.sinscrir);
        creeCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,inscription.class);
                startActivity(intent);
            }
        });
        //pour se connecter:
        Button conn;
        conn=(Button)findViewById(R.id.seconnecter);
        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,accueil.class);
                startActivity(intent);
            }
        });
        //pour oublier mot de passe

            TextView motDePasseOub;
            motDePasseOub = (TextView) findViewById(R.id.OubMotDpass);
            motDePasseOub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,oubpasse.class);
                    startActivity(intent);
                }
            });




//verifiation de code et nutel et entrer ces derniers element a la base de donner:
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeConnecter();
            }
            private void SeConnecter() {
                String  numerotel=numtel.getText().toString();
                String motdepasse=code.getText().toString();
                if(TextUtils.isEmpty(numerotel))
                {
                    Toast.makeText(MainActivity.this, "Ecrivez votre numéro de téléphone..", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(motdepasse))
                {
                    Toast.makeText(MainActivity.this, "Ecrivez votre mot de passe..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoadingBar.setTitle("vérification de compte");
                    LoadingBar.setCanceledOnTouchOutside(false);
                    LoadingBar.show();
                    AcceCompte(numerotel,motdepasse);
                }

            }

            private void AcceCompte(String numerotel, String motdepasse)
            {
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();
                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {

                     if(snapshot.child(parentDbName).child(numerotel).exists())

                     {
                         users userbasedo =snapshot.child(parentDbName).child(numerotel).getValue(users.class);

                         if(userbasedo.getNumerotel().equals(numerotel))
                         {
                             if(userbasedo.getMotdepasse().equals(userbasedo.getConfirmerpass()))
                             {
                                 Toast.makeText(MainActivity.this,"l'operation faite avec succès ",Toast.LENGTH_SHORT).show();
                                 LoadingBar.dismiss();
                                 Intent intent=new Intent(MainActivity.this,accueil.class);
                                startActivity(intent);
                             }
                             else
                             {
                                 LoadingBar.dismiss();
                                 Toast.makeText(MainActivity.this, "Le mot de passe est incorrecte !!", Toast.LENGTH_SHORT).show();
                             }

                         }


                     }
                     else{
                         Toast.makeText(MainActivity.this, "Ce compte n'existe pas!", Toast.LENGTH_SHORT).show();
                     LoadingBar.dismiss();
                    }}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });









            }


        });




        }
}