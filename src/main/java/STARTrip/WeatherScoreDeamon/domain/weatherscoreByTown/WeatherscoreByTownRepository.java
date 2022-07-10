package STARTrip.WeatherScoreDeamon.domain.weatherscoreByTown;

import STARTrip.WeatherScoreDeamon.domain.weatherscore.Weatherscore;
import java.util.Optional;
import java.util.UUID;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherscoreByTownRepository extends JpaRepository<WeatherscoreByTown, UUID> {
    Optional<WeatherscoreByTown> findByAreacode(String areacode);
}
