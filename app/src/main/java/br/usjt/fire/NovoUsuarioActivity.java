package br.usjt.fire;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class NovoUsuarioActivity extends Activity {

    private EditText loginNovoUsuarioEditText;
    private EditText senhaNovoUsuarioEditText;
    private FirebaseAuth auth;

    protected void onCrate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        loginNovoUsuarioEditText = findViewById(R.id.loginNovoUsuarioEditText);
        senhaNovoUsuarioEditText = findViewById(R.id.senhaNovoUsuarioEditText);
        auth = FirebaseAuth.getInstance();
    }

    public void criarNovoUsuario (View view) {
        String login = loginNovoUsuarioEditText.getEditableText().toString();
        String senha = senhaNovoUsuarioEditText.getEditableText().toString();
        auth.createUserWithEmailAndPassword(login, senha);
    }
}
