package apiTests.utils;

import restApi.enums.PostEndPoint;
import restApi.enums.UserEndPoint;
import io.restassured.specification.ResponseSpecification;
import restApi.pojos.Post;
import restApi.pojos.User;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static List<Post> getAllPosts(ResponseSpecification responseSpecification) {
        return given()
                .when()
                .get(PostEndPoint.GET_POSTS.getEndPoint())
                .then().spec(responseSpecification).log().all()
                .extract().body().jsonPath().getList("", Post.class);
    }

    public static Post getPost(int numberOfPost, ResponseSpecification responseSpecification) {
        return given()
                .when()
                .get(String.format(PostEndPoint.GET_POST.getEndPoint(), numberOfPost))
                .then().spec(responseSpecification).log().all()
                .extract().as(Post.class);
    }

    public static Post postNewPost(Post post, ResponseSpecification responseSpecification) {
        return given()
                .body(post)
                .when()
                .post(PostEndPoint.POST_NEW_POST.getEndPoint())
                .then().spec(responseSpecification).log().all()
                .extract().as(Post.class);
    }

    public static List<User> getAllUsers(ResponseSpecification responseSpecification) {
        return given()
                .when()
                .get(UserEndPoint.GET_USERS.getEndPoint())
                .then().spec(responseSpecification).log().all()
                .extract().body().jsonPath().getList("", User.class);
    }

    public static User getUser(int numberOfUser, ResponseSpecification responseSpecification) {
        return given()
                .when()
                .get(String.format(UserEndPoint.GET_USER.getEndPoint(), numberOfUser))
                .then().spec(responseSpecification).log().all()
                .extract().as(User.class);
    }
}
