package com.example.cincuentazo.model.Clases;

import com.example.cincuentazo.model.AbstractsClases.AbstractPlayer;
import com.example.cincuentazo.model.Interfaces.IMachine;

/**
 * Artificial intelligence player implementation.
 *
 * The machine evaluates its hand and selects a move
 * according to offensive and defensive strategies.
 * @author Yoskar Alomia Alvarado
 * @version 1.0
 */
public class Machine extends AbstractPlayer implements IMachine {

    /**
     * Chooses the best possible move for the current turn.
     *
     * @param currentSum current game sum
     * @return selected card
     */
    @Override
    public String think(int currentSum) {
        String move = agressiveMove(currentSum);
        return (move == null) ? defensiveMove(currentSum) : move;
    }
    /**
     * Calculates the effective value of a card.
     *
     * @param card card to evaluate
     * @param sum current accumulated sum
     * @return card value
     */
    private int getCardValue(String card, int sum) {
        int value = Game.getInstance().check(card.charAt(0));

        //En caso de que la carta sea A
        if (value == 10) value = (value + sum <= 50) ? 10 : 1;

        //Para el caso de que sea un 10
        if (value == 11) value = 10;

        return value;
    }

    /**
     * Attempts to minimize the resulting sum while remaining valid.
     *
     * @param sum current accumulated sum
     * @return selected card or null if no move is possible
     */
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

    /**
     * Attempts to maximize the resulting sum without exceeding fifty.
     *
     * @param sum current accumulated sum
     * @return selected card or null if no move is possible
     */
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
