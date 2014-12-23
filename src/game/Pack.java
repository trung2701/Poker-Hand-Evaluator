/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.util.*;

/**
 *
 * @author Shine
 */
public class Pack{
    ArrayList<Card> pack;
    
    public Pack(){
        pack = new ArrayList<>();
        for (int i = 1; i < 14; i++){
            for (int j = 0; j < 4; j++){
                pack.add(new Card(i, j));
            }
        }
    }
    
    public void display(){
        for (Card card: pack){
            System.out.println(card.toString());
        }
    }
    
    public int getSize(){
        return pack.size();
    }
    
    public ArrayList<Card> getPack(){
        return pack;
    }
    
    public void shuffle(){
        for (int i = 0; i < 100; i++){            
            int card_1 = (int) (Math.random()*52);
            int card_2 = (int) (Math.random()*52);
            
            Card dummy = pack.get(card_1);            

            pack.set(card_1, pack.get(card_2));
            pack.set(card_2, dummy);
        }
    }

    public Card getCard(int index){
        return pack.get(index);
    }
    
    public void removeCard(Card c){
        pack.remove(c);
    }
}
