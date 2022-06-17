package com.vehicle.rent.rental.service;


import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.model.BranchModel;

import java.util.Set;

public interface BranchService {
    boolean addBranch(String branchName, Set<VehicleType> vehicleTypes);
    BranchModel getBranch(String branchName);
}
