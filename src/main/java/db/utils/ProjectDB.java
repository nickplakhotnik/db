package db.utils;

import db.dao.IDao;
import db.models.ProjectModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProjectDB implements IDao<ProjectModel> {

    private List<ProjectModel> projects = new ArrayList<>();

    private static final String SQL_GET = "SELECT * FROM Project WHERE id = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT id, name FROM Project";
    private static final String SQL_INSERT = "INSERT INTO Project (name) values (?)";
    private static final String SQL_UPDATE = "UPDATE Project SET " +
            "name = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Project WHERE id = ?";

    @Override
    public ProjectModel get(int id) {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        ProjectModel projectModel = null;
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET)){
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                projectModel = new ProjectModel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return projectModel;
    }

    @Override
    public List<ProjectModel> getAll() {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        projects = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET_ATTRIBUTES)){
            rs = statement.executeQuery();
            while (rs.next()) {
                projects.add(new ProjectModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public void insert(ProjectModel project) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_INSERT)){
            statement.setObject(1, project.getName(), Types.VARCHAR);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ProjectModel project) {
        Connection conn = DataBaseConnection.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)){
            statement.setObject(1, project.getName(), Types.VARCHAR);
            statement.setObject(2, project.getId(), Types.INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ProjectModel project) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_DELETE)){
            statement.setInt(1, project.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
