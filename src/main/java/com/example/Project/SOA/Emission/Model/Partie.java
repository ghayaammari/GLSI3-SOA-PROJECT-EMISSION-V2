package com.example.Project.SOA.Emission.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partie {
    @Id
    @GeneratedValue
    Long id ;

    String duree;
    @NotNull
    //@Column(unique = true)
    int numpartie ;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emission_id", nullable = false )

    Emission emission;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "partie_personne_externe",
            joinColumns = @JoinColumn(name = "partie_id"),
            inverseJoinColumns = @JoinColumn(name = "personne_externe_id", unique = true)
    )
    private Set<PersonneExterne> listeInvitee = new HashSet<>();



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Partie partie = (Partie) obj;
        return id.equals(partie.id) &&
                duree.equals(partie.duree) &&
                numpartie == partie.numpartie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duree, numpartie);
    }



}
