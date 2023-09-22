package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    private WeatherReportController weatherReportController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherReportController = new WeatherReportController(weatherReportService);
    }

    @Test
    public void testGetWeatherReport() {
        double latitude = 37.8267;
        double longitude = -122.4233;
        WeatherReport mockReport = new WeatherReport();
        mockReport.setTemperature(25.0);
        mockReport.setHumidity(60.0);

        when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockReport);

        WeatherReport response = weatherReportController.getWeatherReport(latitude, longitude);

        verify(weatherReportService, times(1)).getWeatherReport(latitude, longitude);
        assertEquals(25.0, response.getTemperature());
        assertEquals(60.0, response.getHumidity());
    }
}