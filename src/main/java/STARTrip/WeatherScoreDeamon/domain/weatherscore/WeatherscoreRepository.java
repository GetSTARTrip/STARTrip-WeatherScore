package STARTrip.WeatherScoreDeamon.domain.weatherscore;

import STARTrip.WeatherScoreDeamon.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeatherscoreRepository extends JpaRepository<Weatherscore, UUID> {
    Optional<Weatherscore> findByPlaceId(UUID placeId);
}