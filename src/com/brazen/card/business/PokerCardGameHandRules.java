package com.brazen.card.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.brazen.card.data.model.CardDeck;
import com.brazen.card.data.model.CardSuites;
import com.brazen.card.data.model.CardType;
import com.brazen.card.data.model.PokerHands;

public class PokerCardGameHandRules implements IPokerGameCardRule {

	HashMap<Integer, List<com.brazen.card.data.model.CardDeck>> map_PokerPlayerCards = new HashMap<Integer,List<CardDeck>>();
	
	@Override
	public PokerHands findMatchPokerWinningHand(List<CardDeck> playerCards) {
		
		//Get first heighest hand		
		PokerHands winningHand = getPokerHandOfFlush(playerCards);
		
		if(winningHand != PokerHands.HIGH_CARD) {
			
			return winningHand;
		} else {
			
			return getPokerHandByCardKinds(playerCards);
			
		}
		
	}

	@Override
	public void validateInputCards() {
		
		//validate cards from right deck and already used.
		
	}

	@Override
	public PokerHands getPokerHandOfFlush(List<CardDeck> playCards) {
		//Find for Flush
		Map<CardSuites, Integer> mapSuiteCountInDeck = new HashMap<>();
		
		for(CardDeck cd : playCards) {
			mapSuiteCountInDeck.put(cd.getCardSuit(), mapSuiteCountInDeck.getOrDefault(cd.getCardSuit(), 0)+1);
		}
		
		List<CardDeck> flushCards = new ArrayList<>();
		for(Entry<CardSuites, Integer> ct : mapSuiteCountInDeck.entrySet()) {
			if(ct.getValue() >= 5 ) {				
				for(CardDeck cd : playCards) {					
					if(cd.getCardSuit() == ct.getKey()) {
						flushCards.add(cd);
					}					
				}
				
				//it is not possible to get more than one flush present
				if(findRoyalInPlayerCards(flushCards))
					return PokerHands.ROYAL_FLUSH;
				else if (findStraightInPlayerCards(flushCards))
					return PokerHands.STRAIGHT_FLUSH;
			}	
		}
		
		return PokerHands.HIGH_CARD;
	}

	@Override
	public PokerHands getPokerHandByCardKinds(List<CardDeck> playCards) {
		//Find for four of Kind.
		Map<CardType, Integer> mapCardCountInPlayerCards = new HashMap<>();
		int countPairs = 0;
		Boolean isThreeOfKindExists = false;
		for(CardDeck cd : playCards)
			mapCardCountInPlayerCards.put(cd.getCardType(), mapCardCountInPlayerCards.getOrDefault(cd.getCardType(), 0)+1);
		
		for(Entry<CardType, Integer> ct : mapCardCountInPlayerCards.entrySet()) {
			if(ct.getValue() == 4 )
				return PokerHands.FOUR_OF_A_KIND;
			else if(ct.getValue() == 3)
				isThreeOfKindExists = true;
			else if (ct.getValue() == 2)
				countPairs++;
		}
		
        if (countPairs == 1 && isThreeOfKindExists)
			return PokerHands.FULL_HOUSE;
        else if(findStraightInPlayerCards(playCards))
        	return PokerHands.STRAIGHT;
		else if (isThreeOfKindExists && countPairs == 0)
			return PokerHands.THREE_OF_A_KIND;
		else if(countPairs == 2 && !isThreeOfKindExists) 
			return PokerHands.TWO_PAIR;
		else if(countPairs == 1 && !isThreeOfKindExists)
			 return PokerHands.ONE_PAIR;
		
		return PokerHands.HIGH_CARD;
	}

