package be.springboot.pp.librarymanagementsystem.entities;

import java.time.LocalDateTime;

public class Admin extends User {
    LocalDateTime shiftStartTime;
    LocalDateTime shiftEndTime;
}
