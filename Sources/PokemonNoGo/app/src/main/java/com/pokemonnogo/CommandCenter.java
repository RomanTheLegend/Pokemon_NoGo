package com.pokemonnogo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;

import android.widget.Toast;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by the.Legend on 16/07/2016.
 */
public class CommandCenter extends Service {








    IBinder mBinder;
    int mStartMode;

    boolean mAllowRebind;

    private Thread thread = null;
    private NetworkModule networkModule = null;



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


    @Override
    public void onCreate(){
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        networkModule=new NetworkModule();
        if(networkModule.init){
            thread = new Thread(networkModule);
            thread.start();

            Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

            sendMessage("Service is running");
        }else {

            Toast.makeText(this, "Service failed", Toast.LENGTH_LONG).show();

            sendMessage("Service could not start");
            onDestroy();
        }




        return START_STICKY;
    }






    @Override
    public void onDestroy() {

        if (thread != null) {
            networkModule.stopListener();
            try {
                thread.interrupt();
            }catch (Exception e) {

            }
        }


        super.onDestroy();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }




    private void sendMessage(String message){
        if(null != MainActivity.mUiHandler)
        {
            Message msgToActivity = new Message();
            msgToActivity.obj  = message;

            MainActivity.mUiHandler.sendMessage(msgToActivity);
        }

    }





class NetworkModule implements Runnable {

    BufferedReader inboundBuffer;
    private ServerSocket listener;
    private Socket socket;
    private boolean isRunning=false;
    public boolean init=false;
    private FakeGPS fakeGPS;

    public NetworkModule(){
        try {
            listener = new ServerSocket(9090);
            fakeGPS=new FakeGPS();
            isRunning=true;
            init=true;
        } catch (Exception e) {
            sendMessage("Failed to open socket");
            e.printStackTrace();
        }

    }



    @Override
    public void run() {
        try {
            socket = listener.accept();
            sendMessage("Client connection detected");
            inboundBuffer = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            Double latitude = 60.089438;
            Double longitude = 29.899352;

            while (isRunning) {

                String message = inboundBuffer.readLine();
                if (!message.equals(null)){

                    if(message.indexOf("GPS!")!=0){
                        if(message.length()>2){
                            sendMessage(message);
                        }
                    }else {

                        String [] messageParts=message.split("!",3);
                        longitude=Double.parseDouble(messageParts[1]);
                        latitude=Double.parseDouble(messageParts[2]);

                        fakeGPS.applyCoordinates(longitude,latitude);
                    }




                }else{
                    sendMessage("Client disconnected");
                    isRunning=false;
                }

            }
        }catch (Exception expected){
            sendMessage("Connection interrupted");
            isRunning=false;
            stopSelf();
        }


    }




    private void stopListener(){
        try {
            isRunning=false;
            socket.close();
            listener.close();
            sendMessage("Network connections closed");

        }catch (Exception expected){
            sendMessage("Error closing connections");
        }


    }





}


}
