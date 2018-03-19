package com.pokik.challenge.model;

import java.util.ArrayList;

public class Project {
    private int penalty;
    private String countryName;
    private ArrayList<Integer> servicesUnitNeeded;

    private ArrayList<Integer> coveredServices;

    public ArrayList<Integer> getCoveredServices() {
        return coveredServices;
    }

    public ArrayList<ProviderRegionTakenResource> getTakenResources() {
        return takenResources;
    }

    private ArrayList<ProviderRegionTakenResource> takenResources = new ArrayList<>();

    public static class ProviderRegionTakenResource {
        ProviderRegion providerRegion;
        int takenResource;

        public ProviderRegion getProviderRegion() {
            return providerRegion;
        }

        public void setProviderRegion(ProviderRegion providerRegion) {
            this.providerRegion = providerRegion;
        }

        public int getTakenPackages() {
            return takenResource;
        }

        public void setTakenPackages(int takenResource) {
            this.takenResource = takenResource;
        }
    }

    public Project(int numberOfServices) {
        servicesUnitNeeded = new ArrayList<>();
        coveredServices = new ArrayList<>(numberOfServices);
        for (int i=0; i<numberOfServices; i++) {
            coveredServices.add(0);
        }
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public ArrayList<Integer> getServicesUnitNeeded() {
        return servicesUnitNeeded;
    }

    public void setServicesUnitNeeded(ArrayList<Integer> servicesUnitNeeded) {
        this.servicesUnitNeeded = servicesUnitNeeded;
    }

    public boolean isProjectCovered() {
        for (int i = 0; i < coveredServices.size(); i++) {
            if (coveredServices.get(i) < servicesUnitNeeded.get(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isServiceCovered(int index) {
        return servicesUnitNeeded.get(index) <= coveredServices.get(index);
    }

    public int getBestServiceToCover() {
        int bestService = -1;
        int maxNeeded = 0;
        for (int serviceIndex=0; serviceIndex<servicesUnitNeeded.size(); serviceIndex++) {
            if (servicesUnitNeeded.get(serviceIndex)-coveredServices.get(serviceIndex) > maxNeeded) {
                maxNeeded = servicesUnitNeeded.get(serviceIndex)-coveredServices.get(serviceIndex);
                bestService = serviceIndex;
            }
        }

        return bestService;
    }

    public void takeResourcesForService(ProviderRegion region, int serviceIndex) {
        int neededUnitsForService = servicesUnitNeeded.get(serviceIndex);

        int regionAvailabilityForService = region.getAvailableUnitsForService(serviceIndex);
        int totalUnitsTaken = Integer.min(regionAvailabilityForService*region.getNumAvaiablesPackages(), (int)(Math.ceil((float)neededUnitsForService/(float)regionAvailabilityForService)*regionAvailabilityForService));
        int takenPackages = totalUnitsTaken/regionAvailabilityForService;

        for (int i=0; i<servicesUnitNeeded.size(); i++) {
            servicesUnitNeeded.set(i, getServicesUnitNeeded().get(i) - takenPackages*region.getNumAvaiablesUnitForService().get(i));
        }

        region.addPackages(-takenPackages);

        ProviderRegionTakenResource takenResource = new ProviderRegionTakenResource();
        takenResource.setProviderRegion(region);
        takenResource.setTakenPackages(takenPackages);
        getTakenResources().add(takenResource);
    }

    public void mergeProviders() {
        ArrayList<ProviderRegionTakenResource> finalTakenResources = new ArrayList<>();
        for (ProviderRegionTakenResource r: getTakenResources()) {
            boolean ce = false;
            for (ProviderRegionTakenResource rr: finalTakenResources) {
                if (rr.getProviderRegion().equals(r.getProviderRegion())) {
                    rr.setTakenPackages(rr.getTakenPackages()+r.getTakenPackages());
                    ce = true;
                    break;
                }
            }

            if (!ce) {
                finalTakenResources.add(r);
            }
        }

        takenResources = finalTakenResources;
    }
}
