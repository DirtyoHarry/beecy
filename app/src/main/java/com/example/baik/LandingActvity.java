package com.example.baik;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_actvity);
        Button oButton = (Button) findViewById(R.id.bookBtn);

        oButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(LandingActvity.this);
                        builder.setMessage("Prenotando questa bici ti assumi la resposabilità per eventuali danni e/o smarrimento")
                                .setTitle("ATTENZIONE");

                        builder.setPositiveButton("Accetto", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent myIntent = new Intent(getApplicationContext(), GetBikeActivity.class);
                                // myIntent.putExtra("key", value); //Optional parameters
                                LandingActvity.this.startActivity(myIntent);
                            }
                        });
                        builder.setNegativeButton("Rifiuto", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                });
    }




}
