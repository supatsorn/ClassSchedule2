//package com.myfrist.classschedule;
//
//import android.graphics.Color;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.PolylineOptions;
//
//import java.util.ArrayList;
//
//public class Main4Activity extends AppCompatActivity {
//
//
//
//    SupportMapFragment mapFragment;
//    private GoogleMap map;
//    public String latLngs = new String();
//
//
//
//
//    private LatLng a1 = new LatLng(18.80365652691035, 98.95246602594852);
//    private LatLng a2 = new LatLng(18.80303255273886, 98.95221691578627);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.get_current_location);
//
//
//
////        latLngs.add(a1);
////        latLngs.add(a2);
//
//
//        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                map = googleMap;
//                map.setMyLocationEnabled(true);
//                map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
//                    @Override
//                    public void onMyLocationChange(Location location) {
//                        double me_lat = location.getLatitude();
//                        double me_lng = location.getLongitude();
//                        LatLng latLng = new LatLng(me_lat, me_lng);
//                        Log.i("mint", me_lat + "," + me_lng);
////                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
////                        map.setOnMyLocationChangeListener(null);
////                        addMarker();
////                        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
////                            @Override
////                            public void onMapClick(LatLng latLng) {
////                                map.addMarker(new MarkerOptions()
////                                        .position(latLng)
////                                        .title("me"));
////
////                                Log.i("POND", latLng.latitude + "\n" + latLng.longitude);
////                            }
////                        });
//                    }
//                });
//            }
//        });
//
//
//
//
//
//    }
//
//
////
////    private void addMarker() {
////        for (int i = 0; i < latLngs.size(); i++) {
////            map.addMarker(new MarkerOptions().position(latLngs.get(i)));
////        }
////        map.addPolyline(new PolylineOptions()
////                .add(a1, a2)
////                .width(5)
////                .color(Color.RED));
////    }
//}
