package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="GamePlayer_id")
    private GamePlayer gamePlayer;

    private String type;

    @ElementCollection
    @Column(name="listOfLocations")
    private List<String> listOfLocations = new ArrayList<>();

    public Ship(){}

    public Ship(String type, GamePlayer gamePlayer, List listOfLocations) {
        this.type = type;
        this.gamePlayer = gamePlayer;
        this.listOfLocations = listOfLocations;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getListOfLocations() {
        return listOfLocations;
    }

    public void setListOfLocations(List<String> listOfLocations) {
        this.listOfLocations = listOfLocations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
