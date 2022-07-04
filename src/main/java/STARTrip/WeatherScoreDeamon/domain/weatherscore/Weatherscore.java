package STARTrip.WeatherScoreDeamon.domain.weatherscore;

import STARTrip.WeatherScoreDeamon.domain.place.Place;
import STARTrip.WeatherScoreDeamon.dto.WeatherscoreDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "weatherscore")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Weatherscore {

    @Id
    @Column(name = "weatherscore_id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "place_id")
    private UUID placeId;

    @Column(name = "place_name")
    private String placeName;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String score;

    public static Weatherscore of (WeatherscoreDto dto){
        return  Weatherscore.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .score(dto.getScore())
                .build();
    }

    public void update(WeatherscoreDto dto) {
        this.placeId = dto.getPlaceId();
        this.placeName = dto.getPlaceName();
        this.latitude = dto.getLatitude();
        this.longitude = dto.getLongitude();
    }

    public void updateScore (String score){
        this.score = score;
    }
}