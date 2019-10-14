package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.SecretGenerator;
import hu.bme.aut.retelab2.domain.Subscription;
import hu.bme.aut.retelab2.dto.EmailDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SubscriptionRepository {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public Ad save(long adId, EmailDto emailDto) {
        Ad ad = em.find(Ad.class, adId);
        if (ad == null) {
            return null;
        } else {
            Subscription subscription = new Subscription();
            subscription.setEmail(emailDto.getEmail());
            ad.addSubscriber(subscription);
            return ad;
        }
    }

    @Transactional
    public List<Subscription> getSubscrioptionsByAdId(long id) {
        return em.createQuery("SELECT s FROM Subscription s WHERE s.ad.id = :id", Subscription.class)
                .setParameter("id", id)
                .getResultList();
    }
}
