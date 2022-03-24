package secondPart;

import db.enums.DataType;
import db.models.TestModel;
import db.utils.DataBaseConnection;
import db.utils.GetRandomListDoubleID;
import db.utils.ModelUtils;
import db.utils.TestDB;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    private List<TestModel> randomList;
    private TestDB testDB = new TestDB();
    private int projectId;
    private int authorId;
    private List<Integer> listAddedTest = new ArrayList<>();
    String pathAuthorJson = "src\\test\\resources\\author.json";
    String pathProjectJson = "src\\test\\resources\\project.json";

    @BeforeMethod
    public void beforeMethod() {
        randomList = GetRandomListDoubleID.getListDoubleIngeter(testDB.getAll());
        projectId = ModelUtils.getProjectId(ModelUtils.getData(DataType.PROJECT, pathProjectJson));
        authorId = ModelUtils.getAuthorId(ModelUtils.getData(DataType.AUTHOR, pathAuthorJson));

        for (int i = 0; i < 10; i++) {
            TestModel test = randomList.get(i);
            test.setProjectId(projectId);
            test.setAuthorId(authorId);
            testDB.insert(test);
            listAddedTest.add(ModelUtils.getTestModelId(test));
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        for (int i = 0; i < listAddedTest.size(); i++) {
            TestModel test = testDB.get(listAddedTest.get(i));
            test.setStartTime(new Timestamp(result.getStartMillis()));
            test.setEndTime(new Timestamp(result.getEndMillis()));
            test.setSessionId(ModelUtils.getSessionId(new Timestamp(System.currentTimeMillis())));
            testDB.update(test);
        }

        for (int i = 0; i < listAddedTest.size(); i++) {
            testDB.delete(testDB.get(listAddedTest.get(i)));
        }

        DataBaseConnection.closeConnection();
    }
}
