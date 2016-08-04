package com.pokemonnogo;

import android.content.Context;
import android.location.*;
import android.os.SystemClock;
import android.util.Log;


/**
 * Created by the.Legend on 17/07/2016.
 */
public class FakeGPS {

    private LocationManager locationManager;
    public static final String GPS_MOCK_PROVIDER = "gps";

    public FakeGPS(){

        locationManager = (LocationManager) Environment.mainContext.getSystemService(Context.LOCATION_SERVICE);

        if(!locationManager.isProviderEnabled(GPS_MOCK_PROVIDER)) {

            locationManager.addTestProvider(GPS_MOCK_PROVIDER, false, false,
                    false, false, true, false, false, 0, 5);
            locationManager.setTestProviderEnabled(GPS_MOCK_PROVIDER, true);

            locationManager.setTestProviderStatus(GPS_MOCK_PROVIDER, LocationProvider.AVAILABLE, null, System.currentTimeMillis());
        }

    }





    public void applyCoordinates(   Double longitude,  Double latitude){

        if(locationManager.isProviderEnabled(GPS_MOCK_PROVIDER)) {



            //Double latitude = 50.089438;
            //Double longitude = 19.899352;
            Double altitude= 0.0;
            Float accuracy=3.0f;
            long timestamp=System.currentTimeMillis();



            try {

                Location location = new Location(LocationManager.GPS_PROVIDER);
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                location.setAltitude(altitude);
                location.setAccuracy(accuracy);
                location.setTime(timestamp);
                location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());




                locationManager.setTestProviderLocation(GPS_MOCK_PROVIDER, location);


                //Log.i(GPS_MOCK_PROVIDER,location.toString());



            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}










