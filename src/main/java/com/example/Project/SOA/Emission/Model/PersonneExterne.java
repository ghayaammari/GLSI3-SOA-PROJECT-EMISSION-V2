package com.example.Project.SOA.Emission.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class PersonneExterne {
    @Id
    @GeneratedValue
    Long id ;
    String travailleActuelle ;
    String domaine ;
    @OneToOne
    @JoinColumn(name = "person_id")
    Personne donnePers;
    //@ManyToOne
    //@JoinColumn(name = "partie_id")
    //Partie partie ;


}
