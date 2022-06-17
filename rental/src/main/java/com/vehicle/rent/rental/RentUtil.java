package com.vehicle.rent.rental;

public abstract class RentUtil {

    public static int getInt(String value){
        try{
            return Integer.valueOf(value);
        }catch (Exception ex){
            throw new RuntimeException("Invalid input");
        }
    }
}
