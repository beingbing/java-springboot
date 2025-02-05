package be.springboot.pp.fooddeliverysystem.pojos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BusinessHours {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public BusinessHours(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
