package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="GamePlayer_id")
    private GamePlayer gamePlayer;

    private int turn;

    @ElementCollection
    @Column(name="salvoLocation")
    private List<String> salvoLocation = new ArrayList<>();

    public Salvo(){}

    public Salvo(GamePlayer gamePlayer, int turn, List salvoLocation) {
        this.turn = turn;
        this.gamePlayer = gamePlayer;
        this.salvoLocation = salvoLocation;
    }

    public long getId() {
        return id;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<String> getSalvoLocation() {
        return salvoLocation;
    }

    public void setSalvoLocation(List<String> salvoLocation) {
        this.salvoLocation = salvoLocation;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
