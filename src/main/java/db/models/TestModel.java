package db.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

public class TestModel extends Model {

    private Integer id;
    private String name;
    private Integer statusId;
    private String methodName;
    private Integer projectId;
    private Integer sessionId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String env;
    private String browser;
    private Integer authorId;

    public TestModel() {}

    public TestModel(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            statusId = resultSet.getInt("status_id");
            methodName = resultSet.getString("method_name");
            projectId = resultSet.getInt("project_id");
            sessionId = resultSet.getInt("session_id");
            startTime = resultSet.getTimestamp("start_time");
            endTime = resultSet.getTimestamp("end_time");
            env = resultSet.getString("env");
            browser = resultSet.getString("browser");
            authorId = resultSet.getInt("author_id");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestModel test = (TestModel) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(name, test.name) &&
                Objects.equals(statusId, test.statusId) &&
                Objects.equals(methodName, test.methodName) &&
                Objects.equals(projectId, test.projectId) &&
                Objects.equals(sessionId, test.sessionId) &&
                Objects.equals(startTime, test.startTime) &&
                Objects.equals(endTime, test.endTime) &&
                Objects.equals(env, test.env) &&
                Objects.equals(browser, test.browser) &&
                Objects.equals(authorId, test.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
    }
}
