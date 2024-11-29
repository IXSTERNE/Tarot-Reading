public class CardWithInversion {
    private Tarot card;
    private boolean isInverted;

    public CardWithInversion(Tarot card, boolean isInverted) {
        this.card = card;
        this.isInverted = isInverted;
    }

    public Tarot getCard() {
        return card;
    }

    public boolean isInverted() {
        return isInverted;
    }
}
