package com.brazen.card.data.model;

public class CardDeck {
	
	private CardType cardType;
	private CardSuites cardSuit;
	
	public CardDeck(CardType cardType, CardSuites cardSuit) {
		super();
		this.cardType = cardType;
		this.cardSuit = cardSuit;
	}
	
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public CardSuites getCardSuit() {
		return cardSuit;
	}
	public void setCardSuit(CardSuites cardSuit) {
		this.cardSuit = cardSuit;
	}
}
