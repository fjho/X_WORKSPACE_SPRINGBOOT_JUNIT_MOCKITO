package com.ust.formacion.unit_testing.business;

import java.util.Arrays;

import com.ust.formacion.unit_testing.data.DataService;

public class BusinessService {

    private DataService dataService;

    public BusinessService(DataService dataService) {
        this.dataService = dataService;
    }

    public BusinessService() {
    }

    public int calculateSum(int[] data) {
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    public int calculateSumUsingDataService() {
        int[] data = dataService.getData();
        int sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    public int calculateSumFunctional(int[] data) {
        return Arrays.stream(data).reduce(Integer::sum).orElse(0);
    }

    public int calculateSumFunctional() {
        int[] data = dataService.getData();
        return Arrays.stream(data).reduce(Integer::sum).orElse(0);
    }
}
