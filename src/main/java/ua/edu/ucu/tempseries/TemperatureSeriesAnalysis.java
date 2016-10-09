package ua.edu.ucu.tempseries;

import java.util.Arrays;

public class TemperatureSeriesAnalysis {
    double[] temperatureSeries;
    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The range is empty!");
        }
        double result = 0;
        for (double temperature: temperatureSeries) {
            result += temperature;
        }
        return result / temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The range is empty!");
        }
        double mean = 0;
        for (double temperature: temperatureSeries) {
            mean += temperature;
        }
        mean = mean / temperatureSeries.length;
        double variance = 0;
        for (double temp: temperatureSeries) {
            variance = (temp - mean) * (temp - mean);
        }
        return Math.sqrt(Math.sqrt(variance));
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The range is empty!");
        }
        double minEl = 1005000.0D;
        for (double temp: temperatureSeries) {
            if (minEl > temp) {
                minEl = temp;
            }
        }
        return minEl;
    }

    public double max() {
        double maxEl = -1005000.0D;
        for (double temp : temperatureSeries) {
            if (maxEl < temp) {
                maxEl = temp;
            }
        }
        return maxEl;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The range is empty!");
        }
        double curClosest = 100500.0D;
        for (double element: temperatureSeries) {
            if (Math.abs(element) < Math.abs(curClosest)) {
                curClosest = element;
                if (element + curClosest == 0) {
                    curClosest = Math.abs(curClosest);
                }
            }
        }
        return curClosest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The range is empty!");
        }
        double[] copyTemperatureSeries = temperatureSeries;
        double[] newList = new double[copyTemperatureSeries.length];
        Arrays.sort(copyTemperatureSeries);
        double min = 10005000.0D;
        for (int i = 0; i < copyTemperatureSeries.length; i++) {
            newList[i] = tempValue - copyTemperatureSeries[i];
            if (Math.abs(newList[i]) <= Math.abs(min)) {
                min = newList[i];
            }
        }
        double result = 0;
        for (int j = 0; j < newList.length; j++) {
            if (newList[j] == min) {
                result = copyTemperatureSeries[j];
            }
        }
        return result;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] newArray = new double[temperatureSeries.length];
        int indexNewArray = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                newArray[indexNewArray] = temperatureSeries[i];
                indexNewArray += 1;
            }
        }
        double[] result = new double[indexNewArray];
        for (int j = 0; j < indexNewArray; j++) {
            result[j] = newArray[j];
        }
        return result;

    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] newArray = new double[temperatureSeries.length];
        int indexNewArray = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (tempValue <= temperatureSeries[i]) {
                newArray[indexNewArray] = temperatureSeries[i];
                indexNewArray += 1;
            }
        }
        double[] result = new double[indexNewArray];
        for (int j = 0; j < result.length; j++) {
            result[j] = newArray[j];
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("The range is empty!");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        int freeRoom = 0;

        for (double element: temperatureSeries) {
            if (element == 0.0) {
                freeRoom += 1;
            }
        }
        int startIndex = temperatureSeries.length - freeRoom;
        while (freeRoom < temps.length) {
            double[] newArr = new double[temperatureSeries.length * 2];
            for (int i = 0;i < temperatureSeries.length; i++) {
                newArr[i] = temperatureSeries[i];
            }
            temperatureSeries = newArr;
            freeRoom += temperatureSeries.length;
        }
        int index = 0;
        for (int j = startIndex; j < startIndex + temps.length;j++) {
            temperatureSeries[j] = temps[index];
            index += 1;
        }
        int result = 0;
        for (double element: temperatureSeries) {
            result += element;
        }
        return result;
    }
}
