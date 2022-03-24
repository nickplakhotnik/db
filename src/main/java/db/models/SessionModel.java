package db.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class SessionModel extends Model {

    private Integer id;
    private String sessionKey;
    private Timestamp createdTime;
    private Integer buildNumber;

    public SessionModel() {}

    public SessionModel(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            sessionKey = resultSet.getString("session_key");
            createdTime = resultSet.getTimestamp("created_time");
            buildNumber = resultSet.getInt("build_number");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionModel that = (SessionModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sessionKey, that.sessionKey) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(buildNumber, that.buildNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionKey, createdTime, buildNumber);
    }
}
