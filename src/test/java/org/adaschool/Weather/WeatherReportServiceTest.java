package org.adaschool.Weather;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherReportService = new WeatherReportService(restTemplate);
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        double latitude = 37.8267;
        double longitude = -122.4233;

        WeatherApiResponse simulatedApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        simulatedApiResponse.setMain(main);

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=37.8267&lon=-122.4233&appid=424efd794bb4c2b5db87843b790e7ab0";

        when(restTemplate.getForObject(apiUrl, WeatherApiResponse.class)).thenReturn(simulatedApiResponse);

        WeatherReport response = weatherReportService.getWeatherReport(latitude, longitude);

        verify(restTemplate, times(1)).getForObject(apiUrl, WeatherApiResponse.class);


        assertEquals(25.0, response.getTemperature(), 0.01);
        assertEquals(60.0, response.getHumidity(), 0.01);
    }
}