package com.chakri.games.reddogchallenge.game.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private List<Card> cardsInHand = new ArrayList<>();
    private int chipsInHand = 0;

    public Player(String playerName, Integer chipsPerPlayer) {
        this.playerName = playerName;
        this.chipsInHand = chipsPerPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public Integer getChipsInHand() {
        return chipsInHand;
    }

    public void setChipsInHand(Integer chipsInHand) {
        this.chipsInHand = chipsInHand;
    }
}
