package br.com.vygos.springkeycloak.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
  public static <T> T parseString(String json, Class<T> tClass) {
    try {
      return new ObjectMapper().readValue(json, tClass);
    } catch (JsonProcessingException e) {
      throw new JsonParserException("Error while parse string to json: " + e.getMessage());
    }
  }
}
