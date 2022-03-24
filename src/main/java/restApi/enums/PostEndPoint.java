package restApi.enums;

public enum PostEndPoint {

    GET_POSTS("/posts"),
    POST_NEW_POST("/posts"),
    GET_POST("/posts/%d");

    private String endPoint;

    PostEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return this.endPoint;
    }
}
