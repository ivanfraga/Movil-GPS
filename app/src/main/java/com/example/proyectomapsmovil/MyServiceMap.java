package com.example.proyectomapsmovil;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


public class MyServiceMap extends IntentService {
    private FusedLocationProviderClient ubication;
    FirebaseDatabase database;
    Timer timer;
    private Context thisConetext=this;

    TimerTask timerTask;

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.proyectomapsmovil.action.FOO";
    public static final String ACTION_BAZ = "com.example.proyectomapsmovil.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.proyectomapsmovil.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.proyectomapsmovil.extra.PARAM2";

    public MyServiceMap() {
        super("MyServiceMap");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // getLastLocation();
        timer = new Timer();
        database=  FirebaseDatabase.getInstance();

        //   startTimer(intent.getIntExtra("context",));
        startTimer();
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void getLastLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            ubication = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            ubication.getLastLocation().addOnSuccessListener((Activity) getApplicationContext(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // LocationUser lu=new LocationUser("s",location.getLatitude(),location.getLongitude());
                    Toast.makeText(getApplicationContext(), "location" + location.getLatitude() + ":" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    //saveLocationUser(lu);
                }
            });
        }

    }

    public void saveLocationUser(LocationUser locationUser) {
        DatabaseReference myRef = database.getReference("locations");
        myRef.push().setValue(locationUser);
        // databaseReference.child("locations").child().setValue(locationUser);

    }

    public void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                // getLastLocation();
                // Toast.makeText(thisConetext, "location", Toast.LENGTH_SHORT).show();
                Log.i("com.pakriders.intentserviceexample", "hola");
                DatabaseReference myRef = database.getReference("locations");
                LocationUser lu = new LocationUser("ads","0.221","0.156", LocalDateTime.now().toString());
                myRef.push().setValue(lu);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                }
               // ubication = LocationServices.getFusedLocationProviderClient(getApplicationContext());

               // getLastLocation();

            }
        };
        timer.schedule(timerTask,0,6000);
    }
}