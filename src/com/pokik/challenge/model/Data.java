package com.pokik.challenge.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data {
    private int numProviders;
    private int numServices;
    private int numCountries;
    private int numProjects;
    private ArrayList<String> services;
    private ArrayList<String> countries;
    private ArrayList<Provider> providers;
    private ArrayList<Project> projects;

    public Data()
    {
        services = new ArrayList<>();
        countries = new ArrayList<>();
        providers = new ArrayList<>();
        projects = new ArrayList<>();
    }

    public int getNumProviders() {
        return numProviders;
    }

    public void setNumProviders(int numProviders) {
        this.numProviders = numProviders;
    }

    public int getNumServices() {
        return numServices;
    }

    public void setNumServices(int numServices) {
        this.numServices = numServices;
    }

    public int getNumCountries() {
        return numCountries;
    }

    public void setNumCountries(int numCountries) {
        this.numCountries = numCountries;
    }

    public int getNumProjects() {
        return numProjects;
    }

    public void setNumProjects(int numProjects) {
        this.numProjects = numProjects;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<Provider> getProviders() {
        return providers;
    }

    public void setProviders(ArrayList<Provider> providers) {
        this.providers = providers;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
