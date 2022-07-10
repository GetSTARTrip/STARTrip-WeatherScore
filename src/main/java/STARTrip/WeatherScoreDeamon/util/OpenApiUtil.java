package STARTrip.WeatherScoreDeamon.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
public class OpenApiUtil {

    @Value("${apikey}")
    private String DATA_GO_KR_API_KEY;

    public String[] getTime(LocalDateTime currentTime) {
        String dateInfo [] = new String [2];

        String yyyyMMdd = currentTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hh = String.valueOf(currentTime.getHour());

        dateInfo[0] = yyyyMMdd;
        dateInfo[1] = hh;

        return dateInfo;
    }


    // 기준시간을 기준으로 전날 데이터를 가져올지 결정하는 메소드
    public LinkedHashMap getDateTime(String datePattern){

        String [] dateAndTime = new String [2]; // dateAndtime[0] : yyyMMdd, dateAndtime[1] = HH

        //현재시간
        LocalDateTime currentTime = LocalDateTime.now();
        dateAndTime = getTime(currentTime);
        String date = dateAndTime[0];
        String time = dateAndTime[1];

        LocalDateTime nowDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        // 조회 기준 시간
        LocalDateTime standardTime [] = new LocalDateTime[8];
        for(int i =0; i<8; i++){
            standardTime[i] = LocalDateTime.of(LocalDate.now(), LocalTime.of( 2+3*i, 0, 0)); // 2, 5, 8, 11, 14, 17, 20, 23
            //log.info(String.valueOf(standardTime[i]));
        }

        for(int i =0; i<8; i++){
            if(i == 7){
                date = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0, 0))
                    .format(DateTimeFormatter.ofPattern(datePattern));
                time = "2300";
                break;
            }

            if(standardTime[i].isBefore(nowDateTime) && standardTime[i+1].isAfter(nowDateTime)){
                String hh = (String.valueOf(standardTime[i].getHour()));
                log.info(hh);
                date = LocalDateTime.of(LocalDate.now(), LocalTime.of(Integer.parseInt(hh), 0, 0))
                    .format(DateTimeFormatter.ofPattern(datePattern));
                time = hh + "00";
                break;
            }
        }


//        // 기준 시간 (0500 부터 예보조회가 가능함)
//        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(5, 0, 0));
//        // 현재 시각
//        LocalDateTime nowDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//
//        String date, time;
//        if (startDateTime.isBefore(nowDateTime)){
//            // 오늘 0500 이전이면 전날 최신 데이터를 가져온다
//            date = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 0, 0))
//                .format(DateTimeFormatter.ofPattern(datePattern));
//            time = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 0, 0))
//                .format(DateTimeFormatter.ofPattern("HHmm"));
//        } else {
//            // 오늘 0500 이후이면 0500 데이터를 가져온다
//            date = LocalDateTime.of(LocalDate.now(), LocalTime.of(05, 0, 0))
//                .format(DateTimeFormatter.ofPattern(datePattern));
//            time = LocalDateTime.of(LocalDate.now(), LocalTime.of(05, 0, 0))
//                .format(DateTimeFormatter.ofPattern("HHmm"));
//        }

        log.info("시각: " + date + ", " + time);
        LinkedHashMap result = new LinkedHashMap<>();
        result.put("date", date);
        result.put("time", time);
        return result;
    }
}
