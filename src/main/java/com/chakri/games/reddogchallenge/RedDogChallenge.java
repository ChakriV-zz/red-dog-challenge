package com.chakri.games.reddogchallenge;

import com.chakri.games.reddogchallenge.game.model.Card;
import com.chakri.games.reddogchallenge.game.model.DeckOfCards;
import com.chakri.games.reddogchallenge.game.model.Player;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RedDogChallenge {
    private static int pot = 0;

    public static void main(String[] args) {
        DeckOfCards deckOfCards = new DeckOfCards();
        List<Player> players = initializePlayers(4, 5);
        while (players.size() != 1) {
            deckOfCards.shuffleCards();
            deckOfCards.dealCards(players);
            play(players, deckOfCards);
        }
        System.out.println(players.get(0).getPlayerName() + " is the winner");
    }

    private static void play(List<Player> players, DeckOfCards deckOfCards) {
        System.out.println("Playing");
        initializePot(players);
        for (Player player : players) {
            if (pot == 0) {
                initializePot(players);
            }
            playRound(player, deckOfCards);
        }
    }

    private static List<Player> initializePlayers(int noOfPlayers, int chipsPerPlayer) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player("Player-" + i, chipsPerPlayer));
        }
        return players;
    }

    private static void initializePot(List<Player> players) {
        removeLostPlayers(players);
        for (Player player : players) {
            player.setChipsInHand(player.getChipsInHand() - 1);
        }
        pot = players.size();
    }

    private static void removeLostPlayers(List<Player> players) {
        List<Player> lostPlayers = players.stream().filter(player -> player.getChipsInHand() == 0).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(lostPlayers)) {
            players.removeAll(lostPlayers);
        }
    }

    private static void playRound(Player player, DeckOfCards deckOfCards) {
        Integer playerChips = player.getChipsInHand();
        int playerBet = ThreadLocalRandom.current().nextInt(0, Integer.max(playerChips, pot) + 1);
        if (playerBet >= 1) {
            pot = pot + playerBet;
            Card dealtCard = deckOfCards.dealCard();
            for (Card card : player.getCardsInHand()) {
                if (card.getSuit().equals(dealtCard.getSuit())) {
                    if (card.getRank().getRankValue() > dealtCard.getRank().getRankValue()) {
                        pot = pot - playerBet;
                        player.setChipsInHand(playerChips + playerBet);
                    } else {
                        player.setChipsInHand(playerChips - playerBet);
                    }
                }
            }
        }
    }

}
