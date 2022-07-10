package STARTrip.WeatherScoreDeamon.service;

import STARTrip.WeatherScoreDeamon.domain.koreaTownInfo.KoreaTownInfo;
import STARTrip.WeatherScoreDeamon.domain.koreaTownInfo.KoreaTownInfoRepository;
import STARTrip.WeatherScoreDeamon.domain.place.Place;
import STARTrip.WeatherScoreDeamon.domain.place.PlaceRepository;
import STARTrip.WeatherScoreDeamon.domain.weatherscore.Weatherscore;
import STARTrip.WeatherScoreDeamon.domain.weatherscore.WeatherscoreRepository;
import STARTrip.WeatherScoreDeamon.domain.weatherscoreByTown.WeatherscoreByTown;
import STARTrip.WeatherScoreDeamon.domain.weatherscoreByTown.WeatherscoreByTownRepository;
import STARTrip.WeatherScoreDeamon.dto.KoreaTownInfoDto;
import STARTrip.WeatherScoreDeamon.dto.WeatherscoreByTownDto;
import STARTrip.WeatherScoreDeamon.dto.WeatherscoreDto;
import STARTrip.WeatherScoreDeamon.util.weather.WeatherApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class WeatherScoreByTownService {

    private final WeatherscoreByTownRepository weatherscoreByTownRepository;
    private final KoreaTownInfoRepository koreaTownInfoRepository;

    @Autowired
    private WeatherApi weatherApi;

    private final List<Place> resultPlaces = new ArrayList<>();

    @Autowired
    public WeatherScoreByTownService(WeatherscoreByTownRepository weatherscoreByTownRepository,
        KoreaTownInfoRepository koreaTownInfoRepository,
        WeatherApi weatherApi){
        this.weatherscoreByTownRepository = weatherscoreByTownRepository;
        this.koreaTownInfoRepository = koreaTownInfoRepository;
        this.weatherApi = weatherApi;
    }

    public void setWeatherscoreByTown() {

        // 저장된 기상청xml Data 추출
        List<KoreaTownInfo> koreaTownInfos = koreaTownInfoRepository.findAll();
        for ( KoreaTownInfo koreaTownInfo : koreaTownInfos){

            //get... 에서 최초의 WeatherscoreByTown 생성
            WeatherscoreByTown weatherscoreByTown = getWeathersocreByTownAsKoreaTownInfo(koreaTownInfo.getId(), koreaTownInfo);
            String score = searchWeatherscoreByKoreaTownInfo(koreaTownInfo);
            log.info("점수 추출결과: " + score);
            weatherscoreByTown.updateScore(score);
            weatherscoreByTownRepository.save(weatherscoreByTown);
        }
    }

    public WeatherscoreByTown getWeathersocreByTownAsKoreaTownInfo(String areacode, KoreaTownInfo koreaTownInfo){
        Optional<WeatherscoreByTown> weatherscoreByTown = weatherscoreByTownRepository.findByAreacode(areacode);
        if(weatherscoreByTown.isEmpty()) {
            createWeatherscore(koreaTownInfo);
            weatherscoreByTown = weatherscoreByTownRepository.findByAreacode(areacode);
        }
        return weatherscoreByTown.get();
    }

    public String searchWeatherscoreByKoreaTownInfo(KoreaTownInfo koreaTownInfo) {

        String score= "";
        log.info("위치: " + koreaTownInfo.getGridX() + " " + koreaTownInfo.getGridY());
        try {
            score = weatherApi.getApiResult(koreaTownInfo.getGridX() , koreaTownInfo.getGridY());
        }catch(Exception e){
            e.getMessage();
        }
        return score;
    }


    public void createWeatherscore(KoreaTownInfo koreaTownInfo){

        String cityName = koreaTownInfo.getStep1() + " " + koreaTownInfo.getStep2();

        WeatherscoreByTownDto dto = new WeatherscoreByTownDto();
        dto.setAreacode(koreaTownInfo.getId());
        dto.setCityName(cityName);
        dto.setWeatherScore("0");

        WeatherscoreByTown weatherscoreByTown = WeatherscoreByTown.of(dto);
        weatherscoreByTownRepository.save(weatherscoreByTown);
    }


}
