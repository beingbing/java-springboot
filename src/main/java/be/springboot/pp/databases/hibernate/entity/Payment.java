package be.springboot.pp.databases.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "application_id")
    private Application application;
}
