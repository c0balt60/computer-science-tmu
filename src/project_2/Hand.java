package project_2;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<>();

    public Hand() {

    }

    public int calculateTotal() {
        int total = 0;
        int aces = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.isAce())
                aces++;
        }

        // Reduces aces from 11 to 1 as needed to avoid bust
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateTotal() == 21;
    }

    public boolean isBust() {
        return calculateTotal() > 21;
    }

    public boolean isSoft() {
        int total = 0;
        int aces = 0;

        for (Card card : cards) {
            total += card.getValue();
            if (card.isAce())
                aces++;
        }

        return aces > 0 && total <= 21;
    }
}
