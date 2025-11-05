package com.ust.formacion.unit_testing.business;

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
}
