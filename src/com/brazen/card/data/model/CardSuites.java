package com.brazen.card.data.model;

public enum CardSuites {

	//Four card suit types
	SPADES(1),
	DIAMONDS (2),
	CLUBS (3),
	HEARTS(4);

	private final Integer cardSuitType;

	CardSuites(Integer suitType) {
		this.cardSuitType = suitType;
	}

	public Integer getCardSuitType() {
		return this.cardSuitType;
	}
}
