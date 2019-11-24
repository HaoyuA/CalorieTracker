package com.example.assignment2;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragmentActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    Button button;
    Button buttonShow;
    Double lat = -37.8;
    Double lng = 145.04;
    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        button = findViewById(R.id.btnHome);
        buttonShow = findViewById(R.id.btnShow);

        button.setOnClickListener(new View.OnClickListener() {
            //including onClick() method
            public void onClick(View v) {
                intent = getIntent();
                bundle= intent.getExtras();
                Intent intent = new Intent(MapFragmentActivity.this, MainActivity.class);
                intent.putExtras (bundle);
                startActivity(intent);
            }
        });
        buttonShow.setOnClickListener(new View.OnClickListener() {
            //including onClick() method
            public void onClick(View v) {
                GoogleGeocodeAsyncTask googleGeocodeAsyncTask = new GoogleGeocodeAsyncTask();
                googleGeocodeAsyncTask.execute();
                LatLng marker = new LatLng( lat,lng);
                mMap.addMarker(new MarkerOptions().position(marker));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
            }
        });
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        mMap.setMinZoomPreference(15.0f);
        // Add a marker in address and move the camera
        LatLng marker = new LatLng( lat,lng);
        mMap.addMarker(new MarkerOptions().position(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    private class GoogleGeocodeAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            intent = getIntent();
            bundle= intent.getExtras();
            String postcode = bundle.getString("postcode");
            String address = bundle.getString("address");
            lat = Double.valueOf(GeocodingAPI.getlat(GeocodingAPI.search(address,postcode)));
            lng = Double.valueOf(GeocodingAPI.getlng(GeocodingAPI.search(address,postcode)));



            return result;
        }

        @Override
        protected void onPostExecute(String result) {


        }
    }
}