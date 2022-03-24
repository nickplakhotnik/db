package db.utils;

import db.dao.IDao;
import db.models.AuthorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AuthorDB implements IDao<AuthorModel> {

    private List<AuthorModel> authors = new ArrayList<>();

    private static final String SQL_GET = "SELECT * FROM Author WHERE id = ?";
    private static final String SQL_GET_ATTRIBUTES = "SELECT id, name, login, email FROM Author";
    private static final String SQL_INSERT = "INSERT INTO Author (name, login, email) values (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Author SET " +
            "name = ?, " +
            "login = ?, " +
            "email = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Author WHERE id = ?";

    @Override
    public AuthorModel get(int id) {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        AuthorModel authorModel = null;
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET)){
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                authorModel = new AuthorModel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException();
        }
        return authorModel;
    }

    @Override
    public List<AuthorModel> getAll() {
        Connection conn = DataBaseConnection.getConnection();
        ResultSet rs;
        authors = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SQL_GET_ATTRIBUTES)){
            rs = statement.executeQuery();
            while (rs.next()) {
                authors.add(new AuthorModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public void insert(AuthorModel author) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_INSERT)){
            statement.setObject(2, author.getLogin(), Types.VARCHAR);
            statement.setObject(3, author.getEmail(), Types.VARCHAR);
            statement.setObject(1, author.getName(), Types.VARCHAR);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AuthorModel author) {
        Connection conn = DataBaseConnection.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)){
            statement.setObject(1, author.getName(), Types.VARCHAR);
            statement.setObject(2, author.getLogin(), Types.VARCHAR);
            statement.setObject(3, author.getEmail(), Types.VARCHAR);
            statement.setObject(4, author.getId(), Types.INTEGER);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AuthorModel author) {
        Connection conn = DataBaseConnection.getConnection();

        try (PreparedStatement statement = conn.prepareStatement(SQL_DELETE)){
            statement.setInt(1, author.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
