package com.vehicle.rent.rental.service.Impl;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.VehicleRepository;
import com.vehicle.rent.rental.model.BranchModel;
import com.vehicle.rent.rental.model.VehicleModel;
import com.vehicle.rent.rental.service.BranchService;
import com.vehicle.rent.rental.service.VehicleService;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BranchService branchService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, BranchService branchService){
        this.vehicleRepository=vehicleRepository;
        this.branchService=branchService;
    }
    @Override
    public boolean addVehicle(String branchName, VehicleType vehicleType, String vehicleName, int price) {
        BranchModel branchModel=branchService.getBranch(branchName);
        if(Objects.isNull(branchModel) || !branchModel.getVehicleTypes().contains(vehicleType)){
            return false;
        }
        return vehicleRepository.saveVehicle(branchName, VehicleModel.builder()
                .basePrice(price)
                .VehicleName(vehicleName)
                .type(vehicleType)
                .build());
    }

    public List<VehicleModel> getVehicles(String branchName, VehicleType vehicleType){
        Map<String,VehicleModel> vehicleModelMap=vehicleRepository.getVehicleModelMap(branchName);
        return vehicleModelMap.values().stream().filter(vehicleModel -> vehicleModel.getType() == vehicleType)
                .sorted((o1, o2) -> o1.getBasePrice()-o2.getBasePrice()).collect(Collectors.toList());
    }

    public List<VehicleModel> getVehicles(String branchName){
        Map<String,VehicleModel> vehicleModelMap=vehicleRepository.getVehicleModelMap(branchName);
        return vehicleModelMap.values().stream()
                .sorted(Comparator.comparingInt(VehicleModel::getBasePrice)).collect(Collectors.toList());

    }
}
