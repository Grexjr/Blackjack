package obj;

public class Round {

    // The two pieces of information about each round; the deck and the players - might not want to create
    // players every single round and rather have them persist with the game state?
    protected Deck roundDeck;
    protected Player player1,player2;
    protected boolean hitStatus;

    // The constructor for the round
    public Round(){
        this.roundDeck = new Deck();
        this.player1 = new Player();
        this.player2 = new Player();
        this.hitStatus = false;
    }

    // The getters
    public Deck getRoundDeck(){return this.roundDeck;}
    public Player getPlayer1(){return this.player1;}
    public Player getPlayer2(){return this.player2;}
    public boolean getHitStatus(){return this.hitStatus;}

    // The setters
    public void setHitStatus(boolean status){this.hitStatus = status;}

    // Method to deal a card to a player, then remove that card from the deck. Maybe this should go elsewhere
    public void dealCard(Player player, int cardNumber){
        if(cardNumber == 1){
            player.setCard1(this.roundDeck.getContents().get(0));
            this.roundDeck.getContents().remove(0);
            this.roundDeck.updateCurrentSize();
        } else if(cardNumber == 2){
            player.setCard2(this.roundDeck.getContents().get(0));
            this.roundDeck.getContents().remove(0);
            this.roundDeck.updateCurrentSize();
        } else if(cardNumber == 3){
            player.setCard3(this.roundDeck.getContents().get(0));
            this.roundDeck.getContents().remove(0);
            this.roundDeck.updateCurrentSize();
        }
    }
}
