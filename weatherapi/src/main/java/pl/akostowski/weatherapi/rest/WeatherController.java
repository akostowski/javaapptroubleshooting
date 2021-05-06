package pl.akostowski.weatherapi.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class WeatherController {

    @Value("${pl.akostowski.troubleshooting.delay}")
    private long delay;

    List<String> weather = Arrays.asList("sunny", "rainy", "windy", "cloudy", "snowy", "stormy");

    @GetMapping("/")
    public String get() throws InterruptedException {
        Collections.shuffle(weather);
        Thread.sleep(delay);
        return weather.get(0);
    }
}
