package com.brazen.card.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.brazen.card.data.model.CardDeck;
import com.brazen.card.data.model.CardSuites;
import com.brazen.card.data.model.CardType;

public class AddPlayersToPokerGame implements IAddPlayers {

	HashSet<CardDeck> cardUsedDeck = new HashSet<CardDeck>();
	HashMap<Integer, List<com.brazen.card.data.model.CardDeck>> map_PokerPlayerCards = new HashMap<Integer,List<CardDeck>>();
	
	// public instance
	  public static AddPlayersToPokerGame instance;
	  
	  private AddPlayersToPokerGame() 
	  {

	  }
	  
	  static
	  {
	    // static block to initialize instance
	    instance = new AddPlayersToPokerGame();
	  }
	
	@Override
	public void addPlayerToGame(CardDeck hCard1, CardDeck hCard2) {
		
		CardDeck card1 = new CardDeck(CardType.FOUR, CardSuites.SPADES);
		CardDeck card2 = new CardDeck(CardType.FIVE, CardSuites.HEARTS);
		
		List<CardDeck> PlayerCards = new ArrayList<CardDeck>();
		if(cardUsedDeck.add(hCard1) && cardUsedDeck.add(hCard2)) {
			PlayerCards.addAll(Arrays.asList(hCard1,hCard2));
		}else {
			System.out.println("Invalid deck Player Card ");
		}
		
		map_PokerPlayerCards.putIfAbsent(1, PlayerCards);
		
		
		
	}

}
