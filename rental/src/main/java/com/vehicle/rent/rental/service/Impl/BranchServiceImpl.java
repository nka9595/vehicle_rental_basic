package com.vehicle.rent.rental.service.Impl;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.BranchRepository;
import com.vehicle.rent.rental.model.BranchModel;
import com.vehicle.rent.rental.service.BranchService;

import java.util.Set;

public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    @Override
    public boolean addBranch(String branchName, Set<VehicleType> vehicleTypes) {
        return branchRepository.saveBranch(BranchModel.builder().branchName(branchName)
                .vehicleTypes(vehicleTypes).build());
    }

    @Override
    public BranchModel getBranch(String branchName) {
        return branchRepository.getBranch(branchName);
    }
}
