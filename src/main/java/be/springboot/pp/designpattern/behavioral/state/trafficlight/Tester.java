package be.springboot.pp.designpattern.behavioral.state.trafficlight;

public class Tester {

    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        // Simulate Traffic Light Changes
        trafficLight.change(); // Red → Green
        trafficLight.change(); // Green → Yellow
        trafficLight.change(); // Yellow → Red
        trafficLight.change(); // Red → Green
    }
}
