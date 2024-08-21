package io.materialplus.producer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/*import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;*/
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@Entity
public class Transaction implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private String cardNumber;
    private double amount;
    private LocalDateTime timestamp;
    private String merchantId;

    // Getters and Setters
}