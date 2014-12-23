/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.io.Serializable;

/**
 *
 * @author Shine
 */
public class Card implements Serializable, Comparable<Card>{
    private String[] name = {"Joker", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] suit = {"Club", "Diamond", "Spade", "Heart"};
    private int card_value;
    private int card_suit;
    
    public Card(int card_name, int card_suit){
        this.card_value = card_name;
        this.card_suit = card_suit;
    }
    
    public int getSuit(){
        return card_suit;
    }
    
    public int getValue(){
        return card_value;
    }
    
    @Override
    public String toString(){
        return name[card_value] + " " + suit[card_suit];
    }

    @Override
    public int compareTo(Card card) {
        int compareValue = ((Card) card).getValue();
        
        return this.getValue() - compareValue;
    }
    /*
     Overriding these 2 methods below (they are from hashSet Class)
    used to remove duplicating object by its distribute, which is 
    card_value in this case
    */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.card_value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Card)){
            return false;
        }
        Card other = (Card) obj;
        return (this.card_value == other.card_value);
    }
}
