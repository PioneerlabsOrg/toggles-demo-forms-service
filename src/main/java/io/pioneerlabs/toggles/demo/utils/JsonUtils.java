package io.pioneerlabs.toggles.demo.utils;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@Slf4j
public class JsonUtils {

    public static JSONObject createJsonObject(InputStream inputStream) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(
                    new InputStreamReader(inputStream,
                            "UTF-8"));
        } catch (ParseException | UnsupportedEncodingException e) {
            log.error("An error occurred {}", e);
        }
        return jsonObject;
    }

}
