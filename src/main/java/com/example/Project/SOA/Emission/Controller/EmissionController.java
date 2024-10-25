package com.example.Project.SOA.Emission.Controller;

import com.example.Project.SOA.Emission.Model.Emission;
import com.example.Project.SOA.Emission.Model.Partie;
import com.example.Project.SOA.Emission.Model.PersonneInterne;
import com.example.Project.SOA.Emission.Service.EmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class EmissionController {
    @Autowired
    EmissionService emissionService;
    @PostMapping("/API/Emission/add")
    ResponseEntity<Emission> AddEmission(@RequestBody Emission emission){
        return  ResponseEntity.ok(emissionService.CreateEm(emission));
    }
    @GetMapping("/API/Emission/GetALL")
    ResponseEntity<List<Emission>> GetAllEmission(){
        return ResponseEntity.ok(emissionService.getAllEmission());
    }
    @PostMapping("/API/Emission/AddPartieToEmission/{id}")
    ResponseEntity<Emission> AddPartieInEmission(@PathVariable Long id , @RequestBody Partie partie){
        Emission newemission = emissionService.AddPartieToEmission(id , partie);
        if(newemission==null)
            return ResponseEntity.status(400).build();
        return //ResponseEntity.ok(emissionService.AddPartieToEmission(id , partie));
                ResponseEntity.ok(newemission);
    }
    @DeleteMapping("/API/Emission/DeletePartieFromEmission")
    String deletePartieFromEmission(@RequestParam Long id_emission , @RequestParam Long id_partie){
        return emissionService.deletePartieFromEmission(id_partie , id_emission);
    }
    @GetMapping("/API/Emission/getbyid/{id}")
    Emission getEmissionbyID(@PathVariable  Long id){
        return  emissionService.getEmissionByID(id);
    }
    @GetMapping("/API/Emission/getAllPartie/{id}")
    ResponseEntity<List<Partie>> getAllPartieOfEmission(@PathVariable Long id )
    {
        List<Partie> listpartie= emissionService.findListProduitOfEmission(id);
        if(listpartie== null){
            return ResponseEntity.badRequest().build();
        }else if (listpartie.isEmpty()){
            return ResponseEntity.status(204).build();

        }else {
            return ResponseEntity.ok(listpartie);
        }
    }

    @PutMapping("/API/Emission/UpdateInfoGenerale/{id}")
    ResponseEntity<Emission> updateInoGeneraleInfo(@PathVariable Long id , @RequestBody Emission newemission   ){
        Emission emission= emissionService.updateInfoGeneralEmission(id , newemission);
        if(emission==null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(emission);
    }
    @GetMapping("/API/Emission/GetAllSaisonOfEmission/{titre}")
    ResponseEntity<List<Emission>> getAllSaissonOfEmissionX(@PathVariable String titre){
        List<Emission> emissionList = emissionService.getAllSaisonOfEmission(titre);
        if(!emissionList.isEmpty()){
            return ResponseEntity.ok(emissionList);
        }else
            return ResponseEntity.noContent().build();

    }
    @GetMapping("/API/Emission/findTitreWithMostParties")
    ResponseEntity<String> getEmissionTitreWithHeighPartie(){
        Optional<String> rp = emissionService.getTitreEmissionAvecGrannbredePartie();
        if(rp.isEmpty()){
            return ResponseEntity.status(204).build();
        }else
            return ResponseEntity.ok(rp.get());
    }
    @GetMapping("/API/Emission/nbpartie/{id}")
    ResponseEntity<Integer> getNBPartie(@PathVariable Long id){
        return ResponseEntity.ok(emissionService.nbpartie(id));
    }
    @DeleteMapping("/API/Emission/deleteemission/{id}")
    ResponseEntity<String> deleteEmission(@PathVariable Long id ){
        if(emissionService.delete(id)){
            return ResponseEntity.ok("supprime avec success !!");
        }else
            return ResponseEntity.status(400).body("problem accured !!");
    }
    @PostMapping("/API/Emission/AddPersonneinEquipe")
    ResponseEntity<Emission> AddPersonneInterneToEquipeTR(@RequestParam  Long id_em , @RequestParam Long id_p ){
        Emission em = emissionService.AddPersonneInterne(id_em , id_p);
        if(em==null){
            return ResponseEntity.badRequest().body(em);
        }else
            return ResponseEntity.ok(em);

    }
    @GetMapping("/API/Emission/GetEquipeDeTravaille/{id}")
    ResponseEntity<Set<PersonneInterne>> getEquipeDeTravaille(@PathVariable Long id){
        Set<PersonneInterne> pi = emissionService.GetAllPersonneInterneDeEmission(id);
        if(pi==null){
            return ResponseEntity.badRequest().build();
        }else return ResponseEntity.ok(pi);
    }
    @DeleteMapping("/API/Emission/DeletePersonneFromEquipeEmission")
    ResponseEntity<Emission> deletPersonneInterneEmission(@RequestParam Long id_em , @RequestParam Long id_pi){
        Emission personneInternes = emissionService.DeletePersonneInterneFromEquipe(id_em, id_pi);
        if(personneInternes==null){
            return ResponseEntity.badRequest().build();
        }else return ResponseEntity.ok(personneInternes);
    }



}
