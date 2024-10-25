package com.example.Project.SOA.Emission.Service;

import com.example.Project.SOA.Emission.Model.Personne;
import com.example.Project.SOA.Emission.Model.PersonneInterne;
import com.example.Project.SOA.Emission.Repository.PersonneInterneRepo;
import com.example.Project.SOA.Emission.Repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneInterneService {
    @Autowired
    PersonneInterneRepo personneInterneRepo;
    @Autowired
    PersonneRepo personneRepo;
    public PersonneInterne create(PersonneInterne newInternPerson){
        if (newInternPerson.getDonnePers() == null) {
            return null;
        } else {
            Personne dp = newInternPerson.getDonnePers();
            if(personneRepo.existsByNomAndPrenomAndTel(dp.getNom(), dp.getPrenom(), dp.getTel())){
                return  null ;
            }else{
                Personne personne =personneRepo.save(dp);
                newInternPerson.setDonnePers(personne);
                return personneInterneRepo.save(newInternPerson);
            }


        }


    }
    public List<PersonneInterne> getAllDPDetails(){
        return personneInterneRepo.findAllWithDonnePers();
    }
    public List<PersonneInterne> getAllDefault(){
        return personneInterneRepo.findAll();
    }
    public boolean  deletePersonneInternById(Long id ){
        PersonneInterne person = personneInterneRepo.findById(id).orElse(null);
        if(person==null){
            return false;
        }else {
            personneInterneRepo.deleteById(id);
            return  personneInterneRepo.findById(id).orElse(null)==null ;
        }
    }
    public  PersonneInterne getPersonneInterneById(Long id){
        return personneInterneRepo.findById(id).orElse(null);
    }

    public  PersonneInterne updatePersonneInterne(Long id , PersonneInterne updatedPersonne){
        Optional<PersonneInterne> optionalOldVersion = personneInterneRepo.findById(id);

        if (optionalOldVersion.isPresent()) {
            PersonneInterne oldVersion = optionalOldVersion.get();

            // Mettez à jour les champs de PersonneInterne
            oldVersion.setRoleDansTele(updatedPersonne.getRoleDansTele());
            oldVersion.setSalaire(updatedPersonne.getSalaire());
            oldVersion.setDateEmbauche(updatedPersonne.getDateEmbauche());

            // Mettez à jour les champs de la personne associée
            Personne updatedPersonneInfo = updatedPersonne.getDonnePers();
            if (updatedPersonneInfo != null) {
                Personne oldPersonne = oldVersion.getDonnePers();

                if (oldPersonne == null) {
                    // Si la personne associée n'existe pas, sauvegardez-la
                    personneRepo.save(updatedPersonneInfo);
                    oldVersion.setDonnePers(updatedPersonneInfo);
                } else {
                    // Si la personne associée existe, mettez à jour ses champs
                    oldPersonne.setNom(updatedPersonneInfo.getNom());
                    oldPersonne.setPrenom(updatedPersonneInfo.getPrenom());
                    oldPersonne.setTel(updatedPersonneInfo.getTel());
                    personneRepo.save(oldPersonne);
                }
            }

            // Sauvegardez la PersonneInterne mise à jour
            return personneInterneRepo.save(oldVersion);
        } else {
            // Retournez null ou effectuez une action en fonction de vos besoins métier
            return null;
        }
    }
    public List<PersonneInterne> getByRoleDansTele(String role){
        return  personneInterneRepo.findByRoleDansTele(role);
        //findByRoleDansTele
        //findByRoleDansTele(role);
    }

    public List<PersonneInterne> getByNOM(String nom){
        return personneInterneRepo.findByNomInPersonne(nom);
    }
    public List<PersonneInterne> getByPrenom(String prenom){
        return personneInterneRepo.findByPrenomInPersonne(prenom);

    }
    public Optional<PersonneInterne> getByNomPrenom(String nom , String prenom){
        return  personneInterneRepo.findByNomAndPrenom(nom , prenom);
    }
    public  Optional<PersonneInterne> getInternPersonneWithHeighstSalary(){
        return personneInterneRepo.findPersonneWithHighestSalaire();
    }


}
