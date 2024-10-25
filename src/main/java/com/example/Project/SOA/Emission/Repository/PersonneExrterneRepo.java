package com.example.Project.SOA.Emission.Repository;

import com.example.Project.SOA.Emission.Model.PersonneExterne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneExrterneRepo extends JpaRepository<PersonneExterne, Long > {
    List<PersonneExterne> findPersonneExterneByDomaine(String domaine);
    List<PersonneExterne> findByTravailleActuelle(String travaille);

    //@Query("Select PersonneExterne  from PersonneExterne  px , Emission  e where  e.titre: titre AND e")
    @Query("SELECT pe FROM Emission e JOIN e.listepartie p JOIN p.listeInvitee pe WHERE e.titre = :titreEmission")
    List<PersonneExterne> findPersonnesExternesByEmissionTitre(@Param("titreEmission") String titreEmission);
    @Query("SELECT pe FROM Emission e JOIN e.listepartie p JOIN p.listeInvitee pe WHERE e.titre = :titreEmission AND p.numpartie = :numeroPartie")
    List<PersonneExterne> findPersonnesExternesByEmissionTitreAndPartieNumero(
            @Param("titreEmission") String titreEmission,
            @Param("numeroPartie") int numeroPartie
    );
}
