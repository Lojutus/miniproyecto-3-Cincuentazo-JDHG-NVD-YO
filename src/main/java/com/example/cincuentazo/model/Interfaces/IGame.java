package com.example.cincuentazo.model.Interfaces;

import com.example.cincuentazo.model.AbstractsClases.AbstractPlayer;

public interface IGame {
    Boolean add(String card);
    int check( char num);
    int getSum();
    AbstractPlayer getPlayer(int index);
    int getPlayers();
    Boolean initGame();
    String getLastCard();
    Boolean checkWin();
    Boolean changeHandCard(int playerIndex , int position);
    Boolean changeHandCard(int playerIndex , String card);
    void playerLose(int playerIndex);
}
