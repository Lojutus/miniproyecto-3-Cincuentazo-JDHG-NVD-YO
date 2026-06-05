package com.example.cincuentazo.controller;

import com.example.cincuentazo.model.Clases.Game;

public class GameController
{
 String actualCard ;
 String lastCard ;

    Boolean addPLayer(){
     return Game.getInstance().newPlayer();
 }
 Boolean sentCard(String card){
     return Game.getInstance().add(card);
 }

}
