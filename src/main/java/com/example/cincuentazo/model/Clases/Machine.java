package com.example.cincuentazo.model.Clases;

import com.example.cincuentazo.model.AbstractsClases.AbstractPlayer;
import com.example.cincuentazo.model.Interfaces.IMachine;

public class Machine extends AbstractPlayer implements IMachine {
    @Override
    public String think(int currentSum) {
        String move = agressiveMove(currentSum);
        return (move == null) ? defensiveMove(currentSum) : move;
    }
    private int getCardValue(String card, int sum) {
        int value = Game.getInstance().check(card.charAt(0));

        //En caso de que la carta sea A
        if (value == 10) value = (value + sum <= 50) ? 10 : 1;

        //Para el caso de que sea un A
        if (value == 11) value = 10;

        return value;
    }


    private String defensiveMove(int sum)
    {
        int bestResult = 50;
        String bestCard = null;
        for(String card : getHand()) {
            int value = getCardValue(card, sum);
            int newSum = value + sum;

            //Verifica cual es el mejor resultado
            if (newSum < 50 && newSum < bestResult) {
                bestResult = newSum;
                bestCard = card;
            } else if (newSum == 50) { //En caso de que solo una no supere el limite
                bestCard = card;
            }
        }
        return bestCard;
    }

    private String agressiveMove(int sum)
    {
        int bestResult = 0;
        String bestCard = null;
        for(String card : getHand()) {
            int value = getCardValue(card, sum);
            int newSum = value + sum;

            //Verifica cual es el mejor resultado
            if (newSum <= 50 && newSum > bestResult) {
                bestResult = newSum;
                bestCard = card;
            }
        }
        return bestCard;
    }
}
