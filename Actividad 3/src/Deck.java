import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    class Card {
        private String palo;
        private String color;
        private String valor;

        public Card(String palo, String color, String valor) {
            this.palo = palo;
            this.color = color;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return palo + "," + color + "," + valor;
        }
    }

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] palos = { "Tréboles", "Corazones", "Picas", "Diamantes" };
        String[] colores = { "Rojo", "Negro" };
        String[] valores = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K" };

        for (String palo : palos) {
            String color = (palo.equals("Tréboles") || palo.equals("Picas")) ? "Negro" : "Rojo";
            for (String valor : valores) {
                cards.add(new Card(palo, color, valor));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Se mezcló el Deck.");
    }

    public void head() {
        if (!cards.isEmpty()) {
            Card card = cards.remove(0);
            System.out.println(card.toString());
            System.out.println("Quedan " + cards.size() + " cartas en el deck.");
        } else {
            System.out.println("El deck está vacío.");
        }
    }

    public void pick() {
        if (!cards.isEmpty()) {
            Card card = cards.remove(0);
            System.out.println(card.toString());
            System.out.println("Quedan " + cards.size() + " cartas en el deck.");
        } else {
            System.out.println("El deck está vacío.");
        }
    }

    public void hand() {
        for (int i = 0; i < 5; i++) {
            Card card = cards.remove(0);
            System.out.println(card.toString());
        }

        System.out.println("Quedan " + cards.size() + " cartas en el deck.");
    }
}
