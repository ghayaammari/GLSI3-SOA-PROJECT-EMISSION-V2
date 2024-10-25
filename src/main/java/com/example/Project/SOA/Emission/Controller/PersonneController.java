package com.example.Project.SOA.Emission.Controller;

import com.example.Project.SOA.Emission.Model.Personne;
import com.example.Project.SOA.Emission.Service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonneController {
    @Autowired
    PersonneService personneService;
    @GetMapping("/API/Personne/exist")
    boolean existperson(@RequestBody Personne p){
        return personneService.existpersonnewithdata(p.getNom(), p.getPrenom() , p.getTel());
    }
    @GetMapping("/API/Personne/getALL")
    ResponseEntity<List<Personne>> getAllPersonne(){
        return ResponseEntity.ok(personneService.getAllPersonne());
    }
    @PutMapping("/API/Personne/UpdateIno/{id}")
    ResponseEntity<Personne> UpdateInfoPersonelle(@PathVariable Long id , @RequestBody Personne newp){
        Personne person = personneService.Updateinfo(id , newp);
        if(person==null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(person);
        }
    }

    @GetMapping("/API/Personne/getById/{id}")
    ResponseEntity<Personne> getById(@PathVariable Long id ){
        Optional<Personne> person = personneService.getById(id);
        if (person.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else return ResponseEntity.ok(person.get());
    }
}
