package restApi.enums;

public enum UserEndPoint {

    GET_USERS("/users"),
    GET_USER("/users/%d");

    private String endPoint;

    UserEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

}
