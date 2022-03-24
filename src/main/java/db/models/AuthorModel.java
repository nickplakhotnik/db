package db.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AuthorModel extends Model {

    private Integer id;
    private String name;
    private String login;
    private String email;

    public AuthorModel() {}

    public AuthorModel(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            login = resultSet.getString("login");
            email = resultSet.getString("email");
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorModel that = (AuthorModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(login, that.login) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email);
    }
}
