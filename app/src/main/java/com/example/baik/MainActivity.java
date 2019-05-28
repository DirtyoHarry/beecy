package com.example.baik;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baik.Tables.users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // FIREBASE \\
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;

    // VIEW \\

    Button loginBtn;
    EditText pswText, mailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // VIEW INIT
        pswText = (EditText) findViewById(R.id.pswText);
        mailText = (EditText) findViewById(R.id.mailText);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        // FIREBASE INIT
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logInAuth(mailText.getText().toString(), pswText.getText().toString());
            }
        });

    }

    private void logInSuccesful ()
    {

        Intent myIntent = new Intent(getApplicationContext(), LandingActvity.class);
        // myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }

    private void logInAuth (String mail, String psw)
    {
        mAuth.signInWithEmailAndPassword(mail,psw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                userData();
                Toast.makeText(MainActivity.this, "Hai eseguito il login", Toast.LENGTH_LONG).show();
                finish();
                logInSuccesful();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Errore", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void userData ()
    {
        Log.d("database", mAuth.getUid().toString());
        users users = new users();
        users.setEmail(mAuth.getInstance().getCurrentUser().getEmail().toString());
        users.setUid("test");


        Map<String,Object> userMap = new HashMap<>();

        userMap.put("email", users.getEmail() );
        userMap.put("id", users.getUid());

        mFirestore.collection("users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("database", "completato");
            }
        }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database", "non completato");
            }
        });



    }
}
