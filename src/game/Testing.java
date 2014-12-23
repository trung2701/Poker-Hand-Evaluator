/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgbreakfromaithepokergame;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Shine
 */
public class Testing {
    
    @SuppressWarnings("empty-statement")
    public static void main(String [] args) throws RemoteException{
        ArrayList<Integer> test1 = new ArrayList<>();
        test1.add(1);
        test1.add(2);
        
        ArrayList<Integer> test2 = new ArrayList<>();
        test2.add(3);
        test2.add(4);
        
        ArrayList<Integer> test3 = new ArrayList<>();
        test3.addAll(test1);
        test3.addAll(test2);
        
        for (int i = 0; i < test3.size(); i++){
            System.out.println(test3.get(i));
        }
    }
    
    public boolean royalFlush(ArrayList<Card> l){
        int royal_flush = 0;
        
        for (int i = 0; i < l.size(); i++){
            Card temp = l.get(i);
            if (temp.getValue() == 1 || temp.getValue() == 10 || temp.getValue() == 11 || temp.getValue() == 12 || temp.getValue() == 13){
                royal_flush++;
            }
        }
        
        return ((royal_flush == 5) && this.flush(l)) ? true : false;
    }
    
    public boolean flush(ArrayList<Card> l){
        int flush = 0;
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_kind = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getSuit() == l.get(j).getSuit()){
                    occurrence_kind++;
                }
            }
            if (occurrence_kind >= 5){
                flush += occurrence_kind;
            }
        }
        return flush >= 25 ? true : false;
    }
    
}
