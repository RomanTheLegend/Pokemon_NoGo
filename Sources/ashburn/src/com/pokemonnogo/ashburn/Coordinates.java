package com.pokemonnogo.ashburn;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by the.Legend on 17/07/2016.
 */
public class Coordinates implements Serializable {

    public Double longitude;
    public Double latitude;

    public String direction;

    public String name;


    public Coordinates(Coordinates position){
        this.latitude=position.latitude;
        this.longitude=position.longitude;
        this.direction=position.direction;
        this.name=position.name;

    }

    public Coordinates(){
        latitude=50.089438;
        longitude=19.899352;
        direction="+";
    }


    public void fixFormat(int scale){
        longitude=new BigDecimal(longitude)
                .setScale(scale, BigDecimal.ROUND_HALF_UP)
                .doubleValue();

        latitude=new BigDecimal(latitude)
                .setScale(scale, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }





}
