package com.vehicle.rent.rental.manager;

import com.vehicle.rent.rental.model.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {
    private Map<String, List<Slot<Integer>>> bookingMap=new HashMap<>();

    public List<Slot<Integer>> getBookingForVehicle(String vehicleName){
        return bookingMap.getOrDefault(vehicleName,new ArrayList<>());
    }

    public void updateBooking(String vehicleName,List<Slot<Integer>> bookings ){
        bookingMap.put(vehicleName,bookings);
    }
}
