package STARTrip.WeatherScoreDeamon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScoreScheduler {

    @Autowired
    private WeatherScoreByTownService weatherScoreByTownService;

    @Scheduled(fixedRate = 10800000) // 1h = 3600000ms 간격 설정
    public void weatherScoreScheduler(){
        weatherScoreByTownService.setWeatherscoreByTown();
    }
}

