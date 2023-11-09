package com.brazen.card.data.model;

import java.util.HashMap;
import java.util.Map;

public enum PokerHands {

	HIGH_CARD(1),
	ONE_PAIR(2),
	TWO_PAIR(3),
	THREE_OF_A_KIND(4),
	STRAIGHT(5),
	FLUSH(6),
	FULL_HOUSE(7),
	FOUR_OF_A_KIND(8),
	STRAIGHT_FLUSH(9),
	ROYAL_FLUSH(10);

	private int pokerHand;
    private static Map map = new HashMap<>();

    private PokerHands(int pokerHand) {
        this.pokerHand = pokerHand;
    }

    public void setPokerHand(int pokerHand) {
		this.pokerHand = pokerHand;
	}

	static {
        for (PokerHands pageType : PokerHands.values()) {
            map.put(pageType.pokerHand, pageType);
        }
    }

    public static PokerHands valueOf(int handType) {
        return (PokerHands) map.get(handType);
    }

    public int getPokerHand() {
        return pokerHand;
    }
}
