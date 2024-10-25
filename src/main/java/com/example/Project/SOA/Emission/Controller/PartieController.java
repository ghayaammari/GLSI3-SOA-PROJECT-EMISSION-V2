package com.example.Project.SOA.Emission.Controller;

import com.example.Project.SOA.Emission.Model.Partie;
import com.example.Project.SOA.Emission.Model.PersonneExterne;
import com.example.Project.SOA.Emission.Service.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class PartieController {
    @Autowired
    PartieService partieService;
    @PostMapping("/API/Partie/add")
    ResponseEntity<Partie> addPartie(@RequestBody Partie partie){
       return ResponseEntity.ok(partieService.create(partie)) ;
    }
    @GetMapping("/API/Partie/GetALL")
    ResponseEntity<List<Partie>> getAllPartie(){
        List<Partie> partieList = partieService.getALLPartie();
        if(partieList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.ok(partieList);
    }
    @GetMapping("/API/Partie/GetPartieById/{id}")
    ResponseEntity<Partie> getPartieById(@PathVariable Long id){
        Optional<Partie> partie = partieService.getPartieById(id);
        if(partie==null){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(partie.get());
        }

    }
    @DeleteMapping("/API/Partie/deletPartieById/{id}")
    ResponseEntity<String> deletePartieById(@PathVariable Long id){
        if(partieService.deletepartiebyid(id)){
            return ResponseEntity.ok("Supprime avec succes !!");
        }else
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Partie n'existe pas dans la base de donne");
    }
    @GetMapping("/API/Partie/GETPartieEmissionNum/{nump}/{idem}")
    Optional<Partie> gettt(@PathVariable int nump , @PathVariable Long idem){
        return partieService.partieExisteDansEmission(nump, idem);
    }
    @PutMapping("/API/Partie/UpdateInfoGeneral/{id}")
    ResponseEntity<Partie> UpdateInfoGenearlPartie(@PathVariable Long id , @RequestBody Partie partie){
        Partie upadted= partieService.updatePartie(id , partie);
        if(partie==null){
            return ResponseEntity.status(400).build();
        }else {
            return ResponseEntity.ok(upadted);
        }
    }
    @PostMapping("/API/Partie/AddInviteeToPartie")
    ResponseEntity<Partie> AddInviteeInPartie(@RequestParam Long id_part , @RequestParam Long id_person){
        Partie newpart = partieService.AddInviteeInPartie(id_part , id_person);
        if(newpart==null){
            return ResponseEntity.badRequest().build();
        }else {
            return  ResponseEntity.ok(newpart);
        }
    }

    @GetMapping("/API/Partie/GetAllInviteeInPartie/{id}")
    ResponseEntity<Set<PersonneExterne>> getAllInviteeInPart(@PathVariable Long id){
        Set<PersonneExterne> lpex = partieService.getInviteeDansPartie(id);
        if(lpex.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        else
            return ResponseEntity.ok(lpex);
    }
    @DeleteMapping("/API/Partie/DeleteInviteeFromPartie")
    ResponseEntity<Partie> deleteInviteeFromPart(@RequestParam Long id_part , @RequestParam Long id_person){
        Partie partie = partieService.deleteInviteeFromPartie(id_part , id_person);
        if(partie==null){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(partie);
        }
    }
}
