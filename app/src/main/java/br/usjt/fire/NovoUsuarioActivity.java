package br.usjt.fire;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class NovoUsuarioActivity extends AppCompatActivity {

    private EditText emailNovoUsuarioEditText;
    private EditText senhaNovoUsuarioEditText;
    private FirebaseAuth auth;

    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        emailNovoUsuarioEditText = findViewById(R.id.emailNovoUsuarioEditText);
        senhaNovoUsuarioEditText = findViewById(R.id.senhaNovoUsuarioEditText);

        auth = FirebaseAuth.getInstance();
    }

    public void criarNovoUsuario (View view){
        String email = emailNovoUsuarioEditText.getEditableText().toString();
        String senha = senhaNovoUsuarioEditText.getEditableText().toString();

        auth.createUserWithEmailAndPassword(email, senha).addOnSuccessListener((result) -> {
            Toast.makeText(this, result.getUser().getEmail(), Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener((error -> error.printStackTrace()));
    }
}