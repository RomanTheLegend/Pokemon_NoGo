package com.pokemonnogo.ashburn;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by the.Legend on 17/07/2016.
 */
public class DataManager {

    Coordinates position;

    private ArrayList<Coordinates> checkPoints=new ArrayList<>();

    public DataManager(){



        for (int i=0 ; i<11 ; i++){
            checkPoints.add(null);
        }

    }


    public  void saveSettings(){

        try{
            FileOutputStream fos= new FileOutputStream("AshBurn_saved_data.bin");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(checkPoints);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }



    public  void saveSettings(Coordinates position){

        checkPoints.add(10,position);

        try{
            FileOutputStream fos= new FileOutputStream("AshBurn_saved_data.bin");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(checkPoints);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }





    public  void loadSettings(){


        try
        {
            FileInputStream fis = new FileInputStream("AshBurn_saved_data.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            checkPoints = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe){
            ioe.printStackTrace();

        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();

        }


    }

    public  Coordinates loadCheckpoint(int id){


        Coordinates checkPoint= null;

        try {
            checkPoint = checkPoints.get(id-1);
        } catch (Exception e) {

        }

        return checkPoint;
    }


    public  void saveCheckpoint(int id,Coordinates position){

        Coordinates checkPoint=new Coordinates(position);

        checkPoints.set(id-1,checkPoint);

    }



    public Coordinates getLocation(){
        return position;
    }

    public void setLocation(Coordinates position){
        this.position=position;
    }


}
