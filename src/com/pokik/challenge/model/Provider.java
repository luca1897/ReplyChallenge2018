package com.pokik.challenge.model;

import java.util.ArrayList;

public class Provider {
    private String name;
    private int numRegions;
    private ArrayList<ProviderRegion> providerRegions;

    public Provider() {
        providerRegions = new ArrayList<>();
    }

    public Provider(String name, int numRegions) {
        this.name = name;
        this.numRegions = numRegions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumRegions() {
        return numRegions;
    }

    public void setNumRegions(int numRegions) {
        this.numRegions = numRegions;
    }

    public ArrayList<ProviderRegion> getProviderRegions() {
        return providerRegions;
    }

    public void setProviderRegions(ArrayList<ProviderRegion> providerRegions) {
        this.providerRegions = providerRegions;
    }
}
