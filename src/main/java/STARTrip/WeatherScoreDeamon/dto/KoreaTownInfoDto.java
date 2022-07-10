package STARTrip.WeatherScoreDeamon.dto;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KoreaTownInfoDto {

    @NotNull
    private String step1;

    private String step2;
    private String step3;

    @NotNull
    @Column(name = "grid_x")
    private String gridX;

    @NotNull
    @Column(name = "grid_y")
    private String gridY;

    @NotNull
    @Column(name = "longitude_hour")
    private String longitudeHour;

    @NotNull
    @Column(name = "latitude_hour")
    private String latitudeHour;

    @NotNull
    @Column(name = "longitude_Min")
    private String longitudeMin;

    @NotNull
    @Column(name = "latitude_min")
    private String latitudeMin;

    @NotNull
    @Column(name = "longitude_sec")
    private String longitudeSec;

    @NotNull
    @Column(name = "latitude_sec")
    private String latitudeSec;

    @NotNull
    @Column(name = "longitude_ms")
    private String longitudeMs;

    @NotNull
    @Column(name = "latitude_ms")
    private String latitudeMs;

}
