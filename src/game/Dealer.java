/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Shine
 */
public class Dealer implements DealerIF{
    private ArrayList<PlayerIF> registered_player = new ArrayList<>();
    private Pack aPack;
    private ArrayList<Card> card_to_player = new ArrayList<>();
    private ArrayList<Card> card_on_table = new ArrayList<>();
    
    public Dealer() throws RemoteException{
        aPack = new Pack();
    }

    @Override
    public void register(PlayerIF player) throws RemoteException {
        registered_player.add(player);
    }
    
    public void shuffle(){
        aPack.shuffle();
    }
    
    public void deal(PlayerIF player) throws RemoteException{
        for (int i = 0; i < 2; i++){
            Card temp = aPack.getCard((int) (Math.random()*(aPack.getSize())));
            player.receiveCard(temp);
            card_to_player.add(temp);
            aPack.removeCard(temp);
        }
        
        for (int k = 0; k < card_to_player.size(); k++){
            System.out.println(card_to_player.get(k).toString());
        }
    }
    
    public void pickCardForTable(){
        for (int i = 0; i < 5; i++){
            Card dummy = aPack.getCard((int) (Math.random()*(aPack.getSize())));
            card_on_table.add(dummy);
            aPack.removeCard(dummy);
        }
        System.out.println(aPack.getSize());
        for (int j = 0; j < card_on_table.size(); j++){
            System.out.println(card_on_table.get(j).toString());
        }
    }
    
    public ArrayList<Card> merge(){
        ArrayList<Card> seven = new ArrayList<>();
        seven.addAll(card_to_player);
        seven.addAll(card_on_table);
        Collections.sort(seven);
        return seven;
    }
}
