package com.example.Project.SOA.Emission.Service;

import com.example.Project.SOA.Emission.Model.Emission;
import com.example.Project.SOA.Emission.Model.Partie;
import com.example.Project.SOA.Emission.Model.PersonneExterne;
import com.example.Project.SOA.Emission.Repository.EmissionRepo;
import com.example.Project.SOA.Emission.Repository.PartieRepo;
import com.example.Project.SOA.Emission.Repository.PersonneExrterneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PartieService {
    @Autowired
    PartieRepo partieRepo;
    @Autowired
    EmissionRepo emissionRepo;
    @Autowired
    PersonneExrterneRepo personneExrterneRepo;

    public Partie create(Partie partie){
        return partieRepo.save(partie);
    }
    public List<Partie> getALLPartie(){
        return partieRepo.findAll();
    }
    public Optional<Partie> partieExisteDansEmission(int numpartie , Long id_emission){

        return partieRepo.findPartieByNumpartieAndEmission_Id(numpartie , id_emission);

    }
    public  Optional<Partie> getPartieById(Long id){
        return partieRepo.findById(id);
    }
    public Partie updateDureeNumPartie(Long id,String duree , int numpartie ){
        Partie partie = partieRepo.findById(id).orElse(null);
        if(partie!=null){
            partie.setDuree(duree);
            partie.setNumpartie(numpartie);
            return partieRepo.save(partie);
        }else {
            return null;
        }

    }
    public Boolean deletepartiebyid(Long id){
        Optional<Partie> partie = partieRepo.findById(id);
        if(partie.isEmpty()){
            return false;
        }else {
            Partie p = partie.get();
            Optional<Emission> em = emissionRepo.findEmissionByPartieId(p.getId() , p.getEmission().getId());
            if(em.isEmpty()){
                p.setEmission(null);
                partieRepo.deleteById(id);
                return true;
            }else {
                Emission e = em.get();
                List<Partie> lp = e.getListepartie();
                lp.remove(p);
                p.setEmission(null);
                partieRepo.delete(p);
                e.setListepartie(lp);
                emissionRepo.save(e);
                return true;

            }

            //return true;
        }

    }
    public Optional<Partie> findpartieavecemission(int nump , Long idme){
        return partieRepo.findPartieByNumpartieAndEmission_Id(nump, idme);
    }
    public Partie updatePartie(Long id , Partie partie){
        Optional<Partie> part= getPartieById(id);
        if(part.isEmpty()){
            return null;
        }else {
            Partie newp = part.get();
            newp.setNumpartie(partie.getNumpartie());
            newp.setDuree(partie.getDuree());

            return  partieRepo.save(newp);

        }
    }
    public Partie AddInviteeInPartie(Long id_partie , Long id_personne){
        Optional<Partie> partie = partieRepo.findById(id_partie);
        Optional<PersonneExterne> pex = personneExrterneRepo.findById(id_personne);
        if(partie.isEmpty() ||pex.isEmpty()) {
            return null;
        }else {

                Partie partie1 = partie.get();
                Set<PersonneExterne> lpex = partie1.getListeInvitee();
                PersonneExterne personneExterne = pex.get();
                if(lpex.contains(personneExterne)){
                    return null;
                }else {
                    lpex.add(personneExterne);
                    partie1.setListeInvitee(lpex);
                    return partieRepo.save(partie1);
                }
            }

    }
    public Set<PersonneExterne> getInviteeDansPartie(Long id ){
        Optional<Partie> part = partieRepo.findById(id);
        if(part.isEmpty()){
            return  null;
        }else {
            return part.get().getListeInvitee();
        }
    }
    public  Partie deleteInviteeFromPartie(Long id_partie , Long id_invitee){
        Optional<Partie> part = partieRepo.findById(id_partie);
        Optional<PersonneExterne> personex = personneExrterneRepo.findById(id_invitee);
        if(part.isEmpty() || personex.isEmpty()){
            return  null;
        }else {
            Partie partie = part.get();
            PersonneExterne personneExterne = personex.get();
            Set<PersonneExterne> lpex = partie.getListeInvitee();
            if(!lpex.contains(personneExterne)){
                return null;
            }else {
                lpex.remove(personneExterne);
                partie.setListeInvitee(lpex);
                return partieRepo.save(partie);
            }
        }
    }

}
