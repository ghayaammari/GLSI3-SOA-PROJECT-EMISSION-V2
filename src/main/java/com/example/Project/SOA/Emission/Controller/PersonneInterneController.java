package com.example.Project.SOA.Emission.Controller;

import com.example.Project.SOA.Emission.Model.PersonneInterne;
import com.example.Project.SOA.Emission.Service.PersonneInterneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonneInterneController {
    @Autowired
    PersonneInterneService personneInterneService;
    @PostMapping("/API/PersonneInterne/add")
    ResponseEntity<PersonneInterne> create(@RequestBody PersonneInterne personne){
        return ResponseEntity.ok(personneInterneService.create(personne));
    }
    @GetMapping("/API/PersonneInterne/getAll")
    ResponseEntity<List<PersonneInterne>> getAll(){
        return new ResponseEntity<>(personneInterneService.getAllDPDetails(), HttpStatus.OK);
    }

    @GetMapping("/API/PersonneInterne/get/{id}")
    ResponseEntity<PersonneInterne> getPersonneInternebyId(@PathVariable Long id ){
        PersonneInterne personneInterne = personneInterneService.getPersonneInterneById(id);
        if (personneInterne == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(personneInterne);
    }
    @PutMapping("/API/PersonneInterne/update/{id}")
    ResponseEntity<PersonneInterne> updatePersonneInterneData(@PathVariable Long id , @RequestBody PersonneInterne newversion){
        return ResponseEntity.ok(personneInterneService.updatePersonneInterne(id,newversion));
    }
    @DeleteMapping("/API/PersonneInterne/delete/{id}")
    ResponseEntity<String> deletePersonneInterne(@PathVariable Long id){
        if(personneInterneService.deletePersonneInternById(id)){
            return ResponseEntity.ok("deleted successfully");
        }else {
            return ResponseEntity.status(201).body("Personne Interne doesn't exist");        }
    }
    @GetMapping("/API/PersonneInterne/role/{role}")
    ResponseEntity<List<PersonneInterne>> getPersonneInterneWithrole(@PathVariable String role){
        return ResponseEntity.ok(personneInterneService.getByRoleDansTele(role));
    }
    @GetMapping("/API/PersonneInterne/name/{nom}")
    ResponseEntity<List<PersonneInterne>> getPersonneInterneAvecNom(@PathVariable String nom){
        List<PersonneInterne> personneInternes=  personneInterneService.getByNOM(nom);
        if (personneInternes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(personneInternes);
    }
    @GetMapping("/API/PersonneInterne/prenom/{prenom}")
    ResponseEntity<List<PersonneInterne>> getPersonneInterneAvecPrenom(@PathVariable String prenom){
        List<PersonneInterne> personneInternes =personneInterneService.getByPrenom(prenom);
        if(personneInternes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(personneInternes);
    }
    @GetMapping("/API/PersonneInterne/NomPrenom/{nom}/{prenom}")
    ResponseEntity<Optional<PersonneInterne>> getPersonneInterneAvecNOMPrenom(@PathVariable String nom , @PathVariable String prenom){
        Optional<PersonneInterne> persno = personneInterneService.getByNomPrenom(nom, prenom);
        if(persno.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(persno);
        }
    }
    @GetMapping("/API/PersonneInterne/heighestSalary")
    ResponseEntity<Optional<PersonneInterne>> getPersonHeighestSalary(){

        Optional<PersonneInterne> personneInterne= personneInterneService.getInternPersonneWithHeighstSalary();
        if (personneInterne.isEmpty()){
            return ResponseEntity.noContent().build();
        }else
            return ResponseEntity.ok(personneInterne);
    }

}
