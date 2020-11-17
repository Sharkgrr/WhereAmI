package br.usjt.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class NovoLugar extends Activity {

    EditText editPlace;
    Button btSubmit;
    TextView textViewAddress;
    TextView txtData;
    EditText edNome;
    TextView txtLocal;
    EditText edDescricao;
    Button btCreate;
    private FirebaseAuth auth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "NovoLugar";
    private static final String KEY_NOME = "nome";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_DATA = "data";
    private static final String KEY_LOCAL = "local";


    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_novo_lugar );

        editPlace = findViewById( R.id.nomeLugar );
        btSubmit = findViewById( R.id.bt_submit );
        textViewAddress = findViewById( R.id.tv_address );

        btSubmit.setOnClickListener( new View.OnClickListener() {
            @java.lang.Override
            public void onClick(View v) {
                String address = editPlace.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                GeoLocation.getAddress( address, getApplicationContext(), new GeoHandler() );
            }
        } );

        TextView textView;
        textView = findViewById( R.id.dataInclusao );

        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
        GregorianCalendar gc = new GregorianCalendar();
        String dateString = sdf.format( gc.getTime() );
        textView.setText( dateString );

        txtData = findViewById( R.id.dataInclusao );
        edNome = findViewById( R.id.nomeLugar );
        txtLocal = findViewById( R.id.tv_address );
        edDescricao = findViewById( R.id.descricaoInclusao );
    }

    @java.lang.Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void save(View view) {

        String data = txtData.getText().toString();
        String nome = edNome.getText().toString();
        String local = txtLocal.getText().toString();
        String descricao = edDescricao.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put( KEY_DATA, data );
        note.put( KEY_NOME, nome );
        note.put( KEY_LOCAL, local );
        note.put( KEY_DESCRICAO, descricao );


        db.collection("Locais")
                .add( note )
                .addOnSuccessListener( new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d( TAG, "DocumentSnapshot added with ID: " + documentReference.getId() );
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w( TAG, "Error adding document", e );
                    }
                } );

        db.collection( "note" )
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d( TAG, document.getId() + " => " + document.getData() );
                            }
                        } else {
                            Log.w( TAG, "Error getting documents.", task.getException() );
                        }
                    }
                });
                        startActivity( new Intent( this, Dashboard.class ));
    }

//        Map<String, Object> novaLocalização = new HashMap<>();
//        novaLocalização.put("chave_data", txtData);
//        novaLocalização.put("chave_nome", edNome);
//        novaLocalização.put("chave_local", txtLocal);
//        novaLocalização.put("chave_descricao", edDescricao);
//
//        auth = FirebaseAuth.getInstance();
//        String collection = auth.getUid();
//
//        firebaseFirestore.collection(collection).document(edNome).set(txtLocal)
//                .addOnSuccessListener((result) -> {
//                    Toast.makeText(this, ":)", Toast.LENGTH_SHORT).show();
//                    finish();
//                }).addOnFailureListener( error -> {
//            Toast.makeText(this, ":(", Toast.LENGTH_SHORT).show();
//        });
//        startActivity( new Intent( this, Dashboard.class ) );


private class GeoHandler extends Handler {
        @java.lang.Override
        public void handleMessage(Message msg){
            String address;
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    address = bundle.getString(  "address");
                    break;
                default:
                    address = null;
            }
            textViewAddress.setText(address);
        }
    }
}





