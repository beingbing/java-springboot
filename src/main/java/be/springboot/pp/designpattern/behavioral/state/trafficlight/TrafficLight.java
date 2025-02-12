package be.springboot.pp.designpattern.behavioral.state.trafficlight;

public class TrafficLight {
    private TrafficLightState state;

    public TrafficLight() {
        // Default state is Red
        state = new RedLight();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void change() {
        state.changeLight(this);
    }
}
