package STARTrip.WeatherScoreDeamon.service;

import STARTrip.WeatherScoreDeamon.domain.place.Place;
import STARTrip.WeatherScoreDeamon.domain.place.PlaceRepository;
import STARTrip.WeatherScoreDeamon.domain.weatherscore.Weatherscore;
import STARTrip.WeatherScoreDeamon.domain.weatherscore.WeatherscoreRepository;
import STARTrip.WeatherScoreDeamon.dto.WeatherscoreDto;
import STARTrip.WeatherScoreDeamon.util.weather.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WeatherScoreService {
    private final PlaceRepository placeRepository;
    private final WeatherscoreRepository weatherscoreRepository;
    private WeatherApi weatherapi;

    private final List<Place> resultPlaces = new ArrayList<>();
    private String weatherScore;
    private String userWeather;

    private String currentLongitude;

    private UUID currentPlaceId;

    @Autowired
    public WeatherScoreService(PlaceRepository placeRepository, WeatherscoreRepository weatherscoreRepository, WeatherApi weatherApi){
       this.weatherapi = weatherApi;
        this.placeRepository = placeRepository;
        this.weatherscoreRepository = weatherscoreRepository;
    }

    public void setWeatherPlace() {
        List<Place> places = placeRepository.findAll();
        for (Place place : places) {

            // getWeatherscoreAsPlace 함수에서 최초의 Weatherscore가 만들어진다
            Weatherscore weatherscore = getWeatherscoreAsPlace(place.getId());
            String score = searchWeatherScore(place);
            weatherscore.updateScore(score);
            weatherscoreRepository.save(weatherscore);
        }
    }

    public Weatherscore getWeatherscoreAsPlace(UUID placeId){
        Optional<Weatherscore> weatherscore = weatherscoreRepository.findByPlaceId(placeId);
        if(weatherscore.isEmpty()) {
            Place place = getPlace(placeId);
            weatherscore = Optional.ofNullable(createWeatherscore(place));
        }

        weatherscore = weatherscoreRepository.findByPlaceId(placeId);

        return weatherscore.get();
    }

    public String searchWeatherScore(Place place) {
        String score = "";
        String latitude = String.valueOf(place.getLatitude());
        String longitude;
        try{
            longitude = String.valueOf(place.getLongitude());
        }catch (NullPointerException e){
            longitude = "0.0000000";
        }

        try {
            score = weatherapi.getApiResult(latitude, longitude);
        }catch(IOException e){
            e.printStackTrace();
        }
        return score;
    }

    public Weatherscore createWeatherscore(Place place){

        String latitude = String.valueOf(place.getLatitude());
        String longitude = String.valueOf(place.getLongitude());

//        WeatherscoreDto dto = new WeatherscoreDto();
//        dto.setLatitude(Double.valueOf(latitude));
//        dto.setLongitude(Double.valueOf(longitude));
//        dto.setPlaceId(place.getId());
//        dto.setPlaceName(place.getPlaceName());
//        dto.setScore("0");

        Weatherscore weatherscore = Weatherscore.builder()
                        .id(UUID.randomUUID())
                        .placeId(place.getId())
                        .latitude(place.getLatitude())
                        .longitude(place.getLongitude())
                        .placeName(place.getPlaceName())
                        .score("0")
                .build();

        weatherscoreRepository.save(weatherscore);

        return weatherscoreRepository.findByPlaceId(place.getId()).get();
    }

    public Weatherscore getWeatherscore(UUID weatherscoreId){
        Optional<Weatherscore> weatherscore = weatherscoreRepository.findById(weatherscoreId);
        if(weatherscore.isEmpty()) {
            throw new RuntimeException("해당 weatherscore가 없습니다.");
        }
        return weatherscore.get();
    }





    public Place getPlace(UUID placeId){
        Optional<Place> place = placeRepository.findById(placeId);
        if(place.isEmpty()) {
            throw new RuntimeException("해당 place가 없습니다.");
        }
        return place.get();
    }



}
