package com.chakri.games.reddogchallenge.game.service;

import com.chakri.games.reddogchallenge.game.model.Card;
import com.chakri.games.reddogchallenge.game.model.Player;

import java.util.List;
import java.util.Random;

import static com.chakri.games.reddogchallenge.game.model.Constants.SUITS;

public class RedDogService {
    public static void main(String args[]) {

//        1.) Initialize and shuffle deck of cards.
//        2.) Initialize players and deal cards.
//        5.) A play function (game engine?? || command-pattern) that manages turns/tracking params.

        Card[] deckOfCards = getDeckOfCards();
        shuffleCards(deckOfCards);
    }



    private static Card[] getDeckOfCards() {
        Card[] deckOfCards = new Card[52];
        int i = 0;

        for (Integer suit = 0; suit < SUITS.size(); suit++) {
            for (Integer rank = 1; rank <= 13; rank++) {
                deckOfCards[i++] = new Card(rank.toString(), SUITS.get(suit));
            }
        }
        return deckOfCards;
    }

    private static void shuffleCards(Card[] deckOfCards) {
        int index;
        Card tempCard;
        Random random = new Random();
        for (int i = deckOfCards.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            tempCard = deckOfCards[index];
            deckOfCards[index] = deckOfCards[i];
            deckOfCards[i] = tempCard;
        }
    }

    private void dealCards(List<Player> players){

    }
}

