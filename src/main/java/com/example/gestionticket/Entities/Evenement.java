// Evenement.java
package com.example.gestionticket.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "Event")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(length = 45, nullable = false, name = "nom")
    private String titre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEvenement;

    @Column
    private String typeEvenement;

    @Setter
    @Column
    private String description;

    @Column
    private Double price; // Changed from 'double' to 'Double'

    // ... rest of the code ...

}