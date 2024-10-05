package smart.plantpot.cot.boundaries;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.StringReader;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import smart.plantpot.cot.entities.Camera;

public class CameraJSONDecoder implements Decoder.Text<Camera>{

    @Override
    public Camera decode(String jsonMessage) throws DecodeException {

        JsonObject jsonObject = Json
                .createReader(new StringReader(jsonMessage)).readObject();
        String image=jsonObject.getString("image");
        Camera camera = new Camera(image);
        return camera;

    }

    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}