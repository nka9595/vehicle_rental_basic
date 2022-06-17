package com.vehicle.rent.rental.service.Impl;

import com.vehicle.rent.rental.enums.VehicleType;
import com.vehicle.rent.rental.manager.BookRepository;
import com.vehicle.rent.rental.model.Slot;
import com.vehicle.rent.rental.model.VehicleModel;
import com.vehicle.rent.rental.service.BookService;
import com.vehicle.rent.rental.service.PriceService;
import com.vehicle.rent.rental.service.VehicleService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private final VehicleService vehicleService;
    private final BookRepository bookRepository;
    private final PriceService priceService;

    public BookServiceImpl(VehicleService vehicleService,BookRepository bookRepository,PriceService priceService){
        this.vehicleService=vehicleService;
        this.bookRepository=bookRepository;
        this.priceService=priceService;
    }

    @Override
    public int bookVehicle(String branchName, VehicleType vehicleType, int travelStartTime, int travelEndTime) {
        List<VehicleModel> vehicleModels =vehicleService.getVehicles(branchName,vehicleType);

        for (VehicleModel vehicleModel:vehicleModels){
            Slot<Integer> slot=new Slot<>(travelStartTime,travelEndTime);
            if(isAvailable(vehicleModel.getVehicleName(),slot)) {
                List<Slot<Integer>> bookSlots=bookRepository.getBookingForVehicle(vehicleModel.getVehicleName());
                bookSlots.add(slot);
                bookRepository.updateBooking(vehicleModel.getVehicleName(), bookSlots);
                return priceService.getPrice(vehicleModel) * (slot.getEndTime()-slot.getStartTime());
            }
        }

        return -1;
    }

    @Override
    public List<String> displayVehicles(String branchName, int travelStartTime, int travelEndTime) {
        Slot<Integer> slot= Slot.<Integer>builder()
                .startTime(travelStartTime)
                .endTime(travelEndTime)
                .build();
        List<VehicleModel> vehicleModels=vehicleService.getVehicles(branchName);

        return vehicleModels.stream().filter(vehicleModel ->isAvailable(vehicleModel.getVehicleName(),slot)).sorted(Comparator.comparingInt(VehicleModel::getBasePrice))
                .map(VehicleModel::getVehicleName).collect(Collectors.toList());
    }

    private boolean isAvailable(String vehicleName,Slot<Integer> slot){
        List<Slot<Integer>> bookSlots=bookRepository.getBookingForVehicle(vehicleName);

        Optional<Slot<Integer>> matchSlots=bookSlots.stream().filter(bookSlot -> {
            if(slot.getStartTime()<=bookSlot.getStartTime() && slot.getEndTime()<=bookSlot.getStartTime()){
                return false;
            }
            if(slot.getStartTime() >= bookSlot.getEndTime() && slot.getEndTime() >= bookSlot.getEndTime()){
                return  false;
            }
            return true;
        }).findAny();

        return !matchSlots.isPresent();
    }

}
