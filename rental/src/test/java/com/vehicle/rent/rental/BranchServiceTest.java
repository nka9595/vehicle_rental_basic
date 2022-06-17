package com.vehicle.rent.rental;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.BranchRepository;
import com.vehicle.rent.rental.model.BranchModel;
import com.vehicle.rent.rental.service.BranchService;
import com.vehicle.rent.rental.service.Impl.BranchServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BranchServiceTest {

    @Test
    public void addBranchTest(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        boolean isAdded=branchService.addBranch("B1",vehicleTypes);

        Assert.isTrue(isAdded,"Able to create branch");
    }
    @Test
    public void addSameBranchTest(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        boolean isAddedAgain=branchService.addBranch("B1",vehicleTypes);
        Assert.isTrue(!isAddedAgain,"Unable to create same branch");
    }

    @Test
    public void getBranchTest(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        BranchModel branchModel=branchService.getBranch("B1");
        Assertions.assertEquals(branchModel.getBranchName(),"B1");
    }

    @Test
    public void getUnCreatedBranchTest(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        BranchModel branchModel=branchService.getBranch("B2");
        Assert.isNull(branchModel,"Get branch");
    }
}
