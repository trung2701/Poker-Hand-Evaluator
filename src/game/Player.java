/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Shine
 */
public class Player implements PlayerIF{
    private ArrayList<Card> twoCard = new ArrayList<>();
    private Hand hand = new Hand();
    
    @Override
    public void receiveCard(Card card) throws RemoteException {
        twoCard.add(card);
    }
    
    public void showCard(){
        for (int i = 0; i < twoCard.size(); i++){
            twoCard.get(i).toString();
        }
    }

    @Override
    public Pair evaluateHand(ArrayList<Card> l) throws RemoteException {
        return hand.bestHand(l);
    }
}
