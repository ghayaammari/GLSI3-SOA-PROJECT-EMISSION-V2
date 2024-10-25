package com.example.Project.SOA.Emission.Service;

import com.example.Project.SOA.Emission.Model.Emission;
import com.example.Project.SOA.Emission.Model.Partie;
import com.example.Project.SOA.Emission.Model.PersonneInterne;
import com.example.Project.SOA.Emission.Repository.EmissionRepo;
import com.example.Project.SOA.Emission.Repository.PartieRepo;
import com.example.Project.SOA.Emission.Repository.PersonneInterneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmissionService {
    @Autowired
    EmissionRepo emissionRepo;
    @Autowired
    PartieRepo partieRepo;
    @Autowired
    PersonneInterneRepo personneInterneRepo;
    public Emission CreateEm(Emission emission){
        return emissionRepo.save(emission);
    }
    public List<Emission> getAllEmission(){
        return emissionRepo.findAll();
    }
    public Emission getEmissionByID(Long id){
        return emissionRepo.findById(id).orElse(null);
    }
    //public boolean EmissionContainsPartie(Long id_emission , int numPartie){
     //   return partieService.partieExisteDansEmission(numPartie , id_emission).isEmpty();
   // }
    //public List<Partie> getPartieXdeEmissionY(Long id_emission , ){

    //}
    public Emission AddPartieToEmission(Long emissionId, Partie partie ) {
        Optional<Emission> emission =emissionRepo.findById(emissionId);
        if(emission==null){
            return null;
                    //"emission null ";
        }else{
            int numpartie = partie.getNumpartie();
            Optional<Partie> existe = partieRepo.findPartieByNumpartieAndEmission_Id(numpartie , emissionId);
            if(existe.isEmpty()){
                Emission e= emission.get();
                partie.setEmission(e);
                Partie newp = partieRepo.save(partie);
                List<Partie> lp = e.getListepartie();
                lp.add(newp);
                e.setListepartie(lp);
                //emissionRepo.save(e);
                //Optional<Partie> existe2 = partieRepo.findPartieByNumpartieAndEmission_Id(numpartie , emissionId);
                return emissionRepo.save(e);
                //if(existe2.isEmpty()){
                 //   return null;
                            //"partie matzedetch";
                //}else
                 //   return null;
                            //"partie tzedet";
               // return "partie mahich mawjouda fel table bel idmte3 el emission w num emission";

            }else {
                return null;
                        //"partie mawjouda b num el emission hedha ";
            }
            // return "emission null";
            //return "emisssion non null ";
        }
    }
    public  String deletePartieFromEmission(Long id_partie , Long id_emission){
        Emission emission = emissionRepo.findById(id_emission).get();
        Partie partie = partieRepo.findById(id_partie).get();
        if(emission!=null){
            if(partie!=null){
                if(partie.getEmission().equals(emission)){
                    List<Partie> listpartie = emission.getListepartie();
                    listpartie.remove(partie);
                    //emission.getListepartie()
                    partie.setEmission(null);
                    emission.setListepartie(listpartie);
                    emissionRepo.save(emission);
                    partieRepo.deleteById(partie.getId());
                    return "suppression avec success !!";
                }
                return "partie n'existe pas dans l'emission donner ";
            }
            return "partie existe pas ";
        }
        return "emission n'existe pas";
    }

    public List<Partie> findListProduitOfEmission(Long id){
        Emission emission = emissionRepo.findById(id).get();
        if (emission== null){
            return null;
        }else {
            return emission.getListepartie();
        }
    }
    public  Emission updateInfoGeneralEmission(Long id , Emission emissionnew ){
      Emission emission = emissionRepo.findById(id).orElse(null);
      if (emission==null){
          return null;
      }else {

          emission.setDateEmission(emissionnew.getDateEmission());
          emission.setTitre(emissionnew.getTitre());
          emission.setSaison(emissionnew.getSaison());
          return emissionRepo.save(emission);
      }
    }
    public List<Emission> getAllSaisonOfEmission(String titre){
        Optional<List<Emission>> lemission = emissionRepo.findByTitre(titre);
        if(lemission.orElse(null)==null){
            return null ;
        }else
            return lemission.get();
    }

    public  Optional<String> getTitreEmissionAvecGrannbredePartie(){
        return emissionRepo.findTitreWithMostParties();
    }
    public int nbpartie(Long id){
        Optional<Emission> emission= emissionRepo.findById(id);
        if(emission.isEmpty()){
            return -1;
        }else
            return emissionRepo.countPartiesInEmission(id);
    }

    public  boolean delete(Long id){
        Optional<Emission> em = emissionRepo.findById(id);
        if(em.isEmpty()){
            return false;

        }else {
            emissionRepo.delete(em.get());
            return true;
        }
    }

    public Emission AddPersonneInterne(Long id_em , Long id_pr){
        Optional<Emission> emission = emissionRepo.findById(id_em);
        Optional<PersonneInterne> personneInterne= personneInterneRepo.findById(id_pr);
        if(emission.isEmpty()){
            return null;
                    //"verifier id de l'emission !!";
        }else {
            if(personneInterne.isEmpty()){
                return null;
                        //"verifier l'id de la personne interne";
            }else {
                Emission em = emission.get();
                PersonneInterne p= personneInterne.get();
                Set<PersonneInterne> lp= em.getEquipeTravail();
                lp.add(p);
                em.setEquipeTravail(lp);

                return emissionRepo.save(em);

                        //"najmo na3mlo ajout";
            }
        }

    }

    public Set<PersonneInterne> GetAllPersonneInterneDeEmission(Long id_em){
        Optional<Emission> emission = emissionRepo.findById(id_em);
        if(emission.isEmpty()){
            return null;
        }else
            return emission.get().getEquipeTravail();

    }
    public Emission DeletePersonneInterneFromEquipe(Long id_em , Long id_p){
        Optional<Emission> emission = emissionRepo.findById(id_em);
        Optional<PersonneInterne> personneInterne = personneInterneRepo.findById(id_p);
        if(emission.isEmpty() || personneInterne.isEmpty()){
            return  null;

            }else {
                PersonneInterne pr = personneInterne.get();
                Emission em=  emission.get();
                Set<PersonneInterne> lp= em.getEquipeTravail();
                if(!lp.contains(pr)){
                    return null;
                            //null;
                }else{
                    lp.remove(pr);
                    em.setEquipeTravail(lp);
                    //Emission newem =emissionRepo.save(em);
                    return emissionRepo.save(em);
                            //"removed";
                            //newem.getEquipeTravail();

                }
            }

    }

    public void GetdeDeEmissionPersonneDeRole(Long id_em , String role){

    }
    public void GetChroniqueureDansEmission(Long id_em){

    }

}
