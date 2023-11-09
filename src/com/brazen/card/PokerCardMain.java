package com.brazen.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.brazen.card.business.AddPlayersToPokerGame;
import com.brazen.card.business.PokerCardGameHandRules;
import com.brazen.card.data.model.CardDeck;
import com.brazen.card.data.model.CardSuites;
import com.brazen.card.data.model.CardType;
import com.brazen.card.data.model.PokerGameData;
import com.brazen.card.data.model.PokerHands;

public class PokerCardMain {

	public static void main(String[] args) {

		//Assumption of 2 players , texas Holdem Poker.
		
		//Distribute cards to 2 players
		HashSet<CardDeck> cardUsedDeck = new HashSet<CardDeck>();
		
		CardDeck card1 = new CardDeck(CardType.FOUR, CardSuites.SPADES);
		CardDeck card2 = new CardDeck(CardType.FIVE, CardSuites.HEARTS);
		
		AddPlayersToPokerGame.instance.addPlayerToGame(card1, card2);
		
		List<CardDeck> Player1 = new ArrayList<CardDeck>();
		if(cardUsedDeck.add(card1) && cardUsedDeck.add(card2)) {
			Player1.addAll(Arrays.asList(card1,card2));
		}else {
			System.out.println("Invalid deck Player Card ");
		}
				
		CardDeck card3 = new CardDeck(CardType.ACE, CardSuites.CLUBS);
		CardDeck card4 = new CardDeck(CardType.FIVE, CardSuites.CLUBS);
		
		AddPlayersToPokerGame.instance.addPlayerToGame(card1, card2);
		
		List<CardDeck> Player2 = new ArrayList<CardDeck>();
		if(cardUsedDeck.add(card3) && cardUsedDeck.add(card4)) {
			Player2.addAll(Arrays.asList(card3,card4));
		}else {
			System.out.println("Invalid deck Player Card");
		}	
		
		CardDeck communityCards1 = new CardDeck(CardType.SIX, CardSuites.DIAMONDS);
		CardDeck communityCards2 = new CardDeck(CardType.FIVE, CardSuites.DIAMONDS);
		CardDeck communityCards3 = new CardDeck(CardType.JACK, CardSuites.CLUBS);
		
		List<CardDeck> communityCards = new ArrayList<CardDeck>();
		if(cardUsedDeck.add(communityCards1) && cardUsedDeck.add(communityCards3) && cardUsedDeck.add(communityCards2)) {		
			communityCards.addAll(Arrays.asList(communityCards1,communityCards2,communityCards3));
			Player1.addAll(communityCards);	
			Player2.addAll(communityCards);	
	
		} else {
			System.out.println("Invalid deck communityCards");
		}
		//Match first flop and Fold.
		CardDeck turnCard = new CardDeck(CardType.EIGHT, CardSuites.CLUBS);
		CardDeck riverCard = new CardDeck(CardType.SEVEN, CardSuites.CLUBS);
		
		if(cardUsedDeck.add(turnCard) && cardUsedDeck.add(riverCard)) {		
			communityCards.addAll(Arrays.asList(turnCard,riverCard));
			
			Player1.addAll(Arrays.asList(turnCard,riverCard));	
			Player2.addAll(Arrays.asList(turnCard,riverCard));	

		} else {
			System.out.println("Invalid deck communityCards two");
		}
	
		PokerCardGameHandRules pf = new PokerCardGameHandRules();
		PokerHands ph = PokerHands.HIGH_CARD;
		int winningPlayer = pf.getWinnerOfPokerGame(Player1, Player2);
		PokerHands handType = PokerHands.valueOf(ph.getPokerHand());
		
		//persist game data
		PokerGameData pg = new PokerGameData();
		
		System.out.println("Player " + winningPlayer + " Won game with : " +
				handType.name());	
		
		
		//Arrange Flop
		//1. Bet
		//2. open Flop - 3 cards
		//3. Bet
		//4. open flop - 2 cards
		//5. decide Winner.
		
	}
	

}
