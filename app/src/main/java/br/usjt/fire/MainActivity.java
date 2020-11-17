package br.usjt.fire;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText senhaEditText;
    private FirebaseAuth auth;

    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);
        auth = FirebaseAuth.getInstance();

    }

    public void irParaCadastro (View view){
        startActivity(new Intent(this, NovoUsuarioActivity.class));
    }

    public void fazerLogin (View v){
        String login = loginEditText.getEditableText().toString();
        String senha = senhaEditText.getEditableText().toString();
        auth.signInWithEmailAndPassword(login, senha)
                .addOnSuccessListener((result) -> {
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener((error) -> {
                    Toast.makeText(this, "Failure!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                });
        startActivity(new Intent(this, Dashboard.class));
    }

    public void onLocationChanged(Location location){
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }



}