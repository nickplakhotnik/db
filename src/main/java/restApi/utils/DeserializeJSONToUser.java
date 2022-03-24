package restApi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import restApi.pojos.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DeserializeJSONToUser {

    private static String getPuthOfUserJSON(int numberOfUser) {
        return "src\\test\\resources\\user" + numberOfUser + ".json";
    }

    public static User getUser(int numberOfUser) {

        ObjectMapper mapper = new ObjectMapper();
        BufferedReader br = null;
        User user = null;
        try {
            br = new BufferedReader(new FileReader(getPuthOfUserJSON(numberOfUser)));
            user = mapper.readValue(br, User.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}
