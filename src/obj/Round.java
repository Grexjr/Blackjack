package obj;

public class Round {

    // The two pieces of information about each round; the deck and the players - might not want to create
    // players every single round and rather have them persist with the game state?
    protected Deck roundDeck;
    protected Player player1,player2;

    // The constructor for the round
    public Round(){
        this.roundDeck = new Deck();
        this.player1 = new Player();
        this.player2 = new Player();
    }

    // The getters
    public Deck getRoundDeck(){return this.roundDeck;}
    public Player getPlayer1(){return this.player1;}
    public Player getPlayer2(){return this.player2;}

    // The setters

    // Method to deal a card to a player, then remove that card from the deck. Maybe this should go elsewhere
    public void dealCard(Player player){
        // Adds the top card from the deck to the player's hand
        player.getPlayerHand().add(this.roundDeck.getContents().get(0));

        // removes the dealt card from the deck and updates size so new card is in position 0
        this.roundDeck.getContents().remove(0);
        this.roundDeck.updateCurrentSize();
        }
    }
