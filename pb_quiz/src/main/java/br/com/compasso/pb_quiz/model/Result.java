package br.com.compasso.pb_quiz.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String player;
    private long hits;
    private long misses;
    private final LocalDate matchDate = LocalDate.now();

    public String getPlayer() {
        return player;
    }

    public Result setPlayer(String player) {
        this.player = player;
        return this;
    }

    public long getHits() {
        return hits;
    }

    public Result setHits() {
        hits = hits + 1;
        System.out.println("Parabéns, você acertou");
        return this;
    }

    public Result setMisses() {
        misses = misses + 1;
        System.out.println("Você errou");
        return this;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

}
