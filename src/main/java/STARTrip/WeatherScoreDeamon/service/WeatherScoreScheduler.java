package STARTrip.WeatherScoreDeamon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScoreScheduler {

    @Autowired
    private WeatherScoreService weatherScoreService;

    @Scheduled(fixedRate = 500000) //500초 간격 설정
    public void weatherScoreScheduler(){
        weatherScoreService.setWeatherPlace();
    }
}

