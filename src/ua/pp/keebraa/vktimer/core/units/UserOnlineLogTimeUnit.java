package ua.pp.keebraa.vktimer.core.units;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.pp.keebraa.vktimer.core.Application;
import ua.pp.keebraa.vktimer.core.tasks.InitUnitTask;

public class UserOnlineLogTimeUnit implements ApplicationUnit {

    private String unitId = "users-online-logtime-unit";
    private String createConfigTable = "                                                        "
            + " CREATE TABLE if not exists database_files                                       "
            + " (                                                                               "
            + "     id integer primary key asc autoincrement,                                   "
            + "     created_date integer,                                                       "
            + "     closed_date date,                                                           "
            + "     filepath text                                                               "
            + " );                                                                              ";
    private String createNewDatabase = "                                                        "
            + " INSERT INTO database_files                                                      "
            + " (created_date, filepath)                                                        "
            + "    values                                                                       "
            + " (?, ?)                                                                          ";
    private String currentDatabasePath = "                                                      "
            + " SELECT filepath                                                                 "
            + " FROM database_files                                                             "
            + " where closed_date is null                                                       ";
    private Connection configDatabaseConnection;

    private String prefix = "/tmp/";
    private String configDatabase = "vk_users_online_unit.db";
    private Application application;

    @Override
    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public void cleanStart() {
        try {
            Class.forName("org.sqlite.JDBC");
            application.registerTask(new InitUnitTask(this));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String createNewDatabase() {
        try {
            Statement statement = configDatabaseConnection.createStatement();
            statement.executeUpdate(createConfigTable);
            PreparedStatement prepStatement = configDatabaseConnection.prepareStatement(createNewDatabase);
            prepStatement.setLong(1, System.currentTimeMillis());
            prepStatement.setString(2, "/tmp/blablabla.db");
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/tmp/blablabla.db";
    }

    private String getCurrentDatabasePath() {
        try {
            configDatabaseConnection = DriverManager.getConnection("jdbc:sqlite:" + prefix + configDatabase);
            Statement statement = configDatabaseConnection.createStatement();
            statement.executeUpdate(createConfigTable);
            createNewDatabase();
            ResultSet result = statement.executeQuery(currentDatabasePath);
            result.next();
            System.out.println(result.getString(1));
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void init() {
        getCurrentDatabasePath();
    }

    @Override
    public void prepareStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {

    }

    @Override
    public String getUnitId() {
        return unitId;
    }

}
