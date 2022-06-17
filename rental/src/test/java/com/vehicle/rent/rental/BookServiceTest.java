package com.vehicle.rent.rental;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.BookRepository;
import com.vehicle.rent.rental.manager.BranchRepository;
import com.vehicle.rent.rental.manager.VehicleRepository;
import com.vehicle.rent.rental.model.BranchModel;
import com.vehicle.rent.rental.service.BookService;
import com.vehicle.rent.rental.service.BranchService;
import com.vehicle.rent.rental.service.Impl.BookServiceImpl;
import com.vehicle.rent.rental.service.Impl.BranchServiceImpl;
import com.vehicle.rent.rental.service.Impl.PriceServiceImpl;
import com.vehicle.rent.rental.service.Impl.VehicleServiceImpl;
import com.vehicle.rent.rental.service.PriceService;
import com.vehicle.rent.rental.service.VehicleService;
import com.vehicle.rent.rental.strategy.DemandSupplyPriceStrategy;
import com.vehicle.rent.rental.strategy.PriceStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookServiceTest {


    @Test
    public void bookVehicle(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        VehicleRepository vehicleRepository=new VehicleRepository();
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        BookRepository bookRepository=new BookRepository();
        PriceStrategy priceStrategy=new DemandSupplyPriceStrategy();
        PriceService priceService=new PriceServiceImpl(priceStrategy);
        BookService bookService=new BookServiceImpl(vehicleService,bookRepository,priceService);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        vehicleTypes.add(VehicleType.CAR);
        branchService.addBranch("B1",vehicleTypes);
        BranchModel branchModel=branchService.getBranch("B1");
        vehicleService.addVehicle("B1",VehicleType.BIKE,"V1",200);
        int price=bookService.bookVehicle("B1",VehicleType.BIKE,1,5);
        Assertions.assertEquals(800,price);

    }
    @Test
    public void unableToBookVehicle(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        VehicleRepository vehicleRepository=new VehicleRepository();
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        BookRepository bookRepository=new BookRepository();
        PriceStrategy priceStrategy=new DemandSupplyPriceStrategy();
        PriceService priceService=new PriceServiceImpl(priceStrategy);
        BookService bookService=new BookServiceImpl(vehicleService,bookRepository,priceService);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        vehicleTypes.add(VehicleType.CAR);
        branchService.addBranch("B1",vehicleTypes);
        BranchModel branchModel=branchService.getBranch("B1");
        vehicleService.addVehicle("B1",VehicleType.BIKE,"V1",200);
        bookService.bookVehicle("B1",VehicleType.BIKE,1,5);
        int price=bookService.bookVehicle("B1",VehicleType.BIKE,3,6);
        Assertions.assertEquals(-1,price);

    }

    @Test
    public void displayVehicles(){
        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);
        VehicleRepository vehicleRepository=new VehicleRepository();
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);
        BookRepository bookRepository=new BookRepository();
        PriceStrategy priceStrategy=new DemandSupplyPriceStrategy();
        PriceService priceService=new PriceServiceImpl(priceStrategy);
        BookService bookService=new BookServiceImpl(vehicleService,bookRepository,priceService);
        Set<VehicleType> vehicleTypes=new HashSet<>();
        vehicleTypes.add(VehicleType.BIKE);
        vehicleTypes.add(VehicleType.CAR);
        branchService.addBranch("B1",vehicleTypes);
        BranchModel branchModel=branchService.getBranch("B1");
        vehicleService.addVehicle("B1",VehicleType.BIKE,"V1",200);
        vehicleService.addVehicle("B1",VehicleType.CAR,"C1",800);
        bookService.bookVehicle("B1",VehicleType.BIKE,1,5);
        List<String> vehicles=bookService.displayVehicles("B1",5,6);
        Assertions.assertEquals(2,vehicles.size());
        Assertions.assertTrue(vehicles.contains("V1"));
    }
}
