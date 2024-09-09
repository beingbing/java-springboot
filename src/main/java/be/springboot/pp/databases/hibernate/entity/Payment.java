package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    private Double originalAmount;

    private Double paidAmount;

    private String paymentMode;

    private String paymentStatus;

    private String paymentBankReferenceNumber;

    private Double securityDeposit;

    private Double discountAmount;

    private Double discountPercentage;

    private String claimedCouponCode;

    private Double claimedCouponAmount;
}
