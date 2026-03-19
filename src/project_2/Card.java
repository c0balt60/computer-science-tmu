package project_2;

public class Card {
    public static enum Suit {
        SPADES("♠", false),
        HEARTS("♥", true),
        DIAMONDS("♦", true),
        CLUBS("♣", false);

        private final String symbol;
        private final boolean red;

        Suit(String symbol, boolean red) {
            this.symbol = symbol;
            this.red = red;
        }

        public String getSymbol() {
            return symbol;
        }

        public boolean isRed() {
            return red;
        }
    }

    public enum Rank {
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 10),
        ACE("A", 11);

        private final String label;
        private final int value;

        Rank(String label, int value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return this.label;
        }

        public int getValue() {
            return this.value;
        }
    }

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        return rank.getValue();
    }

    public boolean isFaceCard() {
        return switch (rank) {
            case Rank.JACK, Rank.QUEEN, Rank.KING -> true;
            default -> false;
        };
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public String getSuitSymbol() {
        return suit.getSymbol();
    }

    public boolean isRed() {
        return suit.isRed();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.getLabel() + suit.getSymbol();
    }

}
