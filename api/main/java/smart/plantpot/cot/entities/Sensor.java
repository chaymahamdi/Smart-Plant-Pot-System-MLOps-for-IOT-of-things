package smart.plantpot.cot.entities;

public class Sensor {

    private Double temperature;
    private Double  humidity;

    public Sensor() {
    }

    public Sensor(Double temperature,Double humidity) {
        this.temperature=temperature;
        this.humidity=humidity;
    }
    public Double getTemperature() {
        return temperature;
    }
    public Double getHumidity() {
        return humidity;
    }
}