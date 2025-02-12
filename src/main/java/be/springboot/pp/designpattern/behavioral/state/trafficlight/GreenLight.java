package be.springboot.pp.designpattern.behavioral.state.trafficlight;

public class GreenLight implements TrafficLightState {
    @Override
    public void changeLight(TrafficLight trafficLight) {
        System.out.println("🟢 Green Light - Go!");
        trafficLight.setState(new YellowLight());
    }
}
