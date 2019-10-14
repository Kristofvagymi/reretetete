package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import hu.bme.aut.retelab2.domain.SecretGenerator;
import hu.bme.aut.retelab2.domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired(required = true)
    private JavaMailSender javaMailSender;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Transactional
    public Ad save(Ad feedback) {
        feedback.setCreationDate(LocalDateTime.now());
        feedback.setCode(SecretGenerator.generate());
        return em.merge(feedback);
    }

    public List<Ad> findByKeywordAndPrice(String keyword,int min, int max) {
         List<Ad> ads = em.createQuery("SELECT a FROM Ad a WHERE a.title LIKE :word AND a.price BETWEEN :min AND :max", Ad.class)
                .setParameter("word", '%' + keyword + '%')
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        return ads;
    }

    @Transactional
    public Ad update(Ad ad) {
        Ad oldAd = em.find(Ad.class, ad.getId());
        if(oldAd.getCode().equals(ad.getCode())){
            List<Subscription> subscriptions = subscriptionRepository.getSubscrioptionsByAdId(oldAd.getId());
            for(Subscription subscription : subscriptions){
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setFrom("betmanoperator@gmail.com");
                msg.setTo(subscription.getEmail());
                msg.setSubject("Ad updated");
                msg.setText("check website for new information");
                javaMailSender.send(msg);
            }
            return em.merge(ad);
        }else
            return null;
    }
}
