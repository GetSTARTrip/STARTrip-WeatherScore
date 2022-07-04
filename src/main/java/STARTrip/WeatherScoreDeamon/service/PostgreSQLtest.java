package STARTrip.WeatherScoreDeamon.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
@Component
public class PostgreSQLtest  implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());


            //Statement statement = connection.createStatement();
            //String sql = "select * from weatherscore;";
            //ResultSet rs = statement.executeQuery(sql);

            //while(rs.next()){
            //    log.info(rs.getInt(1) + "\t" + rs.getInt(2));
            //}


        }
        //jdbcTemplate.execute("INSERT INTO weather VALUES (3, '3')");
    }
}