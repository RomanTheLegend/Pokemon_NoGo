package com.pokemonnogo.ashburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;




/**
 * Created by the.Legend on 17/07/2016.
 */
public class SignalProcessor {



    private static Socket telnetSocket = null;
    private static PrintWriter telnetOut = null;
    private static BufferedReader telnetIn = null;

    public boolean isConnected;

    public SignalProcessor(){

        connectToPokemons();


    }



    public void reconnect(){
        disconnectFromPokemons();
        connectToPokemons();
    }


    private void sleep(long seconds){

        try{
            Thread.sleep(seconds);
        }catch (Exception expected){}

    }


    private void connectToPokemons(){

        try {
            telnetSocket = new Socket("127.0.0.1", 1234);
            telnetOut = new PrintWriter(telnetSocket.getOutputStream(), true);
            telnetIn = new BufferedReader(new InputStreamReader(telnetSocket.getInputStream()));

        } catch (IOException e) {
            isConnected=false;
        }

        isConnected=true;


    }



    private void disconnectFromPokemons(){



        try {

            telnetOut.close();
            telnetIn.close();
            telnetSocket.close();


        } catch (IOException e) {
            return;
        }

        isConnected=false;


    }


    public void sendData(Coordinates position){

        position.fixFormat(6);

        try {
            telnetOut.println("GPS!" + position.longitude + "!" + position.latitude + "\n");
        } catch (Exception e) {
            isConnected=false;
        }



    }


}
