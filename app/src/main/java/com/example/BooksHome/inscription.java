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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class inscription extends AppCompatActivity
{
    private Button inscrept;
    private EditText prenom,nom,numertel,adresse,motdepasse,confirmemotdepass;
    private ProgressDialog LoadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        inscrept=(Button) findViewById(R.id.inscrire);
        prenom=(EditText) findViewById(R.id.prenom);
        nom=(EditText) findViewById(R.id.nom);
        numertel=(EditText) findViewById(R.id.tel);
        adresse=(EditText) findViewById(R.id.adresse);
        motdepasse=(EditText) findViewById(R.id.motdepasse);
        confirmemotdepass=(EditText) findViewById(R.id.confirmemotdepasse);
        LoadingBar=new ProgressDialog(this);
        inscrept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }
    private void CreateAccount()
    {
        String nom = this.nom.getText().toString();
        String prenom = this.prenom.getText().toString();
        String numeroDeTel =numertel.getText().toString();
        String adresse = this.adresse.getText().toString();
        String motDePasse =motdepasse.getText().toString();
        String confirmerPasse =confirmemotdepass.getText().toString();

        if(TextUtils.isEmpty(nom))
        {
            Toast.makeText(this,"Ecrivez votre nom...",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(prenom))
        {
            Toast.makeText(this,"Ecrivez votre prenom...",Toast.LENGTH_LONG).show();
        }
        else  if(TextUtils.isEmpty(numeroDeTel))
        {
            Toast.makeText(this,"Ecrivez votre numéro de téléphone..",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(adresse))
        {
            Toast.makeText(this,"Ecrivez votre adresse...",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(motDePasse))
        {
            Toast.makeText(this,"Ecrivez votre mot de passe...",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(confirmerPasse))
        {
            Toast.makeText(this,"Confirmer votre mot de passe...",Toast.LENGTH_LONG).show();
        }
        else
        {
            LoadingBar.setTitle("Création de compte");
            LoadingBar.setMessage("nous vérifions les informations d'identification");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();
            ValidationTel(nom, prenom, numeroDeTel, adresse, motDePasse, confirmerPasse);

        }
    }
    private void ValidationTel(final String nom,final String prenom,final String numerotel,final String adresse,final String motdepasse,final String Confirmerpasse )
    {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("users").child(numerotel).exists())) {
if(numerotel.equals(Confirmerpasse)){

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("prenom", prenom);
                    userdataMap.put("nom", nom);
                    userdataMap.put("numerotel", numerotel);
                    userdataMap.put("adresse", adresse);
                    userdataMap.put("motdepasse", motdepasse);
                    userdataMap.put("Confirmerpasse", Confirmerpasse);

                    RootRef.child("users").child(numerotel).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                LoadingBar.dismiss();
                                Toast.makeText(inscription.this, "félicitation!vous avez creer votre compte", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(inscription.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                LoadingBar.dismiss();
                                Toast.makeText(inscription.this, "erreur de conexion:ressayer une autre fois s'il vous plais", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else
                {
                    LoadingBar.dismiss();
                    Toast.makeText(inscription.this, "confirmer mot de passe incorrect..", Toast.LENGTH_SHORT).show();

                }






                } else
                {
                    Toast.makeText(inscription.this, "numéro de téléphone déjà existe...", Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(inscription.this,"essayer par un autre numéro..",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(inscription.this,MainActivity.class);
                    startActivity(intent);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}

