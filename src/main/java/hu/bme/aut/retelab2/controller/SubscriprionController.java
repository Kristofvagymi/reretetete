package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.dto.EmailDto;
import hu.bme.aut.retelab2.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriprion")
public class SubscriprionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @PostMapping(path = "/create/{id}")
    public Ad createSubscription(@RequestBody EmailDto emailDto, @PathVariable long id){
        return subscriptionRepository.save(id, emailDto);
    }
}
