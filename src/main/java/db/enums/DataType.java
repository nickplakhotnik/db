package db.enums;

import db.models.AuthorModel;
import db.models.Model;
import db.models.ProjectModel;
import db.models.SessionModel;
import db.models.StatusModel;
import db.models.TestModel;

public enum DataType {

    AUTHOR(AuthorModel.class),
    PROJECT(ProjectModel.class),
    SESSION(SessionModel.class),
    STATUS(StatusModel.class),
    TEST(TestModel.class);

    private Class<? extends Model> clazz;

    private <T extends Model> DataType(Class<T> clazz) {
        this.clazz = clazz;
    }

    public <T extends Model> Class<T> getClazz() {
        return (Class<T>) this.clazz;
    }
}
