package com.example.Project.SOA.Emission.Controller;

import com.example.Project.SOA.Emission.Model.PersonneExterne;
import com.example.Project.SOA.Emission.Service.PersonneExterneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonneExterneController {
    @Autowired
    PersonneExterneService personneExterneService;

    @PostMapping("/API/PersonneExterne/add")
    ResponseEntity<PersonneExterne> create(@RequestBody PersonneExterne personneExterne){
        PersonneExterne newp = personneExterneService.create(personneExterne);
        if (newp== null){
            return ResponseEntity.badRequest().build();
        }else
            return ResponseEntity.ok(newp);
    }
    @GetMapping("/API/PersonneExterne/getAll")
    ResponseEntity<List<PersonneExterne>> getAll(){
        return ResponseEntity.ok(personneExterneService.getAll());
    }
    @GetMapping("/API/PersonneExterne/getById/{id}")
    ResponseEntity<PersonneExterne> getById(@PathVariable Long id){
        Optional<PersonneExterne> pex = personneExterneService.getById(id);
        if (pex.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else
            return ResponseEntity.ok(pex.get());
    }
    @PutMapping("/API/PersonneExterne/update/{id}")
    ResponseEntity<PersonneExterne> updateinfo(@PathVariable Long id , @RequestBody PersonneExterne person){
        PersonneExterne newp = personneExterneService.updateInfo(id , person);
        if (newp==null){
            return ResponseEntity.badRequest().build();
        }else return ResponseEntity.ok(newp);
    }
    @DeleteMapping("/API/PersonneExterne/delete/{id}")
    boolean delete(@PathVariable Long id){
        return personneExterneService.delete(id);
    }
    @GetMapping("/API/PersonneExterne/findbyemissioandnumparte")
    List<PersonneExterne> find(@RequestParam String titre , @RequestParam int n){
        return personneExterneService.findPersonneExterneby(titre , n);
    }
    @GetMapping("/API/PersonneExterne/findbytitreEmission/{titre}")
    List<PersonneExterne> findbemissiontitre(@PathVariable String titre){
        return personneExterneService.findPersonneExternebyTitreEmission(titre);
    }
    @GetMapping("/API/PersonneExterne/findbyTravaille/{travaille}")
    List<PersonneExterne> findbyTravaille(@PathVariable String travaille){
        return personneExterneService.findPersonneExterneByTravaille(travaille);
    }
    @GetMapping("/API/PersonneExterne/findBydomaine/{domaine}")
    List<PersonneExterne> findpxbydomaine(@PathVariable String domaine){
        return personneExterneService.findPersonnebyDomaine(domaine);
    }
}
