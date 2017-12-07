package com.chakri.games.reddogchallenge;

import com.chakri.games.reddogchallenge.game.model.Card;
import com.chakri.games.reddogchallenge.game.model.DeckOfCards;
import com.chakri.games.reddogchallenge.game.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RedDogChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedDogChallengeApplication.class, args);
    }

    private static int pot = 0;

    @PostConstruct
    public void init() throws InterruptedException {
        DeckOfCards deckOfCards = new DeckOfCards();
        deckOfCards.shuffleCards();


        play(deckOfCards);
    }

    private static void play(DeckOfCards deckOfCards) {
        List<Player> players = initializePlayers(4, 5);
        deckOfCards.dealCards(players);
        players.forEach(player -> {
            for (int i = 0; i < player.getCardsInHand().size(); i++) {
                System.out.print(player.getCardsInHand().get(i).getCard() + "\t\t");
            }
            System.out.println("");
        });
        System.out.println("Current Card position : " + deckOfCards.getCurrentCardIndex());
        initializePot(players);
        playRound(players, deckOfCards);
    }

    private static List<Player> initializePlayers(int noOfPlayers, int chipsPerPlayer) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player("Player-" + i, chipsPerPlayer));
        }
        return players;
    }

    private static void initializePot(List<Player> players) {
        for (Player player : players) {
            player.setChipsInHand(player.getChipsInHand() - 1);
        }
        pot = players.size();
    }

    private static void playRound(List<Player> players, DeckOfCards deckOfCards) {
        List<Player> playersToBeRemoved = new ArrayList<>();
        players
                .stream()
                .filter(player -> {
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
                                    if (player.getChipsInHand() == 0) {
                                        playersToBeRemoved.add(player);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    return false;
                })
                .findFirst()
                .orElse(null);

    }

    private static void refreshPlayers(List<Player> players) {
        players
                .stream()
                .filter(player -> player.getChipsInHand() == 0)

    }


}
