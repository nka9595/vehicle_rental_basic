package com.vehicle.rent.rental.service;

import com.vehicle.rent.rental.enums.VehicleType;

import java.util.List;

public interface BookService {

    int bookVehicle(String branchName, VehicleType vehicleType,int travelStartTime,int travelEndTime);
    List<String> displayVehicles(String branchName, int travelStartTime,int travelEndTime);
}
