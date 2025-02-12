package be.springboot.pp.designpattern.behavioral.state.trafficlight;

public class RedLight implements TrafficLightState {
    @Override
    public void changeLight(TrafficLight trafficLight) {
        System.out.println("🔴 Red Light - Stop!");
        trafficLight.setState(new GreenLight());
    }
}
