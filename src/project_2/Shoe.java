package project_2;

import java.util.ArrayList;
import java.util.Collections;

public class Shoe {
    private final int decks;
    private final ArrayList<Card> cards = new ArrayList<>();
    private final int totalCards;

    public Shoe(int numDecks) {
        this.decks = numDecks;
        this.totalCards = numDecks * 32;
        build();
        shuffle();
    }

    private void build() {
        cards.clear();
        for (int i = 0; i < decks; i++)
            for (Card.Suit suit : Card.Suit.values())
                for (Card.Rank rank : Card.Rank.values())
                    cards.add(new Card(rank, suit));
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public void reshuffle() {
        build();
        shuffle();
    }

    public Card dealNext() {
        if (needsReshuffle())
            shuffle();
        return cards.remove(cards.size() - 1);
    }

    public AnimatedCard dealAnimated() {
        return new AnimatedCard(dealNext());
    }

    public boolean needsReshuffle() {
        return cards.size() < totalCards * .25;
    }
}
