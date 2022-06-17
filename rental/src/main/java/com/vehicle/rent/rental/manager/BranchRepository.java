package com.vehicle.rent.rental.manager;

import com.vehicle.rent.rental.model.BranchModel;

import java.util.HashMap;
import java.util.Map;

public class BranchRepository {
    private Map<String, BranchModel> branchModelMap=new HashMap<>();

    public boolean saveBranch(BranchModel branchModel){
        if(branchModelMap.containsKey(branchModel.getBranchName())){
            return false;
        }
        branchModelMap.put(branchModel.getBranchName(),branchModel);
        return true;
    }

    public BranchModel getBranch(String branchName){
        return branchModelMap.get(branchName);
    }
}
