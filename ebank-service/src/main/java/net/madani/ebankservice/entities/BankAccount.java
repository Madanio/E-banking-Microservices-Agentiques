package net.madani.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.madani.ebankservice.model.Customer;

import java.util.Date;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    // Bases separees : le compte ne garde que l'id du client
    private Long customerId;
    // Rempli par OpenFeign, non stocke en base
    @Transient
    private Customer customer;
}
