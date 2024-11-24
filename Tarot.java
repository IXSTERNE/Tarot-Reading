public class Tarot {
    private String name;
    private String arcana;
    private String suit;
    private String uprightMeaning;
    private String reversedMeaning;

    public Tarot(String name, String arcana, String suit, String uprightMeaning, String reversedMeaning){
        this.name = name;
        this.arcana = arcana;
        this.suit = suit;
        this.uprightMeaning = uprightMeaning;
        this.reversedMeaning = reversedMeaning;
    }

    public String getName(){ return name;}
    public String getArcana(){ return arcana;}
    public String getSuit(){ return suit;}
    public String getUprightMeaning(){ return uprightMeaning;}
    public String getReversedMeaning(){ return reversedMeaning;}
}
