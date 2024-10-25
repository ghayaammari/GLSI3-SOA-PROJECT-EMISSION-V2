package com.example.Project.SOA.Emission.Repository;

import com.example.Project.SOA.Emission.Model.Emission;
import com.example.Project.SOA.Emission.Model.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartieRepo extends JpaRepository<Partie, Long> {
    Optional<Partie> findPartieByNumpartieAndEmission_Id(int numpartie, Long emissionId);
    @Query("SELECT p FROM Partie p WHERE p.emission.id = :emissionId AND p.numpartie = :numpartie")
    Optional<Partie> findPartieByEmissionIdAndNumpartie(@Param("emissionId") Long emissionId, @Param("numpartie") int numpartie);
    //@Query("SELET p FROM Partie p WHERE p.emission.id= :emissionID ")
    @Query("SELECT p FROM Partie p JOIN p.listeInvitee i WHERE i.id = :personneExterneId")
    List<Partie> findPartiesByPersonneExterneId(@Param("personneExterneId") Long personneExterneId);



}
