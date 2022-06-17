package com.vehicle.rent.rental.model;

import com.vehicle.rent.rental.enums.VehicleType;


public class VehicleModel {
    private String VehicleName;
    private VehicleType type;
    private int basePrice;


    public static VehicleModel.VehicleModelBuilder builder() {
        return new VehicleModel.VehicleModelBuilder();
    }

    public String getVehicleName() {
        return this.VehicleName;
    }

    public VehicleType getType() {
        return this.type;
    }

    public int getBasePrice() {
        return this.basePrice;
    }

    public void setVehicleName(final String VehicleName) {
        this.VehicleName = VehicleName;
    }

    public void setType(final VehicleType type) {
        this.type = type;
    }

    public void setBasePrice(final int basePrice) {
        this.basePrice = basePrice;
    }

    public String toString() {
        return "VehicleModel(VehicleName=" + this.getVehicleName() + ", type=" + this.getType() + ", basePrice=" + this.getBasePrice() + ")";
    }

    public VehicleModel(final String VehicleName, final VehicleType type, final int basePrice) {
        this.VehicleName = VehicleName;
        this.type = type;
        this.basePrice = basePrice;
    }

    public VehicleModel() {
    }

    public static class VehicleModelBuilder {
        private String VehicleName;
        private VehicleType type;
        private int basePrice;

        VehicleModelBuilder() {
        }

        public VehicleModel.VehicleModelBuilder VehicleName(final String VehicleName) {
            this.VehicleName = VehicleName;
            return this;
        }

        public VehicleModel.VehicleModelBuilder type(final VehicleType type) {
            this.type = type;
            return this;
        }

        public VehicleModel.VehicleModelBuilder basePrice(final int basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public VehicleModel build() {
            return new VehicleModel(this.VehicleName, this.type, this.basePrice);
        }

        public String toString() {
            return "VehicleModel.VehicleModelBuilder(VehicleName=" + this.VehicleName + ", type=" + this.type + ", basePrice=" + this.basePrice + ")";
        }
    }
}
