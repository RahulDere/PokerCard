package com.brazen.card.data.model;

public enum CardType {

	//Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 Jack,12 Queen, 13 King.
	
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13);
	
	private final Integer cardType;
	//private final CardSuites cardSuitType;

	CardType(Integer deckType) {
		this.cardType = deckType;
	}

	public Integer cardType() {
		return this.cardType;
	}	
	
}
