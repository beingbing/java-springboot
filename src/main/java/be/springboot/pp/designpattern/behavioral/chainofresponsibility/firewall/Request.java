package be.springboot.pp.designpattern.behavioral.chainofresponsibility.firewall;

public class Request {
    private final String ip;
    private final String data;
    private final boolean authenticated;

    public Request(String ip, String data, boolean authenticated) {
        this.ip = ip;
        this.data = data;
        this.authenticated = authenticated;
    }

    public String getIp() { return ip; }
    public String getData() { return data; }
    public boolean isAuthenticated() { return authenticated; }
}
