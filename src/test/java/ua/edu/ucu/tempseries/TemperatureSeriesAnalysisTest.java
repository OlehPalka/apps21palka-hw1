package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStats() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();

        assertEquals(stats.getAvgTemp(), -1.0, 0.00001);
        assertEquals(stats.getDevTemp(), 0.0, 0.00001);
        assertEquals(stats.getMaxTemp(), -1.0, 0.00001);
        assertEquals(stats.getMinTemp(), -1.0, 0.00001);
    }

    @Test
    public void testAdding() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int result = seriesAnalysis.addTemps(2.0);

        assertEquals(result, 1.0, 0.00001);
    }

    @Test
    public void testCreatingWithoutArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        assertEquals(seriesAnalysis.getActualLength(), 0, 0.00001);
    }

    @Test
    public void testFindingClosest() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(seriesAnalysis.findTempClosestToZero(), -1.0, 0.00001);
    }


    @Test
    public void testFindingLessGreater() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expectedOutput = {-1.0};

        assertEquals(seriesAnalysis.findTempsGreaterThen(-5.0)[0], expectedOutput[0], 0.00001);
        assertEquals(seriesAnalysis.findTempsLessThen(5.0)[0], expectedOutput[0], 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }
    

}
