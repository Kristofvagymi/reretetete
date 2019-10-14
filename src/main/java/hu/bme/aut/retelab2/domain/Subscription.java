package hu.bme.aut.retelab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="subscription")
public class Subscription {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private long id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adId")
    private Ad ad;
}
