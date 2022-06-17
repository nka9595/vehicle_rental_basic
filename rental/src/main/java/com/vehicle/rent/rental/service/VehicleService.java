package com.vehicle.rent.rental.service;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.model.VehicleModel;

import java.util.List;

public interface VehicleService {


    boolean addVehicle(String branchName, VehicleType vehicleType,String vehicleName, int price);
    List<VehicleModel> getVehicles(String branchName);
    List<VehicleModel> getVehicles(String branchName,VehicleType vehicleType);
}
