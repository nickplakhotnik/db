package db.utils;

import db.dao.IDao;
import db.models.SessionModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SessionDB implements IDao<SessionModel> {

    private List<SessionModel> sessions = new ArrayList<>();

    private static final String SQL_GET = "SELECT * FROM Session WHERE id = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT id, session_key, created_time, build_number FROM Session";
    private static final String SQL_INSERT = "INSERT INTO Session (session_key, created_time, build_number) values (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Session SET " +
            "session_key = ?, " +
            "created_time = ?, " +
            "build_number = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Session WHERE id = ?";

    @Override
    public SessionModel get(int id) {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        SessionModel sessionModel = null;
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET)){
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                sessionModel = new SessionModel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return sessionModel;
    }

    @Override
    public List<SessionModel> getAll() {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        sessions = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET_ATTRIBUTES)){
            rs = statement.executeQuery();
            while (rs.next()) {
                sessions.add(new SessionModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public void insert(SessionModel session) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_INSERT)){
            statement.setObject(1, session.getSessionKey(), Types.VARCHAR);
            statement.setObject(2, session.getCreatedTime(), Types.TIMESTAMP);
            statement.setObject(3, session.getBuildNumber(), Types.INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(SessionModel session) {
        Connection conn = DataBaseConnection.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)){
            statement.setObject(1, session.getSessionKey(), Types.VARCHAR);
            statement.setObject(2, session.getCreatedTime(), Types.TIMESTAMP);
            statement.setObject(3, session.getBuildNumber(), Types.INTEGER);
            statement.setObject(4, session.getId(), Types.INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(SessionModel session) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_DELETE)){
            statement.setInt(1, session.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
