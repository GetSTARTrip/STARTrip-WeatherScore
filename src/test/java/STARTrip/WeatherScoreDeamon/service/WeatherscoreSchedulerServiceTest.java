package STARTrip.WeatherScoreDeamon.service;


import STARTrip.WeatherScoreDeamon.domain.place.Place;
import STARTrip.WeatherScoreDeamon.domain.place.PlaceRepository;
import STARTrip.WeatherScoreDeamon.domain.weatherscore.Weatherscore;
import STARTrip.WeatherScoreDeamon.domain.weatherscore.WeatherscoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class WeatherscoreSchedulerServiceTest {

    @Autowired
    private WeatherScoreService weatherScoreService;

    @Autowired
    private WeatherscoreRepository weatherscoreRepository;

    @Autowired
    private PlaceRepository placeRepository;


    private String placeId = "dbdf46ab-4a62-4338-92af-e7ced3be5f0a";





    @DisplayName("weatherscore 생성 테스트")
    @Test
    void createWeatherscore() {

        Optional<Place> place = placeRepository.findById(UUID.fromString(placeId));

        weatherScoreService.createWeatherscore(place.get());
        List<Weatherscore> weatherscores = weatherscoreRepository.findAll();



        Optional<Weatherscore> weatherscore = weatherscoreRepository.findByPlaceId(place.get().getId());
        assertThat(weatherscore.get().getPlaceId().equals(place.get().getId()));

    }




}
