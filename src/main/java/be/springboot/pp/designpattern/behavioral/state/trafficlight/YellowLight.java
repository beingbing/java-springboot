package be.springboot.pp.designpattern.behavioral.state.trafficlight;

public class YellowLight implements TrafficLightState {
    @Override
    public void changeLight(TrafficLight trafficLight) {
        System.out.println("🟡 Yellow Light - Slow Down!");
        trafficLight.setState(new RedLight());
    }
}
