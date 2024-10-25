package com.example.Project.SOA.Emission.Repository;

import com.example.Project.SOA.Emission.Model.Emission;
import com.example.Project.SOA.Emission.Model.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmissionRepo extends JpaRepository<Emission , Long > {
    Optional<List<Emission>> findByTitre( String titre);
    @Query("SELECT e FROM Emission e JOIN e.listepartie p WHERE p.id = :partieId AND e.id = :emissionId")
    Optional<Emission> findEmissionByPartieId(@Param("emissionId") Long emissionId, @Param("partieId") Long partieId);

    //@Query("SELECT e.titre FROM Emission e JOIN e.listepartie p GROUP BY e.id ORDER BY COUNT(p) DESC")
    //@Query("SELECT e.titre FROM Emission e ORDER BY SIZE(e.listepartie) DESC")
    @Query("SELECT e.titre FROM Emission e WHERE SIZE(e.listepartie) = (SELECT MAX(SIZE(ep.listepartie)) FROM Emission ep)")

    Optional<String> findTitreWithMostParties();

    @Query("SELECT SIZE(e.listepartie) FROM Emission e WHERE e.id = :emissionId")
    int countPartiesInEmission(@Param("emissionId") Long emissionId);



}
