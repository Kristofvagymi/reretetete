package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ad")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping(path = "/create")
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        Ad createdAd = adRepository.save(ad);
        // createdAd.setCode("");
        return createdAd;
    }

    @GetMapping(path = "/find")
    public List<Ad> create(@RequestParam("keyword") String keyword, @RequestParam("min") int min, @RequestParam("max") int max) {
        if(max == 0)
            max =  10000000;
        List<Ad> ads = adRepository.findByKeywordAndPrice(keyword,min,max);
        for(Ad ad : ads){
            ad.setCode("");
            int size = ad.getSubscribers().size();
            System.out.println(size);
        }
        return ads;
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody Ad ad) {
        Ad updatedAd = adRepository.update(ad);
        if(updatedAd == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }else
            return new ResponseEntity<Ad>(updatedAd, HttpStatus.OK);
    }
}
