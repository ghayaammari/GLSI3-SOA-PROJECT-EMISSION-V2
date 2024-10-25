package com.example.Project.SOA.Emission.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emission {
    @Id
    @GeneratedValue
    Long id ;
    Date dateEmission ;
    String saison ;
    String titre ;
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //Set<PersonneInterne> equipeTravail ;
    @OneToMany(cascade = CascadeType.ALL) //, orphanRemoval = true
    @JoinTable(
            name = "emission_personne_interne",
            joinColumns = @JoinColumn(name = "emission_id"),
            inverseJoinColumns = @JoinColumn(name = "personne_interne_id", unique = true)
    )
    private Set<PersonneInterne> equipeTravail = new HashSet<>();

    public void ajouterPersonneInterne(PersonneInterne personne) {
        equipeTravail.add(personne);
    }
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Partie> listepartie;
}
