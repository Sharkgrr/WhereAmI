package br.usjt.fire;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Dashboard extends AppCompatActivity implements FirestoreAdapter.OnListLongClick{
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView jRecycler;
    private String collection;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int GPS_REQUEST_PERMISSION_CODE = 1001;
    private FirestoreAdapter adapter;

    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );
        locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        collection = auth.getUid();

        jRecycler = findViewById(R.id.recycler);
        Query query = firebaseFirestore.collection(collection);
        FirestoreRecyclerOptions<Local> options = new FirestoreRecyclerOptions.Builder<Local>()
                .setQuery(query, Local.class )
                .build();

        adapter = new FirestoreAdapter(options, this);
        jRecycler.setHasFixedSize(true);
        jRecycler.setLayoutManager(new LinearLayoutManager( this ));
        jRecycler.setAdapter(adapter);
    }
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void redirecionarNovoLugar(View view) {
        startActivity(new Intent(this, NovoLugar.class));
    }

    @Override
    public void onListLongClick(Local snapshot, int position) {
        Log.d( "LONGCLICK :)", "DEU CLICK" );
        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("Deletar")
                .setMessage("Deletar " + snapshot.getLocal() + "?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseFirestore.collection(collection).document(snapshot.getLocal()).delete();
                        Toast.makeText(Dashboard.this, "Exclusão com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    }



