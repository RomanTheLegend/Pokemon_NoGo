package com.pokemonnogo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;

import android.support.v7.app.NotificationCompat;
import android.widget.Toast;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by the.Legend on 16/07/2016.
 */
public class CommandCenter extends Service {








    private Thread thread = null;
    private NetworkModule networkModule = null;

    private NotificationManager notificationManager;

    private NotificationCompat.Builder notificationBuilder;

    private boolean isServiceRunning=false;

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

        isServiceRunning=false;

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(this);


        Intent notificationIntent = new Intent(this, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);


        notificationBuilder.setContentTitle("Pokemon NoGo");
        notificationBuilder.setContentText("");
        notificationBuilder.setSmallIcon(R.drawable.pokeball_icon);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setOngoing(true);

        notificationBuilder.build();

        notificationManager.notify(1234,notificationBuilder.build());







    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        networkModule=new NetworkModule();


        if(!isServiceRunning){
            sendMessage("...");




        if(networkModule.isReady){
            thread = new Thread(networkModule);
            thread.start();

            Toast.makeText(this, "Pokemon NoGo service started", Toast.LENGTH_LONG).show();

            sendNotification("No clients connected");
            //sendMessage("Service is running");
        }else {

            Toast.makeText(this, "Pokemon NoGo service failed", Toast.LENGTH_LONG).show();

            sendMessage("Service could not start");
            onDestroy();
        }



        startForeground(1234,notificationBuilder.build());
        isServiceRunning=true;

        }else {
            sendMessage("Service already running");
        }


        return START_NOT_STICKY;
    }






    @Override
    public void onDestroy() {

        networkModule.isRunning=false;
        if (thread != null) {
            networkModule.stopListener();
            try {
                thread.interrupt();
            }catch (Exception e) {
                sendMessage(e.getMessage());
            }
        }





        notificationManager.cancelAll();
        stopForeground(true);
        isServiceRunning=false;
        Toast.makeText(this, "Pokemon NoGo service stopped", Toast.LENGTH_LONG).show();

        super.onDestroy();
    }




    private void sendMessage(String message){
        if(null != MainActivity.logWindowHandler)
        {
            Message msgToActivity = new Message();
            msgToActivity.obj  = message;

            MainActivity.logWindowHandler.sendMessage(msgToActivity);
        }

    }


    private void sendNotification(String message){
        notificationBuilder.setContentText(message);
        notificationManager.notify(1234,notificationBuilder.build());
    }




class NetworkModule implements Runnable {

    BufferedReader inboundBuffer;
    private ServerSocket socketListener;
    private Socket clientConnection;
    private boolean isRunning=false;
    public boolean isReady=false;
    private FakeGPS fakeGPS;

    public NetworkModule(){
        try {
            socketListener = new ServerSocket(9090);
            isRunning=true;
            isReady=true;
        } catch (Exception e) {
            sendMessage("Failed to open socket");
            sendMessage(e.getMessage());
            e.printStackTrace();
        }

        try{
            fakeGPS=new FakeGPS();
        }catch (Exception e){
            sendMessage("Failed to start fake GPS");
            sendMessage(e.getMessage());
        }

    }



    @Override
    public void run() {

        boolean isConnectionActive=false;

        Double latitude;
        Double longitude;

        while (isRunning) {

        try {
            if(!socketListener.isClosed() && !isConnectionActive) {
                sendMessage("Waiting for client connection");
                clientConnection = socketListener.accept();
                sendNotification("Client connected");
                sendMessage("Client connection detected");
                isConnectionActive=true;
                inboundBuffer = new BufferedReader(
                        new InputStreamReader(clientConnection.getInputStream()));
            }



            if(isConnectionActive) {

                String message = inboundBuffer.readLine();
                if (!message.equals(null)){

                    if(message.indexOf("GPS!")==0){
                        String [] messageParts=message.split("!",3);
                        longitude=Double.parseDouble(messageParts[1]);
                        latitude=Double.parseDouble(messageParts[2]);

                        fakeGPS.applyCoordinates(longitude,latitude);

                    }else {
                        if(message.length()>2){
                            sendMessage(message);
                        }
                    }




                }

            }


        }catch (Exception expected){
            sendMessage("Client disconnected");
            sendNotification("No clients connected");


            if( socketListener.isClosed()){
                stopListener();
                isRunning=false;
            }else {
                isConnectionActive=false;
            }




        }
        }

    }




    private void stopListener(){

        isRunning = false;


        if(clientConnection!=null ) {

            try {
                clientConnection.close();
                sendMessage("Stop accepting client connections");
            } catch (Exception expected) {
                sendMessage("Error closing connection");
                sendMessage(expected.getMessage());
            }
        }



        if(socketListener !=null){

            try {
                socketListener.close();
                sendMessage("Socket closed");
            } catch (Exception expected) {
                sendMessage("Error closing socket");
                sendMessage(expected.getMessage());
            }

        }



    }





}


}
