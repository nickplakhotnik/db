package apiTests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.JsonSettingsFile;
import db.enums.DataType;
import db.models.TestModel;
import db.utils.DataBaseConnection;
import db.utils.ModelUtils;
import db.utils.TestDB;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import apiTests.utils.Specifications;
import restApi.ConfigModel;
import java.sql.Timestamp;

public class BaseTest {

    private static ConfigModel config = new ConfigModel(new JsonSettingsFile("config.json"));
    protected Logger logger = AqualityServices.getLogger();
    String pathAuthorJson = "src\\test\\resources\\author.json";
    String pathProjectJson = "src\\test\\resources\\project.json";

    @BeforeSuite
    public void beforeSuite() {
        Specifications.instalSpecification(Specifications.requestSpecification(config.getUrl()));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        TestDB testDB = new TestDB();
        testDB.get(1);
        TestModel testModel = new TestModel();
        testModel.setName(result.getName());
        testModel.setStatusId(result.getStatus());
        testModel.setMethodName(result.getMethod().toString());
        testModel.setProjectId(ModelUtils.getProjectId(ModelUtils.getData(DataType.PROJECT, pathProjectJson)));
        testModel.setSessionId(ModelUtils.getSessionId(new Timestamp(System.currentTimeMillis())));
        testModel.setStartTime(new Timestamp(result.getStartMillis()));
        testModel.setEndTime(new Timestamp(result.getEndMillis()));
        testModel.setEnv(config.getEnv());
        testModel.setAuthorId(ModelUtils.getAuthorId(ModelUtils.getData(DataType.AUTHOR, pathAuthorJson)));
        testDB.insert(testModel);

        DataBaseConnection.closeConnection();
    }
}
