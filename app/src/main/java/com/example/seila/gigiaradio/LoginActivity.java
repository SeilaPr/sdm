package com.example.seila.gigiaradio;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email, pass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);


    }


    public void entrar(View view){

        String correo = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(LoginActivity.this, "Introduzca el email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Introduzca la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(correo,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {

                        Toast.makeText(LoginActivity.this, "Los datos introducidos son incorrectos", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(LoginActivity.this, GrabarActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void registro(View view){
        Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(intent);
    }

    public void olvidar(View view){
        Intent intent = new Intent(LoginActivity.this, OlvidarActivity.class);
        startActivity(intent);
    }
}
