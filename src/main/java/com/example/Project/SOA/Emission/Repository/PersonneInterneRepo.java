package com.example.Project.SOA.Emission.Repository;

import com.example.Project.SOA.Emission.Model.PersonneInterne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneInterneRepo extends JpaRepository<PersonneInterne, Long > {
    @Query("SELECT pi FROM PersonneInterne pi LEFT JOIN FETCH pi.donnePers")
    List<PersonneInterne> findAllWithDonnePers();
    @Query("SELECT pi FROM PersonneInterne pi JOIN pi.donnePers p WHERE p.nom = :nom")
    List<PersonneInterne> findByNomInPersonne(@Param("nom") String nom);
    @Query("SELECT pi FROM PersonneInterne pi JOIN pi.donnePers p WHERE p.prenom = :prenom")
    List<PersonneInterne> findByPrenomInPersonne(@Param("prenom") String prenom);
    @Query("SELECT pi FROM PersonneInterne pi WHERE pi.donnePers.nom = :nom AND pi.donnePers.prenom = :prenom")
    Optional<PersonneInterne> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    List<PersonneInterne> findByRoleDansTele(String role);
    List<PersonneInterne> findByDateEmbauche(Date date);

    //double salaire ;
    //Date dateEmbauche ;
    List<PersonneInterne> findBySalaire(double salaire);
    List<PersonneInterne> findBySalaireAfter(double salairmin);
    List<PersonneInterne> findBySalaireBefore(double salairemax);
    List<PersonneInterne> findBySalaireBetween(double salairemin , double salairemax);
    @Query("SELECT pi FROM PersonneInterne pi WHERE pi.salaire = (SELECT MAX(pi2.salaire) FROM PersonneInterne pi2)")
    Optional<PersonneInterne> findPersonneWithHighestSalaire();





}
