package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int actualLength = 0;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[] {};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkTheLowest(temperatureSeries);
        this.temperatureSeries = Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
        actualLength = temperatureSeries.length;
    }

    public int getActualLength() {
        return actualLength;
    }

    public void checkTheLowest(double[] temperatureSeries) {
        for (double temp:temperatureSeries) {
            int theLowest = -273;
            if (temp <= theLowest) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        exceptionsHolder();
        double sum = 0;
        for (double num : temperatureSeries) {
            sum += num;
        }
        return sum / actualLength;
    }

    public void exceptionsHolder() {
        if (actualLength == 0) {
            throw new IllegalArgumentException("Array of length 0");
        }
    }

    public double deviation() {
        exceptionsHolder();

        double mean = this.average();
        double sd = 0;
        for (double num : temperatureSeries) {
            sd += (num - mean) * (num - mean);
        }
        sd = Math.sqrt(sd / actualLength);
        return sd;
    }

    public double min() {
        exceptionsHolder();

        double minimalElement = Double.POSITIVE_INFINITY;

        for (double num : temperatureSeries) {
            if (num < minimalElement) {
                minimalElement = num;
            }
        }

        return minimalElement;
    }

    public double max() {
        exceptionsHolder();

        double maxElement = Double.POSITIVE_INFINITY * -1;

        for (double num : temperatureSeries) {
            if (num > maxElement) {
                maxElement = num;
            }
        }
        return maxElement;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        exceptionsHolder();

        double curClosest = Double.POSITIVE_INFINITY;

        for (double num : temperatureSeries) {
            if (Math.abs(num - tempValue) < Math.abs(curClosest - tempValue)) {
                curClosest = num;
            }
        }
        return curClosest;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTempLesOrGreat(tempValue, "<");
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTempLesOrGreat(tempValue, ">");
    }

    public double[] findTempLesOrGreat(double tempValue, String sign) {
        exceptionsHolder();

        double [] result = new double[temperatureSeries.length];
        int counter = 0;

        if (sign.equals("<")) {
            for (double num : temperatureSeries) {
                if (num < tempValue) {
                    result[counter] = num;
                    counter++;
                }
            }
        } else {
            for (double num : temperatureSeries) {
                if (num > tempValue) {
                    result[counter] = num;
                    counter++;
                }
            }
        }

        return Arrays.copyOf(result, counter);
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        checkTheLowest(temps);
        int newLength = 0;
        int tempsLength = temps.length;
        if (actualLength + tempsLength > temperatureSeries.length) {
            while (newLength < actualLength + tempsLength) {
                newLength = temperatureSeries.length * 2;
            }
        }

        double [] newResult = Arrays.copyOf(temperatureSeries, newLength);
        int index = 0;
        for (double temp : temps) {
            newResult[actualLength + index] = temp;
            index++;
        }
        temperatureSeries = newResult;
        actualLength += tempsLength;

        int sum = 0;
        for (int i = 0; i < actualLength; i++) {
            sum += temperatureSeries[i];
        }
        return sum;
    }
}