	@Override
	public Boolean findStraightInPlayerCards(List<CardDeck> playCards) {
		
		 Comparator<CardDeck> comparator = Comparator.comparing(CardDeck::getCardType);
		 playCards.sort(comparator);
		 
		 Boolean isSequencePresent = true;	
		 int fiveCardStraight = 0;
		 for (int i = 1; i < playCards.size(); i++) {
			 
		 if(playCards.get(i).getCardType().cardType() ==  playCards.get(i-1).getCardType().cardType())
			 i++;
		 if((playCards.get(i).getCardType().cardType() -  playCards.get(i-1).getCardType().cardType()) > 1) {
			 isSequencePresent = false;
			 break;
		 }
		 fiveCardStraight++;
		 
	 }
	 
	 if(isSequencePresent == false && fiveCardStraight < 4)
		return findAceStraightInPlayerCards(playCards);
	 
	 return true;		 
	 
}

@Override
public Boolean findAceStraightInPlayerCards(List<CardDeck> playCards) {
	
	 Comparator<CardDeck> comparator = Comparator.comparing(CardDeck::getCardType);
	 playCards.sort(comparator.reversed());
	 
	 Boolean isAcePresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.ACE.cardType());
	 
	 if (isAcePresent) {

		 Boolean isKingPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.KING.cardType());
			 Boolean isQueenPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.QUEEN.cardType());
			 Boolean isJackPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.JACK.cardType());
			 Boolean isTenPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.TEN.cardType());
			 
			 if(isKingPresent && isQueenPresent && isJackPresent && isTenPresent) {
				 return true;
			 }
		}
	 
	  return false;		 
		 
	}
	
	@Override
	public Boolean findRoyalInPlayerCards(List<CardDeck> playCards) {
		
		 Comparator<CardDeck> comparator = Comparator.comparing(CardDeck::getCardType);
		 playCards.sort(comparator);
		 		 
		 Boolean isAcePresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.ACE.cardType());
		 
		 if(isAcePresent) {
			 
			 Boolean isKingPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.KING.cardType());
			 Boolean isQueenPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.QUEEN.cardType());
			 Boolean isJackPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.JACK.cardType());
			 Boolean isTenPresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.TEN.cardType());
			 
			 if(isKingPresent && isQueenPresent && isJackPresent && isTenPresent) {
				 return true;
			 }
			 
		 }
	 
		 return false;
	}

	@Override
	public CardType findHighCardInPlayerCards(List<CardDeck> playCards) {
	
		 Boolean isAcePresent = playCards.stream().anyMatch(x -> x.getCardType().cardType() == CardType.ACE.cardType());
		 
		 if(isAcePresent) 
			 return CardType.ACE;
		 else {
			 
			 Comparator<CardDeck> comparator = Comparator.comparing(CardDeck::getCardType);
			 playCards.sort(comparator.reversed());
			 
			 return playCards.get(0).getCardType();		 
			 
		 }
	}

	@Override
	public int getWinnerOfPokerGame(List<CardDeck> Play1, List<CardDeck> Play2) {
		
		PokerHands ph = PokerHands.HIGH_CARD;
		PokerHands winPlayer1 = findMatchPokerWinningHand(Play1);

		PokerHands winPlayer2 = findMatchPokerWinningHand(Play2);
		if(winPlayer1.getPokerHand() == winPlayer2.getPokerHand()) {
			
			if(findHighCardInPlayerCards(Play1).cardType() == 1 && 
			   findHighCardInPlayerCards(Play2).cardType() != 1) {				
				ph.setPokerHand(winPlayer1.getPokerHand());
				return 1;
			} else if (findHighCardInPlayerCards(Play2).cardType()== 1 && 
			   findHighCardInPlayerCards(Play1).cardType() != 1) {
				ph.setPokerHand(winPlayer2.getPokerHand());
				  return 2;
			} else if (findHighCardInPlayerCards(Play1).cardType() > 
					   findHighCardInPlayerCards(Play2).cardType()) {
				ph.setPokerHand(winPlayer1.getPokerHand());
				return 1;
			} else if (findHighCardInPlayerCards(Play2).cardType() > 
			   findHighCardInPlayerCards(Play1).cardType()) {
				ph.setPokerHand(winPlayer2.getPokerHand());
				return 2;
			} else {
				return 0;
			}

		} else if(winPlayer1.getPokerHand() > winPlayer2.getPokerHand()) {
			ph.setPokerHand(winPlayer1.getPokerHand());
			return 1;
		} else {
			ph.setPokerHand(winPlayer2.getPokerHand());
			return 2;
		}
	}
	
	

}
