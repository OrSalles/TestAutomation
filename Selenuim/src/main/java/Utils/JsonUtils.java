package Utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class JsonUtils {

    /**
     * Return json object
     *
     * @param partialPath to the file json
     * @param key         key in the file
     * @return json object
     */

    public static JSONObject returnJsonObject(String partialPath, String key) {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader;
        try {
            String path = "src/test/resources/";
            fileReader = new FileReader(path + partialPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Object obj;
        try {
            obj = jsonParser.parse(fileReader);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = (JSONObject) obj;
        return (JSONObject) jsonObject.get(key);
    }

}
