package STARTrip.WeatherScoreDeamon.domain.koreaTownInfo;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoreaTownInfoRepository extends JpaRepository<KoreaTownInfo, String> {

}
