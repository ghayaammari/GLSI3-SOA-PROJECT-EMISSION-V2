package com.example.Project.SOA.Emission.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table
@Entity
public class PersonneInterne {
    @Id
    @GeneratedValue
    Long id ;
    @Column(nullable = false)
    String roleDansTele;
    @Column(nullable = false)
    double salaire ;
    Date dateEmbauche ;
    @OneToOne
    @JoinColumn(name = "donnePers_id" , nullable = false ,unique = true)
    Personne donnePers;

}
