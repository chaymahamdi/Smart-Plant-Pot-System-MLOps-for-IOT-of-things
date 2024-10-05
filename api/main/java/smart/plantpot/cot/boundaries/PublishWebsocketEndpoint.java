package smart.plantpot.cot.boundaries;
import jakarta.ejb.EJB;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.util.*;
import smart.plantpot.cot.entities.Sensor;
import smart.plantpot.cot.entities.Camera;
import smart.plantpot.cot.controllers.MqttMessageEventManager;

@ServerEndpoint(value = "/pushes", encoders = {SensorJSONEncoder.class,CameraJSONEncoder.class}, decoders = {SensorJSONDecoder.class,CameraJSONDecoder.class}) //Annotates path for websocket and with the json encoders and decoders
public class PublishWebsocketEndpoint {
    private static Hashtable<String, Session> sessions = new Hashtable<>(); // initialize sessions as empty Hashtable
    public static void broadcastSensorMessage(Sensor sensor) {
        for (Session session : sessions.values()) {
            try {
                session.getBasicRemote().sendObject(sensor); // broadcast the message to websocket
                System.out.println("work: "); // for debugging
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    public static void broadcastCameraMessage(Camera camera) {
        for (Session session : sessions.values()) {
            try {
                session.getBasicRemote().sendObject(camera); // broadcast the message to websocket
                System.out.println("work 2: "); // for debugging
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }
    @OnOpen
    public void onOpen(Session session){
        MqttMessageEventManager eventmanager=new MqttMessageEventManager();
        eventmanager.start();
        sessions.put(session.getId(), session); //add the new session

    }
    @OnClose
    public void onClose(Session session, CloseReason reason){
        sessions.remove(session); // delete sessions when client leave
    }
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("Push WebSocket error for ID " + session.getId() + ": " + error.getMessage());
    }





}