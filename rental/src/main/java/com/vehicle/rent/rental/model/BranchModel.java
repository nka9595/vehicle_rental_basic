package com.vehicle.rent.rental.model;

import com.vehicle.rent.rental.enums.VehicleType;
import java.util.Set;

public class BranchModel {
    private String branchName;
    private Set<VehicleType> vehicleTypes;

    public static BranchModel.BranchModelBuilder builder() {
        return new BranchModel.BranchModelBuilder();
    }

    public String getBranchName() {
        return this.branchName;
    }

    public Set<VehicleType> getVehicleTypes() {
        return this.vehicleTypes;
    }

    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    public void setVehicleTypes(final Set<VehicleType> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public String toString() {
        return "BranchModel(branchName=" + this.getBranchName() + ", vehicleTypes=" + this.getVehicleTypes() + ")";
    }

    public BranchModel(final String branchName, final Set<VehicleType> vehicleTypes) {
        this.branchName = branchName;
        this.vehicleTypes = vehicleTypes;
    }

    public BranchModel() {
    }

    public static class BranchModelBuilder {
        private String branchName;
        private Set<VehicleType> vehicleTypes;

        BranchModelBuilder() {
        }

        public BranchModel.BranchModelBuilder branchName(final String branchName) {
            this.branchName = branchName;
            return this;
        }

        public BranchModel.BranchModelBuilder vehicleTypes(final Set<VehicleType> vehicleTypes) {
            this.vehicleTypes = vehicleTypes;
            return this;
        }

        public BranchModel build() {
            return new BranchModel(this.branchName, this.vehicleTypes);
        }

        public String toString() {
            return "BranchModel.BranchModelBuilder(branchName=" + this.branchName + ", vehicleTypes=" + this.vehicleTypes + ")";
        }
    }
}
