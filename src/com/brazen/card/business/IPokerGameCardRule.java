package com.brazen.card.business;

import java.util.List;

import com.brazen.card.data.model.CardDeck;
import com.brazen.card.data.model.CardType;
import com.brazen.card.data.model.PokerHands;

public interface IPokerGameCardRule {
	
	void validateInputCards();
	PokerHands findMatchPokerWinningHand(List<CardDeck> playCards);	
	PokerHands getPokerHandOfFlush(List<CardDeck> playCards);
	PokerHands getPokerHandByCardKinds(List<CardDeck> playCards);
	Boolean findAceStraightInPlayerCards(List<CardDeck> playCards);
	Boolean findStraightInPlayerCards(List<CardDeck> playCards);
    Boolean findRoyalInPlayerCards(List<CardDeck> playCards);
    
    CardType findHighCardInPlayerCards(List<CardDeck> playCards);
    
    int getWinnerOfPokerGame(List<CardDeck> P1, List<CardDeck> P2);
}
