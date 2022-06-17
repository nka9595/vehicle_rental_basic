package com.vehicle.rent.rental.service.Impl;

import com.vehicle.rent.rental.model.VehicleModel;
import com.vehicle.rent.rental.service.PriceService;
import com.vehicle.rent.rental.strategy.PriceStrategy;

public class PriceServiceImpl implements PriceService {

    private final PriceStrategy priceStrategy;

    public PriceServiceImpl(PriceStrategy priceStrategy){
        this.priceStrategy=priceStrategy;
    }
    @Override
    public int getPrice(VehicleModel vehicleModel) {
        return vehicleModel.getBasePrice();
    }
}
