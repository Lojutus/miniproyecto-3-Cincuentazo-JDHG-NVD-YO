package com.example.cincuentazo.model.Interfaces;

public interface IPlayer {
    public String[] getHand();
    public Boolean switchCard(String card , int Position);
    int getPlayers();
}
