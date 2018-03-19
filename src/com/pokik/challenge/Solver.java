package com.pokik.challenge;

import com.pokik.challenge.model.Data;
import com.pokik.challenge.model.Project;
import com.pokik.challenge.model.Provider;
import com.pokik.challenge.model.ProviderRegion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class Solver {
    private String filenameInput;
    private String filenameOutput;
    private Data data;

    public Solver(String filename) {
        filenameInput = filename + ".in";
        filenameOutput = filename + ".out";
        data = readData();
    }

    public Data readData() {
        Data data = new Data();
        try {
            Scanner scanner = new Scanner(new File(filenameInput));
            scanner.useLocale(Locale.US);
            data.setNumProviders(scanner.nextInt());
            data.setNumServices(scanner.nextInt());
            data.setNumCountries(scanner.nextInt());
            data.setNumProjects(scanner.nextInt());
            for (int i = 0; i < data.getNumServices(); i++) {
                data.getServices().add(scanner.next());
            }
            for (int i = 0; i < data.getNumCountries(); i++) {
                data.getCountries().add(scanner.next());
            }
            for (int i = 0; i < data.getNumProviders(); i++) {
                Provider provider = new Provider();
                provider.setName(scanner.next());
                try {
                    provider.setNumRegions(scanner.nextInt());
                } catch (Exception e) {
                    "ciaone".charAt(0);
                }

                for (int j = 0; j < provider.getNumRegions(); j++) {
                    ProviderRegion providerRegion = new ProviderRegion(provider);
                    providerRegion.setName(scanner.next());
                    providerRegion.setNumAvaiablesPackages(scanner.nextInt());
                    providerRegion.setPackageCost(scanner.nextDouble());
                    for (int k = 0; k < data.getNumServices(); k++) {
                        providerRegion.getNumAvaiablesUnitForService().add(scanner.nextInt());
                    }
                    for (int k = 0; k < data.getNumCountries(); k++) {
                        providerRegion.getLatencyForCountry().add(scanner.nextInt());
                    }
                    provider.getProviderRegions().add(providerRegion);
                }

                data.getProviders().add(provider);
            }
            for (int i = 0; i < data.getNumProjects(); i++) {
                Project project = new Project(data.getNumServices());
                project.setPenalty(scanner.nextInt());
                project.setCountryName(scanner.next());
                for (int j = 0; j < data.getNumServices(); j++) {
                    project.getServicesUnitNeeded().add(scanner.nextInt());
                }
                data.getProjects().add(project);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void solve() {
        readData();

        for (Project project : data.getProjects()) {
            assignPackagesToProject(project);
        }

        try {
            printSolution();
        } catch (Exception e) {
        }

        System.out.println();
    }

    private void assignPackagesToProject(Project project) {
        System.out.println(data.getProjects().indexOf(project));
        boolean projectCantBeCovered = false;

        while (!projectCantBeCovered) {
            int bestServiceToCover = project.getBestServiceToCover();
            if(bestServiceToCover == -1) {
                break;
            }

            while (!project.isServiceCovered(bestServiceToCover)) {
                double minPrice = Double.MAX_VALUE;
                ProviderRegion bestRegion = null;

                for (Provider provider : data.getProviders()) {
                    for (ProviderRegion providerRegion : provider.getProviderRegions()) {
                        if (providerRegion.getAvailableUnitsForService(bestServiceToCover)*providerRegion.getNumAvaiablesPackages() > 0) {
                            double price = providerRegion.getPackageCost();
                            if (price < minPrice) {
                                minPrice = price;
                                bestRegion = providerRegion;
                            }
                        }
                    }
                }

                if (bestRegion == null) {
                    projectCantBeCovered = true;
                    break;
                }

                project.takeResourcesForService(bestRegion, bestServiceToCover);
            }

            if (projectCantBeCovered) {
                for (Project.ProviderRegionTakenResource providerRegionTakenResource : project.getTakenResources()) {
                    providerRegionTakenResource.getProviderRegion().addPackages(providerRegionTakenResource.getTakenPackages());
                }

                project.getTakenResources().clear();
            }
        }
    }

    public void printSolution() throws FileNotFoundException {
        PrintStream printStream = new PrintStream(new File(filenameOutput));

        for (Project project: data.getProjects()) {
            project.mergeProviders();

            boolean firstProvider = true;

            for (Project.ProviderRegionTakenResource takenResource: project.getTakenResources()) {
                if (!firstProvider) {
                    printStream.print(" ");
                }
                firstProvider = false;
                printStream.print(data.getProviders().indexOf(takenResource.getProviderRegion().getProvider()));
                printStream.print(" "+takenResource.getProviderRegion().getProvider().getProviderRegions().indexOf(takenResource.getProviderRegion()));
                printStream.print(" "+takenResource.getTakenPackages());
            }
            printStream.println();
        }

        printStream.flush();
        printStream.close();
    }
}
