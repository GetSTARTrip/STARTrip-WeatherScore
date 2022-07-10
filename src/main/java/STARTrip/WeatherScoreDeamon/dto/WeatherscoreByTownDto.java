package STARTrip.WeatherScoreDeamon.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherscoreByTownDto {

    private String cityName;
    private String weatherScore;
    private String areacode;

}
