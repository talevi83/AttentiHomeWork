package Data;

public class WeatherData {
    int humidity;
    double wind;
    double pressure;
    int temperature;


    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "humidity=" + humidity +
                ", wind=" + wind +
                ", pressure=" + pressure +
                ", temprature=" + temperature +
                '}';
    }
}
