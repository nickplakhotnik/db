package restApi.pojos;

import lombok.Data;

@Data
public class Post {
    private int userId;
    private Integer id;
    private String title;
    private String body;
}



