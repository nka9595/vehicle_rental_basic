package com.vehicle.rent.rental;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.BookRepository;
import com.vehicle.rent.rental.manager.BranchRepository;
import com.vehicle.rent.rental.manager.VehicleRepository;
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

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Driver {


    public static void main(String args[]){
        if(args.length==0){
            throw new RuntimeException("No file path provided");
        }
        Scanner sc;
        try{
            File file=new File(args[0]);
            sc=new Scanner(file);
        }catch (Exception ex){
            throw new RuntimeException("Invalid File");
        }

        BranchRepository branchRepository=new BranchRepository();
        BranchService branchService=new BranchServiceImpl(branchRepository);

        VehicleRepository vehicleRepository=new VehicleRepository();
        VehicleService vehicleService=new VehicleServiceImpl(vehicleRepository,branchService);

        BookRepository bookRepository=new BookRepository();
        PriceStrategy priceStrategy=new DemandSupplyPriceStrategy();

        PriceService priceService=new PriceServiceImpl(priceStrategy);


        BookService bookService=new BookServiceImpl(vehicleService,bookRepository,priceService);



        while (sc.hasNextLine()){
            String[] inputs= sc.nextLine().split("\\s");
            String command=inputs[0];
            try{
                switch (command){
                    case "ADD_BRANCH" :
                        String branchName=inputs[1];
                        Set<VehicleType> vehicleTypes= Arrays.stream(inputs[2].split(","))
                                .map(type->VehicleType.valueOf(type)).collect(Collectors.toSet());
                         System.out.println(branchService.addBranch(branchName,vehicleTypes));
                         break;
                    case "ADD_VEHICLE" :
                        branchName=inputs[1];
                        VehicleType vehicleType=VehicleType.valueOf(inputs[2]);
                        String vehicleName=inputs[3];
                        int price=RentUtil.getInt(inputs[4]);
                        System.out.println(vehicleService.addVehicle(branchName,vehicleType,vehicleName,price));
                        break ;
                    case "BOOK" :
                        branchName=inputs[1];
                        vehicleType=VehicleType.valueOf(inputs[2]);
                        int startTime=RentUtil.getInt(inputs[3]);
                        int endTime=RentUtil.getInt(inputs[4]);
                        System.out.println(bookService.bookVehicle(branchName,vehicleType,startTime,endTime));
                        break ;
                    case "DISPLAY_VEHICLES" :
                        branchName=inputs[1];
                        startTime=RentUtil.getInt(inputs[2]);
                        endTime=RentUtil.getInt(inputs[3]);
                        System.out.println(bookService.displayVehicles(branchName,startTime,endTime));
                        break ;
                    default:
                        throw new RuntimeException("Invalid Input");
                }
            }catch (Exception exception){
                throw new RuntimeException("Invalid Input");
            }
        }
    }
}
