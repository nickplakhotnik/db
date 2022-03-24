package apiTests;

import org.apache.commons.lang3.RandomStringUtils;
import restApi.pojos.Post;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.Assert;
import org.testng.annotations.Test;
import restApi.pojos.User;
import apiTests.utils.ApiUtils;
import restApi.utils.DeserializeJSONToUser;
import apiTests.utils.ResponseSpecs;
import java.util.List;

public class RestAPITest extends BaseTest {

    private JsonSettingsFile testData = new JsonSettingsFile("testData.json");

    @Test
    public void restApiTest() {
        logger.info("Step 1");
        List<Post> allPosts = ApiUtils.getAllPosts(ResponseSpecs.OK);
        for (int i = 1; i < (allPosts.size() - 1); i++) {
            Assert.assertTrue(allPosts.get(i).getId() > allPosts.get(i - 1).getId(),
                    "List of posts is not sorted");
        }

        logger.info("Step 2");
        Post post99 = ApiUtils.getPost(99, ResponseSpecs.OK);
        Assert.assertEquals(post99.getUserId(), testData.getValue("/post99userId"), "UserId is not correct");
        Assert.assertEquals(post99.getId(), testData.getValue("/post99Id"), "Id is not correct");
        Assert.assertNotNull(post99.getBody(), "Body is null");
        Assert.assertNotNull(post99.getTitle(), "Title is null");

        logger.info("Step 3");
        Assert.assertNull(ApiUtils.getPost(150, ResponseSpecs.NOT_FOUND).getId(), "StatusCode not 404");

        logger.info("Step 4");
        String bodyForNewPost = RandomStringUtils.randomAlphabetic(50);
        String titleForNewPost = RandomStringUtils.randomAlphabetic(20);
        int userIdForNewPost = 1;
        Post post = new Post();
        post.setUserId(userIdForNewPost);
        post.setBody(bodyForNewPost);
        post.setTitle(titleForNewPost);
        Post postAnswer = ApiUtils.postNewPost(post, ResponseSpecs.CREATED);
        Assert.assertEquals(postAnswer.getUserId(), userIdForNewPost, "UserId is not " + userIdForNewPost);
        Assert.assertEquals(postAnswer.getBody(), bodyForNewPost, "Body is not " + bodyForNewPost);
        Assert.assertEquals(postAnswer.getTitle(), titleForNewPost, "Title is not " + titleForNewPost);
        Assert.assertEquals(postAnswer.getId(), testData.getValue("/idForNewPost"), "Id is not "
                + testData.getValue("/idForNewPost"));

        logger.info("Step 5");
        List<User> listAllUsers = ApiUtils.getAllUsers(ResponseSpecs.OK);
        User user5 = DeserializeJSONToUser.getUser(5);
        Assert.assertTrue(listAllUsers.contains(user5), "Response list is not contains user5");

        logger.info("Step 6");
        Assert.assertEquals(ApiUtils.getUser(5, ResponseSpecs.OK), user5, "Response user is not user5");
    }
}
