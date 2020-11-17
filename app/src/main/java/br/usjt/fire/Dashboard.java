package br.usjt.fire;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;


    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int GPS_REQUEST_PERMISSION_CODE = 1001;

    TextView txData;
    EditText etNome;
    TextView txLocal;
    EditText edDescricao;


    @java.lang.Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        auth = FirebaseAuth.getInstance();
//
//        String collection = auth.getUid();
//        Query query = (Query) firebaseFirestore.collection(collection);
//
//        FirestoreRecyclerOptions<Location> options = new FirestoreRecyclerOptions.Builder<Location>()
//                .setQuery(query, NovoLugar.class)
//                .build();
//        adapter = new FirebaseAdapter(options, this);
//
//


    }
//
//    @java.lang.Override
//    protected void onStart() {
//        super.onStart();
//
//        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED) {
//            locationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER,
//                    5000,
//                    10,
//                    locationListener
//            );
//        } else {
//            ActivityCompat.requestPermissions(
//                    this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    GPS_REQUEST_PERMISSION_CODE //resolvendo o número mágico
//            );
//        }
//    }
//
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == GPS_REQUEST_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.checkSelfPermission(this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                    locationManager.requestLocationUpdates(
//                            LocationManager.GPS_PROVIDER,
//                            5000,
//                            10,
//                            locationListener
//                    );
//                }
//            } else {
//                Toast.makeText(this, "Not Allowed", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }
    public void redirecionarNovoLugar(View view) {
        startActivity(new Intent(this, NovoLugar.class));
    }
}

