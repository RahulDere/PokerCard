package com.brazen.card.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Persistence of games history
public class PokerGameData {

	int gameId;
	int winningPlayer;
	PokerHands winningHand;
	Date dateOfPokerGame;	
    private List<CardDeck> playerCards = 
              new ArrayList<>(0);
    private List<CardDeck> communityCards = 
            new ArrayList<>(0);
	  
	public PokerGameData() {
		
	}

	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getWinningPlayer() {
		return winningPlayer;
	}
	public void setWinningPlayer(int winningPlayer) {
		this.winningPlayer = winningPlayer;
	}
	public PokerHands getWinningHand() {
		return winningHand;
	}
	public void setWinningHand(PokerHands winningHand) {
		this.winningHand = winningHand;
	}	

	public List<CardDeck> getPlayerCards() {
		return playerCards;
	}

	public List<CardDeck> getCommunityCards() {
		return communityCards;
	}

	public void setCommunityCards(List<CardDeck> communityCards) {
		this.communityCards = communityCards;
	}

	public void setPlayerCards(List<CardDeck> playerCards) {
		this.playerCards = playerCards;
	}

}
