package com.example.Project.SOA.Emission.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class Personne {
    @Id
    @GeneratedValue
    Long id ;
    String nom ;
    String prenom ;
    String tel ;

}
