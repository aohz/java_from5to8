package java5.solutions;

/**
 * Transform the following code to use Java Enums 
 * 
 *
 */
public class SolutionEx2 {

	public static void main(String[] args) {

		// must run program with -ea flag (java -ea ..) to
		// use assert statements
		Card fourDiamond = new Card(Rank.FOUR, Suit.DIAMONDS);

		assert fourDiamond.getRank().name().equalsIgnoreCase("Four");		
		assert fourDiamond.getSuit().getName().equals("Diamonds");
		
		System.out.println("==== Rank Values ====");
		for (Suit suit: Suit.values()){
			System.out.println(suit.getId() + " - " + suit.getName() + " (" + suit.name() + ")");
		}
	}
}

class Card {
	private final Rank rank;
	private final Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public String toString() {
		return rank + " of " + suit;
	}
}

enum Suit {
	
	DIAMONDS(0, "Diamonds"), CLUBS(1, "Clubs"), HEARTS(2, "Hearts"), SPADES(3, "Spades");
	
	private final int id;
	private final String name;
	
	Suit(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}	
}

enum Rank {
	DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}