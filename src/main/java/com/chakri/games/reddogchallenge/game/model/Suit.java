package com.chakri.games.reddogchallenge.game.model;

public enum Suit {
    SPADES(1),
    HEARTS(2),
    DIAMONDS(3),
    CLUBS(4);

    private final Integer suit;
    Suit(Integer suit) {
        this.suit = suit;
    }

    public Integer getSuit() {
        return suit;
    }
}
