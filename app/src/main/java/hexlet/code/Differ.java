package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.LinkedHashMap;

public class Differ {
    public static Map<String, LinkedList<Object>> generate(String filePath1, String filePath2) {
        Map<String, Object> dict1 = null;
        Map<String, Object> dict2 = null;
        LinkedHashMap<String, LinkedList<Object>> model = new LinkedHashMap<>();

        try {
            dict1 = Parser.getData(filePath1);
            dict2 = Parser.getData(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dict1 == null || dict2 == null) {
            return model;
        }
        Set<String> keys = new TreeSet<>(dict1.keySet());
        keys.addAll(dict2.keySet());

        for (String key : keys) {
            var value1 = dict1.get(key) == null ? "null" : dict1.get(key);
            var value2 = dict2.get(key) == null ? "null" : dict2.get(key);
            var list = new LinkedList<>();

            if (dict1.containsKey(key) && dict2.containsKey(key)) {
                if (value1.equals(value2)) {
                    list.add(value1);
                    list.add("nochanges");
                    model.put(key, list);
                } else {
                    list.add(value1);
                    list.add("modified");
                    list.add(value2);
                    model.put(key, list);
                }
            } else if (dict1.containsKey(key)) {
                list.add(value1);
                list.add("deleted");
                model.put(key, list);
            } else {
                list.add(value2);
                list.add("added");
                model.put(key, list);
            }
        }
        return model;
    }
}
