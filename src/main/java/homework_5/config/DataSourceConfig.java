package homework_5.config;

import homework_5.connection.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

public class DataSourceConfig {
    @Autowired
    protected DataSource dataSource;
}
