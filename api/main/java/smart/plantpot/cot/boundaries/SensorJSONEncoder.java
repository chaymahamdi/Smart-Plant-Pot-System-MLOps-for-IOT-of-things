package smart.plantpot.cot.boundaries;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import smart.plantpot.cot.entities.Sensor;

public class SensorJSONEncoder implements Encoder.Text<Sensor> {
    @Override
    public String encode(Sensor sensor) throws EncodeException {

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("temperature", sensor.getTemperature())
                .add("humidity", sensor.getHumidity())
                .build();
        return jsonObject.toString();

    }


}