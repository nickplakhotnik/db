package db.utils;

import db.dao.IDao;
import db.models.TestModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TestDB implements IDao<TestModel> {

    private List<TestModel> tests = new ArrayList<>();

    private static final String SQL_GET = "SELECT * FROM Test WHERE id = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT id, name, status_id, method_name, project_id, session_id," +
            "start_time, end_time, env, browser, author_id FROM Test";
    private static final String SQL_INSERT = "INSERT INTO Test (name, status_id, method_name, project_id, session_id, " +
            "start_time, end_time, env, browser, author_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Test SET " +
            "name = ?, " +
            "status_id = ?, " +
            "method_name = ?, " +
            "project_id = ?, " +
            "session_id = ?, " +
            "start_time = ?, " +
            "end_time = ?, " +
            "env = ?, " +
            "browser = ?, " +
            "author_id = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Test WHERE id = ?";

    @Override
    public TestModel get(int id) {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        TestModel testModel = null;
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET)){
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                testModel = new TestModel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return testModel;
    }

    @Override
    public List<TestModel> getAll() {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        tests = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET_ATTRIBUTES)){
            rs = statement.executeQuery();
            while (rs.next()) {
                tests.add(new TestModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    @Override
    public void insert(TestModel test) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_INSERT)){
            statement.setObject(1, test.getName(), Types.VARCHAR);
            statement.setObject(2, test.getStatusId(), Types.INTEGER);
            statement.setObject(3, test.getMethodName(), Types.VARCHAR);
            statement.setObject(4, test.getProjectId(), Types.INTEGER);
            statement.setObject(5, test.getSessionId(), Types.INTEGER);
            statement.setObject(6, test.getStartTime(), Types.TIMESTAMP);
            statement.setObject(7, test.getEndTime(), Types.TIMESTAMP);
            statement.setObject(8, test.getEnv(), Types.VARCHAR);
            statement.setObject(9, test.getBrowser(), Types.VARCHAR);
            statement.setObject(10, test.getAuthorId(), Types.INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TestModel test) {
        Connection conn = DataBaseConnection.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)){
            statement.setObject(1, test.getName(), Types.VARCHAR);
            statement.setObject(2, test.getStatusId(), Types.INTEGER);
            statement.setObject(3, test.getMethodName(), Types.VARCHAR);
            statement.setObject(4, test.getProjectId(), Types.INTEGER);
            statement.setObject(5, test.getSessionId(), Types.INTEGER);
            statement.setObject(6, test.getStartTime(), Types.TIMESTAMP);
            statement.setObject(7, test.getEndTime(), Types.TIMESTAMP);
            statement.setObject(8, test.getEnv(), Types.VARCHAR);
            statement.setObject(9, test.getBrowser(), Types.VARCHAR);
            statement.setObject(10, test.getAuthorId(), Types.INTEGER);
            statement.setObject(11, test.getId(), Types.INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TestModel test) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_DELETE)){
            statement.setInt(1, test.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
