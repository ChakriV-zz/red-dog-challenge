package com.chakri.games.reddogchallenge.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    private List<Card> deckOfCards = new ArrayList<>();
    private Integer currentCardIndex = 0;


    public DeckOfCards() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deckOfCards.add(new Card(rank, suit));
            }
        }
    }

    public List<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public Integer getCurrentCardIndex() {
        return currentCardIndex;
    }

    public void setCurrentCardIndex(Integer currentCardIndex) {
        this.currentCardIndex = currentCardIndex;
    }

    public void shuffleCards() {
        Collections.shuffle(deckOfCards);
    }


    public void dealCards(List<Player> players) {
        currentCardIndex = 0;
        int noOfPlayers = players.size();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < noOfPlayers; j++) {
                players.get(j).getCardsInHand().add(dealCard());
            }
        }
    }
    public Card dealCard(){
        Card card = deckOfCards.get(currentCardIndex);
        currentCardIndex++;
        return card;
    }


}
