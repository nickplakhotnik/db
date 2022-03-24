package db.utils;

import db.models.TestModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetRandomListDoubleID {

    public static List<TestModel> getListDoubleIngeter(List<TestModel> list) {
        Set<TestModel> resultSet = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> listNumber = Arrays.asList(String.valueOf(list.get(i).getId()).split(""));
            Collections.sort(listNumber);
            for(int j = 0; j < listNumber.size() - 1; j++) {
                if(listNumber.get(j).equals(listNumber.get(j + 1))) {
                    resultSet.add(list.get(i));
                }
            }
        }
        List<TestModel> resultList = new ArrayList<>(resultSet);
        Collections.shuffle(resultList);
        return resultList;
    }
}
