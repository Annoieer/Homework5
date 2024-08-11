package homework_5.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSource {

    private final HikariConfig config = new HikariConfig(
            "/hikari.properties");
    private final HikariDataSource ds = new HikariDataSource(config);

    public DataSource() {
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
