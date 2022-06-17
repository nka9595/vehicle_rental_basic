package com.vehicle.rent.rental;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.BranchRepository;
import com.vehicle.rent.rental.manager.VehicleRepository;
import com.vehicle.rent.rental.model.VehicleModel;
import com.vehicle.rent.rental.service.BranchService;
import com.vehicle.rent.rental.service.Impl.BranchServiceImpl;
import com.vehicle.rent.rental.service.Impl.VehicleServiceImpl;
import com.vehicle.rent.rental.service.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VehicleServiceTest {

    @Test
    public void addVehicleTest(){
        VehicleRepository vehicleRepository=new VehicleRepository();
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        boolean isAdded=vehicleService.addVehicle("B1",VehicleType.BIKE,"V1",200);

        Assertions.assertEquals(true,isAdded);
    }
    @Test
    public void notAddedVehicleTest(){
        VehicleRepository vehicleRepository=new VehicleRepository();
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        boolean isAdded=vehicleService.addVehicle("B2",VehicleType.BIKE,"V1",200);

        Assertions.assertEquals(false,isAdded);
    }
    @Test
    public void getVehicleTest(){
        VehicleRepository vehicleRepository=new VehicleRepository();
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        vehicleService.addVehicle("B1",VehicleType.BIKE,"V1",200);
        List<VehicleModel> vehicleModels=vehicleService.getVehicles("B1");

        Assertions.assertEquals(1,vehicleModels.size());
        vehicleModels=vehicleService.getVehicles("B2");
        Assertions.assertEquals(0,vehicleModels.size());
    }
    @Test
    public void getVehicleByVehicleTypeTest(){
        VehicleRepository vehicleRepository=new VehicleRepository();
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        branchService.addBranch("B1",vehicleTypes);
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        vehicleService.addVehicle("B1",VehicleType.BIKE,"V1",200);
        List<VehicleModel> vehicleModels=vehicleService.getVehicles("B1",VehicleType.BIKE);

        Assertions.assertEquals(1,vehicleModels.size());
        vehicleModels=vehicleService.getVehicles("B1",VehicleType.AUTO);
        Assertions.assertEquals(0,vehicleModels.size());
    }


}
