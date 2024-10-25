package com.example.Project.SOA.Emission.Repository;

import com.example.Project.SOA.Emission.Model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepo extends  JpaRepository<Personne, Long > {
    Personne findPersonneByIdAndNom(String nom , Long id);
    //Personne findByNom
    @Query("SELECT COUNT(p) > 0 FROM Personne p WHERE p.nom = :nom AND p.prenom = :prenom AND p.tel = :tel")
    boolean existsByNomAndPrenomAndTel(@Param("nom") String nom, @Param("prenom") String prenom, @Param("tel") String tel);
}
