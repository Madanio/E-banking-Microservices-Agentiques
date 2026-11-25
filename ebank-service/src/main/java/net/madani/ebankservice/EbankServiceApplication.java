package net.madani.ebankservice;

import net.madani.ebankservice.entities.BankAccount;
import net.madani.ebankservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

// Sans @EnableFeignClients, l'interface CustomerRestClient n'est pas implementee
@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    // 5 comptes pour chacun des 3 clients. Insertion directe par le repository :
    // ebank-service peut demarrer meme si customer-service n'est pas encore lance.
    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository) {
        return args -> {
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < 5; j++) {
                    accountRepository.save(BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .createdAt(new Date())
                            .balance(1000 + Math.random() * 90000)
                            .type(Math.random() < 0.5 ? "CURRENT_ACCOUNT" : "SAVING_ACCOUNT")
                            .customerId((long) i)
                            .build());
                }
            }
        };
    }
}
