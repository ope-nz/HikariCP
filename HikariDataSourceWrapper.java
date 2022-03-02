package nz.ope.HikariCP;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4j.objects.SQL;
import anywheresoftware.b4a.keywords.Common;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.concurrent.Callable;

@Version(5.01F)
@Author("ope")
@ShortName("HikariCP")

@DependsOn(values = { "HikariCP-5.0.1", "slf4j-api-1.7.30" })

public class HikariDataSourceWrapper {
  private HikariDataSource hikariDataSource;

  private boolean IsInitialised = false;

  public boolean IsInitialised() {
    return IsInitialised;
  }

  public void Initialize(String driverClassName, String JdbcUrl) {
    try {
      hikariDataSource = new HikariDataSource();
      hikariDataSource.setDriverClassName(driverClassName);
      hikariDataSource.setJdbcUrl(JdbcUrl);
      IsInitialised = true;
    } catch (Exception e) {
      Common.Log(e.toString());
      IsInitialised = false;
    }
  }

  public void Initialize2(String driverClassName, String JdbcUrl, String user, String password) {
    try {
      HikariDataSource hikariDataSource = new HikariDataSource();
      hikariDataSource.setDriverClassName(driverClassName);
      hikariDataSource.setJdbcUrl(JdbcUrl);
      hikariDataSource.addDataSourceProperty("user", user);
      hikariDataSource.addDataSourceProperty("password", password);
      IsInitialised = true;
    } catch (Exception e) {
      Common.Log(e.toString());
      IsInitialised = false;
    }
  }

  public void Initialize3(String driverClassName, String JdbcUrl, String user, String password, String ConnectionTestQuery) {
    try {
      HikariDataSource hikariDataSource = new HikariDataSource();
      hikariDataSource.setDriverClassName(driverClassName);
      hikariDataSource.setJdbcUrl(JdbcUrl);
      hikariDataSource.addDataSourceProperty("user", user);
      hikariDataSource.addDataSourceProperty("password", password);
      hikariDataSource.setConnectionTestQuery(ConnectionTestQuery);
      IsInitialised = true;
    } catch (Exception e) {
      Common.Log(e.toString());
      IsInitialised = false;
    }

  }

  public SQL GetConnection() throws SQLException {
    SQL sQL = new SQL();
    sQL.connection = hikariDataSource.getConnection();
    return sQL;
  }

  public int getMaximumPoolSize() {
    return hikariDataSource.getMaximumPoolSize();
  }

  public void setMaximumPoolSize(int Poolsize) {
    hikariDataSource.setMaximumPoolSize(Poolsize);
  }

  public int getMinimumIdle() {
    return hikariDataSource.getMinimumIdle();
  }

  public void setMinimumIdle(int minIdle) {
    hikariDataSource.setMinimumIdle(minIdle);
  }

  public long getMaxLifetime() {
    return hikariDataSource.getMaxLifetime();
  }

  public void setMaxLifetime(long maxLifetimeMs) {
    hikariDataSource.setMaxLifetime(maxLifetimeMs);
  }

  public boolean getReadOnly() {
    return hikariDataSource.isReadOnly();
  }

  public void setReadOnly(boolean readOnly) {
    hikariDataSource.setReadOnly(readOnly);
  }

  public void setAutoCommit(boolean isAutoCommit) {
    hikariDataSource.setAutoCommit(isAutoCommit);
  }

  public void ClosePool() {
    if (IsInitialised)
      hikariDataSource.close();
  }
}
