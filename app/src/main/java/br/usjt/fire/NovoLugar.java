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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
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
    FirebaseOptions options;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "novoLugar";
    private static final String KEY_NOME = "local";
    private static final String KEY_DESCRICAO = "descricao";
    private static final String KEY_DATA = "data";
    private static final String KEY_LOCAL = "coordenadas";
    public String collection;

    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_novo_lugar );
        auth = FirebaseAuth.getInstance();


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

        collection = auth.getUid();

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

        Log.d( "COLLECTION", collection );
    }

    @java.lang.Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void save(View view) throws FileNotFoundException {

        String data = txtData.getText().toString();
        String nome = edNome.getText().toString();
        String local = txtLocal.getText().toString();
        String descricao = edDescricao.getText().toString();

        Map<String, Object> locais = new HashMap<>();
        locais.put( KEY_DATA, data );
        locais.put( KEY_NOME, nome );
        locais.put( KEY_LOCAL, local );
        locais.put( KEY_DESCRICAO, descricao );


        db.collection(collection).document(nome).set( locais )
                .addOnSuccessListener( new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d( TAG, "Registrado com sucesso!" );

                        Intent i = new Intent(NovoLugar.this, Dashboard.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(i);
                        cleanData();
                        finish();
                    }
                } )
                .addOnFailureListener( new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w( TAG, "Erro ao registrar!", e );
                    }
                } );

    }

    private void cleanData() {
        txtData.setText("");
        txtLocal.setText("");
        edDescricao.setText("");
        edNome.setText("");
    }

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





