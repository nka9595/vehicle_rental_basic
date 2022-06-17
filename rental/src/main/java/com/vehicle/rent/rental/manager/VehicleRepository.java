package com.vehicle.rent.rental.manager;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.model.VehicleModel;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleRepository {

    Map<String, Map<String,VehicleModel>> branchVehicleMap=new HashMap<>();

    public boolean saveVehicle(String branchName,VehicleModel vehicleModel){
        Map<String,VehicleModel> vehicleModelMap=branchVehicleMap.getOrDefault(branchName,new HashMap<>());
        if(vehicleModelMap.containsKey(vehicleModel.getVehicleName())){
            return false;
        }
        vehicleModelMap.put(vehicleModel.getVehicleName(),vehicleModel);
        branchVehicleMap.put(branchName,vehicleModelMap);
        return  true;
    }

    public Map<String,VehicleModel> getVehicleModelMap(String branchName){
        return branchVehicleMap.getOrDefault(branchName,new HashMap<>());
    }
}
