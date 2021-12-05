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
        Minus273(temperatureSeries);
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        actualLength = temperatureSeries.length;
    }

    public void Minus273(double[] temperatureSeries) {
        int TheLowest = -273;

        for (double temp:temperatureSeries) {
            if (temp <= TheLowest) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        ExceptionsHolder();
        double sum = 0;
        for (double num : temperatureSeries) {
            sum += num;
        }
        return sum / actualLength;
    }

    public void ExceptionsHolder() {
        if (actualLength == 0) {
            throw new IllegalArgumentException("Array of length 0");
        }
    }

    public double deviation() {
        ExceptionsHolder();

        double mean = this.average();
        double SD = 0;
        for (double num : temperatureSeries) {
            SD += Math.pow(num - mean, 2);
        }
        SD = Math.sqrt(SD / actualLength);
        return SD;
    }

    public double min() {
        ExceptionsHolder();

        double MinimalElement = Double.POSITIVE_INFINITY;

        for (double num : temperatureSeries) {
            if (num < MinimalElement) {
                MinimalElement = num;
            }
        }

        return MinimalElement;
    }

    public double max() {
        ExceptionsHolder();

        double MaxElement = Double.POSITIVE_INFINITY * -1;

        for (double num : temperatureSeries) {
            if (num > MaxElement) {
                MaxElement = num;
            }
        }
        return MaxElement;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        ExceptionsHolder();

        double CurClosest = Double.POSITIVE_INFINITY;

        for (double num : temperatureSeries) {
            if (Math.abs(num - tempValue) < Math.abs(CurClosest - tempValue)) {
                CurClosest = num;
            }
        }
        return CurClosest;
    }

    public double[] findTempsLessThen(double tempValue) {
        return FindTempLesOrGreat(tempValue, "<");
    }

    public double[] findTempsGreaterThen(double tempValue) {

        return FindTempLesOrGreat(tempValue, ">");
    }

    public double[] FindTempLesOrGreat(double tempValue, String sign) {
        ExceptionsHolder();

        double [] result = new double[temperatureSeries.length];
        int counter = 0;

        if (sign.equals("<")) {
            for (double num : temperatureSeries) {
                if (num < tempValue) {
                    result[counter] = num;
                    counter ++;
                }
            }
        } else {
            for (double num : temperatureSeries) {
                if (num > tempValue) {
                    result[counter] = num;
                    counter ++;
                }
            }
        }

        return Arrays.copyOf(temperatureSeries, counter - 1);
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        Minus273(temps);
        int newLength = 0;
        int tempsLength = temps.length;
        if (actualLength + tempsLength > temperatureSeries.length) {
            while (newLength < actualLength + tempsLength) {
                newLength = temperatureSeries.length * 2;
            }
        }

        double [] NewResult = Arrays.copyOf(temperatureSeries, newLength);
        int index = 0;
        for (double temp : temps) {
            NewResult[actualLength + index] = temp;
            index ++;
        }
        temperatureSeries = NewResult;
        actualLength += tempsLength;

        int sum = 0;
        for (int i = 0; i < actualLength; i++) {
            sum += temperatureSeries[i];
        }
        return sum;
    }
}
