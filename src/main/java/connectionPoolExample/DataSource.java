package connectionPoolExample;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

// этот класс нужен для многопоточной работы с соединениями к базе данных
public class DataSource {

    private static volatile DataSource dataSource;
    private ComboPooledDataSource cpds;

    private DataSource() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();  // пулл соединений Connection
        cpds.setDriverClass("org.postgresql.Driver"); // регистрация драйвера
        cpds.setJdbcUrl("jdbc:postgreesql://localhost:5432/akio889DB");
        cpds.setUser("akio889");
        cpds.setPassword("дущтфквщ09");
        cpds.setMinPoolSize(1);
        cpds.setMaxPoolSize(10);
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null) {
                    try {
                        dataSource = new DataSource();
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }

    public Connection connection () throws SQLException {
        return cpds.getConnection(); // получение соединения из пула
    }

    public static Connection getConnection () throws SQLException {
        return getInstance().connection();
    }
}

class ConnectionPool {
    public static void main(String[] args) {
        try {
            Connection connection = DataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
