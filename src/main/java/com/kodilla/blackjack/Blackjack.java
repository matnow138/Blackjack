package com.kodilla.blackjack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class Blackjack extends Application {

    private final Deck deck = new Deck(1);
    private final Hand hand = new Hand();
    private final Hand dealer = new Hand();
    private boolean busted;
    private boolean playerTurn;
    private FlowPane cards = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane dealerCards = new FlowPane(Orientation.HORIZONTAL);
    private Label totalLabel = new Label();
    private Label totalLabelDealer = new Label();

    private Label dealerLbl = new Label("Karty krupiera");
    private Label playerLbl = new Label("Twoje Karty");

    private Label status = new Label();

    private Image imageback = new Image("file:src/main/resources/table.png");

    public void drawCard(Hand hand, FlowPane pane, Label l){
        try{
            Card card = deck.dealCard();
            ImageView img = new ImageView(card.getCardImage());
            pane.getChildren().add(img);
            hand.addCard(card);

            int handTotal = hand.evaluateHand();

            StringBuilder total = new StringBuilder();
            if (hand.getSoft()>0){
                total.append(handTotal -10).append("/");

            }
            total.append(handTotal);
            l.setText(total.toString());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void newDeck(){
        deck.restoreDeck();
        deck.shuffle();
        System.out.println("Talia przetasowana");
    }

    public void newHand(){
        if(deck.getNumberOfCardsRemaining() <= deck.getSizeOfDeck()*0.2){
            newDeck();
        }
        hand.discardHand();
        dealer.discardHand();
        cards.getChildren().removeAll(cards.getChildren());
        dealerCards.getChildren().removeAll(dealer.getChildren());
        totalLabel.setText("");
        totalLabelDealer.setText("");
        busted=false;
        playerTurn = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100,100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(11.5, 12.5,13.5,14.5));
        grid.setHgap(5.5);
        grid.setVgap(5.5);
        grid.setBackground(background);

        ImageView img = new ImageView(card);
        cards.getChildren().add(img);
        grid.add(cards,1,5,3,1);
        Scene scene = new Scene(grid, 1600, 900, Color.BLACK);
        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(scene);
        primaryStage.show();




    }
}