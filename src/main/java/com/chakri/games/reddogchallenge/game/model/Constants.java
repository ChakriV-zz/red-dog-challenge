package com.chakri.games.reddogchallenge.game.model;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<Integer, String> SUITS = new HashMap<>();
    public static final Map<Integer, String> RANK = new HashMap<>();

    static {
        SUITS.put(0, "CLUBS");
        SUITS.put(1, "DIAMONDS");
        SUITS.put(2, "HEARTS");
        SUITS.put(3, "SPADES");
    }
}
