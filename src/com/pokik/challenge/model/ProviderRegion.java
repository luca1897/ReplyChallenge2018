package com.pokik.challenge.model;

import java.util.ArrayList;

public class ProviderRegion {
    private String name;
    private int numAvaiablesPackages;
    private Double packageCost;

    private Provider provider;

    private ArrayList<Integer> numAvaiablesUnitForService;
    private ArrayList<Integer> latencyForCountry;
    public ProviderRegion(Provider provider) {
        numAvaiablesUnitForService = new ArrayList<>();
        latencyForCountry = new ArrayList<>();
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumAvaiablesPackages() {
        return numAvaiablesPackages;
    }

    public void setNumAvaiablesPackages(int numAvaiablesPackages) {
        this.numAvaiablesPackages = numAvaiablesPackages;
    }

    public Double getPackageCost() {
        return packageCost;
    }

    public void setPackageCost(Double packageCost) {
        this.packageCost = packageCost;
    }

    public ArrayList<Integer> getNumAvaiablesUnitForService() {
        return numAvaiablesUnitForService;
    }

    public void setNumAvaiablesUnitForService(ArrayList<Integer> numAvaiablesUnitForService) {
        this.numAvaiablesUnitForService = numAvaiablesUnitForService;
    }

    public ArrayList<Integer> getLatencyForCountry() {
        return latencyForCountry;
    }

    public void setLatencyForCountry(ArrayList<Integer> latencyForCountry) {
        this.latencyForCountry = latencyForCountry;
    }

    public int getAvailableUnitsForService(int serviceIndex) {
        return numAvaiablesUnitForService.get(serviceIndex);
    }

    public void addPackages(int numPackages) {
        numAvaiablesPackages += numPackages;
    }
}
