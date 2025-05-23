package obj;

public class Player {

    // The only two pieces of information about the player; what their first card is and what their second card is
    private Card card1,card2,card3;

    // The constructor; null values aren't the best, but will never be referenced
    public Player(){
        this.card1 = null;
        this.card2 = null;
        this.card3 = null;
    }

    // The getters
    public Card getCard1(){return this.card1;}
    public Card getCard2(){return this.card2;}
    public Card getCard3(){return this.card3;}

    // The setters
    public void setCard1(Card card){this.card1 = card;}
    public void setCard2(Card card){this.card2 = card;}
    public void setCard3(Card card){this.card3 = card;}
}
