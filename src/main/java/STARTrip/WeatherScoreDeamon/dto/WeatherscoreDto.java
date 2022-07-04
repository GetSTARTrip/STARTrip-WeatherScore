package STARTrip.WeatherScoreDeamon.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WeatherscoreDto {

    @NotNull
    private UUID placeId;
    private String placeName;
    private Double latitude;
    private Double longitude;
    private String score;
}