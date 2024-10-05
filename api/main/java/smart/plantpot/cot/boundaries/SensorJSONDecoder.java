package smart.plantpot.cot.boundaries;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.StringReader;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import smart.plantpot.cot.entities.Sensor;

public class SensorJSONDecoder implements Decoder.Text<Sensor>{

    @Override
    public Sensor decode(String jsonMessage) throws DecodeException {

        JsonObject jsonObject = Json
                .createReader(new StringReader(jsonMessage)).readObject();
        Double temperature=jsonObject.isNull("temperature") ? null : jsonObject.getJsonNumber("temperature").doubleValue();
        Double humidity=jsonObject.isNull("humidity") ? null : jsonObject.getJsonNumber("humidity").doubleValue();
        Sensor sensor = new Sensor(temperature,humidity);
        return sensor;

    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}