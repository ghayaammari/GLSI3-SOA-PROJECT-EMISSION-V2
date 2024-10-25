package com.example.Project.SOA.Emission.Service;

import com.example.Project.SOA.Emission.Model.Personne;
import com.example.Project.SOA.Emission.Model.PersonneExterne;
import com.example.Project.SOA.Emission.Repository.PersonneExrterneRepo;
import com.example.Project.SOA.Emission.Repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneExterneService {
    @Autowired
    PersonneExrterneRepo personneExrterneRepo;
    @Autowired
    PersonneRepo personneRepo;
    public PersonneExterne create(PersonneExterne personneExterne){
        if (personneExterne.getDonnePers() == null) {
            return null;
        } else {
            Personne dp = personneExterne.getDonnePers();
            if(personneRepo.existsByNomAndPrenomAndTel(dp.getNom(), dp.getPrenom(), dp.getTel())){
                return  null ;
            }else{
                Personne personne =personneRepo.save(dp);
                personneExterne.setDonnePers(personne);
                return personneExrterneRepo.save(personneExterne);
            }


        }
    }
    public List<PersonneExterne> getAll(){
        return personneExrterneRepo.findAll();
    }

    public Optional<PersonneExterne> getById(Long id){
        return  personneExrterneRepo.findById(id);
    }

    public PersonneExterne updateInfo(Long id , PersonneExterne newdata){
        Optional<PersonneExterne> person = personneExrterneRepo.findById(id);
        if (person.isEmpty()){
            return null;
        }else {
            PersonneExterne personneExterne= person.get();
            personneExterne.setDomaine(newdata.getDomaine());
            personneExterne.setTravailleActuelle(newdata.getTravailleActuelle());
            return personneExrterneRepo.save(personneExterne);
        }
    }

    public  boolean delete(Long id){
        personneExrterneRepo.deleteById(id);
        PersonneExterne p =personneExrterneRepo.findById(id).get();
        if(p==null){
            return false;
        }else
            return true;
    }
    //delete lezem 9bal matfasa5 tfasa5o mel les parties elli mawjoud fehom w ba3ed tfas5o

    public List<PersonneExterne> findPersonneExterneby(String titre , int nmem){
        return personneExrterneRepo.findPersonnesExternesByEmissionTitreAndPartieNumero(titre , nmem);
    }
    public List<PersonneExterne> findPersonneExternebyTitreEmission(String titre){
        return personneExrterneRepo.findPersonnesExternesByEmissionTitre(titre);
    }

    public List<PersonneExterne> findPersonneExterneByTravaille(String  travaille){
        return personneExrterneRepo.findByTravailleActuelle(travaille);
    }
    public List<PersonneExterne> findPersonnebyDomaine(String domaine){
        return personneExrterneRepo.findPersonneExterneByDomaine(domaine);
    }
}
