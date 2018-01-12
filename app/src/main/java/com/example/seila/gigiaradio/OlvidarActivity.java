package com.example.seila.gigiaradio;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class OlvidarActivity extends AppCompatActivity {

    private EditText email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        email = (EditText) findViewById(R.id.correo);

        auth = FirebaseAuth.getInstance();
    }



    public void recuperar(View view){

        String correo = email.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplication(), "Introduzca el email", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(OlvidarActivity.this, "Le hemos enviado un correo para recuperar la contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OlvidarActivity.this, "No hemos podido enviarle el correo para recuperar la contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
