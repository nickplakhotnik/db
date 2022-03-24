package db.utils;

import com.google.gson.Gson;
import db.enums.DataType;
import db.models.AuthorModel;
import db.models.Model;
import db.models.ProjectModel;
import db.models.SessionModel;
import db.models.TestModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.Objects;

public class ModelUtils {

    public static int getTestModelId(TestModel testModelLookingId) {
        TestDB testDB = new TestDB();
        int id = -1;
        for (TestModel testModel : testDB.getAll()) {
            if (Objects.equals(testModel.getName(), testModelLookingId.getName()) &&
                    Objects.equals(testModel.getStatusId(), testModelLookingId.getStatusId()) &&
                    Objects.equals(testModel.getMethodName(), testModelLookingId.getMethodName()) &&
                    Objects.equals(testModel.getProjectId(), testModelLookingId.getProjectId()) &&
                    Objects.equals(testModel.getSessionId(), testModelLookingId.getSessionId()) &&
                    Objects.equals(testModel.getStartTime(), testModelLookingId.getStartTime()) &&
                    Objects.equals(testModel.getEndTime(), testModelLookingId.getEndTime()) &&
                    Objects.equals(testModel.getEnv(), testModelLookingId.getEnv()) &&
                    Objects.equals(testModel.getBrowser(), testModelLookingId.getBrowser()) &&
                    Objects.equals(testModel.getAuthorId(), testModelLookingId.getAuthorId())) {
                id = testModel.getId();
            }
        }
        return id;
    }

    public static int getAuthorId(AuthorModel testAuthorModel) {
        AuthorDB authorDB = new AuthorDB();
        int id = -1;
        for(AuthorModel authorModel : authorDB.getAll()) {
            if (authorModel.getName().equals(testAuthorModel.getName()) && authorModel.getEmail().equals(testAuthorModel.getEmail())
                    && authorModel.getLogin().equals(testAuthorModel.getLogin())) {
                id = authorModel.getId();
            }
        }

        if (id == -1) {
            authorDB.insert(testAuthorModel);
            for(AuthorModel authorModel : authorDB.getAll()) {
                if (authorModel.getName().equals(testAuthorModel.getName()) && authorModel.getEmail().equals(testAuthorModel.getEmail())
                        && authorModel.getLogin().equals(testAuthorModel.getLogin())) {
                    id = authorModel.getId();
                }
            }
        }
        return id;
    }

    public static int getProjectId(ProjectModel testProjectModel) {
        ProjectDB projectDB = new ProjectDB();
        int id = -1;
        for(ProjectModel project : projectDB.getAll()) {
            if(project.getName().equals(testProjectModel.getName())) {
                id = project.getId();
            }
        }

        if(id == -1) {
            projectDB.insert(testProjectModel);
            for(ProjectModel project : projectDB.getAll()) {
                if(project.getName().equals(testProjectModel.getName())) {
                    id = project.getId();
                }
            }
        }
        return id;
    }

    public static int getSessionId(Timestamp timestamp) {
        SessionDB sessionDB = new SessionDB();
        int sessionId = -1;
        SessionModel session = new SessionModel();
        session.setSessionKey(timestamp.toString());
        session.setCreatedTime(timestamp);
        session.setBuildNumber(1);
        sessionDB.insert(session);
        for (SessionModel sessionModel : sessionDB.getAll()) {
            if(sessionModel.getSessionKey().equals(session.getSessionKey())) {
                sessionId = sessionModel.getId();
            }
        }
        return sessionId;
    }

    public static <T extends Model> T getData(DataType type, String path) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(br, type.getClazz());
    }

}
