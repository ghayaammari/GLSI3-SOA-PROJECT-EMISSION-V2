package com.example.Project.SOA.Emission.Service;

import com.example.Project.SOA.Emission.Model.Personne;
import com.example.Project.SOA.Emission.Repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    PersonneRepo personneRepo;
    public List<Personne > getAllPersonne(){
        return  personneRepo.findAll();
    }

    public  boolean existpersonnewithdata(String nom , String prenom , String tlp ){
        return personneRepo.existsByNomAndPrenomAndTel(nom , prenom , tlp);
    }
    public  Personne Updateinfo(Long id , Personne newp){
        Optional<Personne> person = personneRepo.findById(id);
        //personneRepo.findById().isEmpty()
        //personneRepo.findAll().isEmpty();
        //Long nb = 12L;
        //Personne personnnn =personneRepo.findById(nb).orElse(null);
        //personnnn.getId()
        //personneRepo.findById(nb).get();
        //personneRepo.deleteById();

        if(person.isEmpty()){
            return null;
        }else {
            Personne personne = person.get();
            personne.setNom(newp.getNom());
            personne.setPrenom(newp.getPrenom());
            personne.setTel(newp.getTel());
            return personneRepo.save(personne);
        }
    }
    public Optional<Personne> getById(Long id){
        return personneRepo.findById(id);
    }


}
