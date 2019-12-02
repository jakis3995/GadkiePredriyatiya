package com.example.gadkiepredriyatiya;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            LatLng center = new LatLng(54,21);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        }

        String json;
        try {
            InputStream is = getAssets().open("openData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject entry = jsonArray.getJSONObject(i);
                String name = entry.getString("Name");
                JSONObject record = entry.getJSONObject("Record");
                String latLng = record.getString("Place");
                if (!latLng.equals("")) {
                    int index = latLng.indexOf(",");
                    double latitude = Double.parseDouble(latLng.substring(0, index));
                    double longitude = Double.parseDouble(latLng.substring(index + 1));
                    LatLng factory = new LatLng(latitude, longitude);
                    int riskCategory = record.getInt("RiskCategory");
                    Marker marker;
                    switch (riskCategory){
                        case (1):
                            marker = mMap.addMarker(new MarkerOptions().position(factory).title(name).icon
                                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.
                                            HUE_RED)));
                            break;
                        case (2):
                            marker = mMap.addMarker(new MarkerOptions().position(factory).title(name).icon
                                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.
                                            HUE_ORANGE)));
                            break;
                        case (3):
                            marker = mMap.addMarker(new MarkerOptions().position(factory).title(name).icon
                                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.
                                            HUE_YELLOW)));
                            break;
                        case (4):
                            marker = mMap.addMarker(new MarkerOptions().position(factory).title(name).icon
                                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.
                                            HUE_GREEN)));
                            break;
                        case (5):
                            marker = mMap.addMarker(new MarkerOptions().position(factory).title(name).icon
                                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.
                                            HUE_CYAN)));
                            break;
                        default:
                            marker = mMap.addMarker(new MarkerOptions().position(factory).title(name).icon
                                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.
                                            HUE_AZURE)));
                            break;
                    }
                    marker.setTag(factory);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, InfoActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}
