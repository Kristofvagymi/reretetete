package hu.bme.aut.retelab2.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ad")
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    private LocalDateTime creationDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public List<Subscription> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscription> subscribers) {
        this.subscribers = subscribers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Subscription> subscribers = new ArrayList<>();

    public void addSubscriber(Subscription subscriber) {
        subscribers.add(subscriber);
        subscriber.setAd(this);
    }

    public void removeSubscriber(Subscription subscriber) {
        subscribers.remove(subscriber);
        subscriber.setAd(null);
    }
}
