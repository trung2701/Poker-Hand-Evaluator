/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgbreakfromaithepokergame;

import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Galaxy
 */
public class Pair implements Serializable{
    private final int aNumber;
    private ArrayList<Card> hand = new ArrayList<>();
    
    public Pair(int aNumber, ArrayList<Card> l){
        this.aNumber = aNumber;
        this.hand = l;
    }
    
    protected int getNumber(){
        return aNumber;
    }
    
    protected ArrayList<Card> getHand(){
        return hand;
    }
    
    
}
