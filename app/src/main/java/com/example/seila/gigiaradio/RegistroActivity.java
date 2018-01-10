package com.example.seila.gigiaradio;

import android.content.Intent;
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

public class RegistroActivity extends AppCompatActivity {

    private EditText email, pass, pass2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.txCorreo);
        pass = (EditText) findViewById(R.id.txPass);
        pass2 = (EditText) findViewById(R.id.txPass2);
    }

    public void registrar(View view){
        String correo = email.getText().toString().trim();
        String contra = pass.getText().toString().trim();
        String contra2 = pass2.getText().toString().trim();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Introduzca el email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contra)) {
            Toast.makeText(getApplicationContext(), "Introduzca la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contra2)) {
            Toast.makeText(getApplicationContext(), "Introduzca la contraseña de nuevo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.equals(contra,contra2)) {
            Toast.makeText(getApplicationContext(), "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show();
            return;
        }

        if (contra.length() < 7) {
            Toast.makeText(getApplicationContext(), "Contraseña demasiado corta, mínimo 7 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(correo,contra)
                .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistroActivity.this, "No hemos podido registrarle." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                        }
                    }
                });
    }
}
