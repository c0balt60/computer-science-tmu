package quizes;

public class Card implements Comparable<Card> {
    public static void main() {
        Card twoClubs = new Card(2, 'C');
        Card twoClubs2 = new Card(2, 'C');
        Card aceSpades = new Card(14, 'S');

        System.out.println("Compare Two of Clubs to Ace of Spades: " + twoClubs.compareTo(aceSpades));
        System.out.println("Compare Two of Clubs to Two of Clubs: " + twoClubs.compareTo(twoClubs2));
        System.out.println(twoClubs);
        System.out.println(aceSpades);
        System.out.println("Two of Clubs equals to Ace of Spades: " + twoClubs.equals(aceSpades));
        System.out.println("Two Clubs equals Two of Clubs: " + twoClubs.equals(twoClubs2));
    }

    final int rank;
    final char suit;

    final String nameFilter;
    final String suitFilter;
    final int suitRank;

    public Card(int rank, char suit) {
        this.rank = rank;
        this.suit = suit;

        // Calculate name
        this.nameFilter = switch (rank) {
            case 2, 3, 4, 5, 6, 7, 8, 9, 10 -> Integer.toString(rank);
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> "None";
        };

        // Calculate suit
        this.suitFilter = switch (suit) {
            case 'C' -> "Clubs";
            case 'D' -> "Diamonds";
            case 'H' -> "Hearts";
            case 'S' -> "Spades";
            default -> "Invalid Suit";
        };

        // Calculate suit rank
        this.suitRank = switch (suit) {
            case 'C' -> 1;
            case 'D' -> 3;
            case 'H' -> 4;
            case 'S' -> 2;
            default -> -1;
        };
    }

    @Override
    public int compareTo(Card other) {
        if (this.rank > other.rank) {
            return 1;
        } else if (this.rank < other.rank) {
            return -1;
        }

        if (this.suitRank > other.suitRank) {
            return 1;
        } else if (this.suitRank < other.suitRank) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card))
            return false;
        Card other = (Card) o;
        return (other.rank == this.rank) && (other.suit == this.suit);
    }

    @Override
    public String toString() {
        return String.format(
                "%s of %s",
                nameFilter,
                suitFilter);
    }
}
