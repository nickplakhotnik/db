package db.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ProjectModel extends Model {

    private Integer id;
    private String name;

    public ProjectModel() {}

    public ProjectModel(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectModel that = (ProjectModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
