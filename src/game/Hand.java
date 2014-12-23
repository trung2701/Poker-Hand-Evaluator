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
public class Hand {
    
    public Pair bestHand(ArrayList<Card> l){  
        ArrayList<Card> bestHand = null;
        Collections.sort(l);
        int best = 0;
        if (!onePair(l).isEmpty()){
            bestHand = onePair(l);
        }
        if (!twoPair(l).isEmpty()){
            best = 1;
            bestHand = twoPair(l);
        }
        if (!threeOfKind(l).isEmpty()){
            best = 2;
            bestHand = threeOfKind(l);
        }
        if (!straight(l).isEmpty()){
            best = 3;
            bestHand = straight(l);
        }
        if (!flush(l).isEmpty()){
            best = 4;
            bestHand = flush(l);
        }
        if (!fullHouse(l).isEmpty()){
            best = 5;
            bestHand = fullHouse(l);
        }
        if (!fourOfKind(l).isEmpty()){
            best = 6;
            bestHand = fourOfKind(l);
        }
        if (!straightFlush(l).isEmpty()){
            best = 7;
            bestHand = straightFlush(l);
        }
        if (!royalFlush(l).isEmpty()){
            best = 8;
            bestHand = royalFlush(l);
        }
        
        return (new Pair(best, bestHand));
    }
    
    private ArrayList<Card> removeDuplicate(ArrayList<Card> l){    // remove duplicated card based on attributes and return 
        int end = l.size();                                               // the arraylist containning all the cards                  
        Set<Card> set = new HashSet<>();

        for(int i = 0; i < end; i++){
            set.add(l.get(i));
        }
        ArrayList<Card> dummy = new ArrayList<>();
        for (Card nextCard : set) {
            dummy.add(nextCard);
        }
        
        return dummy;
    }
    
    private ArrayList getCardValue(ArrayList<Card> l){
        ArrayList<Integer> allValue = new ArrayList<>();
        for (int i = 0; i < l.size(); i ++){
            allValue.add(l.get(i).getValue());
        }
        return allValue;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> royalFlush(ArrayList<Card> l){
        Integer[] royal_flu = {10, 11, 12, 13, 1};
        ArrayList<Integer> temp_cardValue = getCardValue(l);
        ArrayList<Card> temp_royalFlush = new ArrayList<>();
        ArrayList<Card> royalFlush;
        
        if (temp_cardValue.containsAll(Arrays.asList(royal_flu))){
            for (int i = 0; i < l.size(); i++){
                Card temp_card = l.get(i);
                for (int j = 0; j < royal_flu.length; j++){
                    if (temp_card.getValue() == royal_flu[j]){
                        temp_royalFlush.add(temp_card);
                    }
                }
            }
        }
        
        royalFlush = flush(temp_royalFlush);
        return royalFlush;      
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> straightFlush(ArrayList<Card> l){
        ArrayList<Card> straightFlush;
        
        ArrayList<Card> temp_flush = new ArrayList<>();
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_kind = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getSuit() == l.get(j).getSuit()){
                    occurrence_kind++;
                }
            }
            if (occurrence_kind >= 5){
                temp_flush.add(temp);
            }
        }
        
        straightFlush = straight(temp_flush);
        return straightFlush;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> fourOfKind(ArrayList<Card> l){
        ArrayList<Card> fourOfKind = new ArrayList<>();
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_kind = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getValue() == l.get(j).getValue()){
                    occurrence_kind++;
                }
            }
            if (occurrence_kind == 4){
                fourOfKind.add(temp);
            }
        }
        
        return fourOfKind;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> fullHouse(ArrayList<Card> l){
        ArrayList<Card> fullHouse = new ArrayList<>();
        
        if ((!threeOfKind(l).isEmpty()) && (!onePair(l).isEmpty())){
            fullHouse.addAll(threeOfKind(l));
            fullHouse.addAll(onePair(l));
        }
        
        return fullHouse;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> flush(ArrayList<Card> l){
        ArrayList<Card> flush = new ArrayList<>();
        ArrayList<Card> temp_flush = new ArrayList<>();
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_kind = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getSuit() == l.get(j).getSuit()){
                    occurrence_kind++;
                }
            }
            if (occurrence_kind >= 5){
                temp_flush.add(temp);
            }
        }
        
        if (temp_flush.size() > 5){
            for (int k = (temp_flush.size() - 5); k < temp_flush.size(); k++){
                flush.add(temp_flush.get(k));
            }
        }else{
            flush.addAll(temp_flush);
        }
        
        return flush;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> straight(ArrayList<Card> l){
        ArrayList<Card> straight = new ArrayList<>();
        ArrayList<Card> card_value = this.removeDuplicate(l);
        ArrayList<Integer> temp_value = this.getCardValue(card_value);
        
        ArrayList<Integer[]> possible_straight = new ArrayList<>();
        possible_straight.add(new Integer[]{1, 2, 3, 4, 5});
        possible_straight.add(new Integer[]{2, 3, 4, 5, 6}); 
        possible_straight.add(new Integer[]{3, 4, 5, 6, 7});
        possible_straight.add(new Integer[]{4, 5, 6, 7, 8});
        possible_straight.add(new Integer[]{5, 6, 7, 8, 9});
        possible_straight.add(new Integer[]{6, 7, 8, 9, 10});
        possible_straight.add(new Integer[]{7, 8, 9, 10, 11});
        possible_straight.add(new Integer[]{8, 9, 10, 11, 12});
        possible_straight.add(new Integer[]{9, 10, 11, 12, 13});
        possible_straight.add(new Integer[]{10, 11, 12, 13, 1});
        
        HashMap<Integer, Integer[]> check_list = new HashMap<>();
        int controller = 0;
        
        for (int i = 0; i < possible_straight.size(); i++){
            Integer[] dummy = possible_straight.get(i);
            if (temp_value.containsAll(Arrays.asList(dummy))){
                check_list.put(controller++, dummy);
            }
        }
        
        if (!check_list.isEmpty()){
            for (int k = 0; k < check_list.get((controller - 1)).length; k++){
                for (int m = 0; m < card_value.size(); m++){
                    Card temp_card = card_value.get(m);
                    if (temp_card.getValue() == check_list.get(controller - 1)[k]){
                      straight.add(temp_card);
                    }
                }
            }
        }
        
        return straight;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> threeOfKind(ArrayList<Card> l){
        ArrayList<Card> threeOfKind = new ArrayList<>();
        ArrayList<Card> temp_three_of_kind = new ArrayList<>();
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_kind = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getValue() == l.get(j).getValue()){
                    occurrence_kind++;
                }
            }
            if (occurrence_kind == 3){
                temp_three_of_kind.add(temp);
            }
        }
        if (temp_three_of_kind.size() > 3){
            for (int k = (temp_three_of_kind.size() - 3); k < temp_three_of_kind.size(); k++){
                threeOfKind.add(temp_three_of_kind.get(k));
            }
        }else{
            threeOfKind.addAll(temp_three_of_kind);
        }
        
        return threeOfKind;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> twoPair(ArrayList<Card> l){  // setect two pairs in list of 7 cards
        ArrayList<Card> twoPairs = new ArrayList<>();
        
        ArrayList<Card> temp_two_pair = new ArrayList<>();
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_pair = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getValue() == l.get(j).getValue()){
                    occurrence_pair++;
                }
            }
            if (occurrence_pair == 2){
                temp_two_pair.add(temp);
            }
        }
        
        if (temp_two_pair.size() > 4){
            for (int k = (temp_two_pair.size() - 4); k < temp_two_pair.size(); k++){
                twoPairs.add(temp_two_pair.get(k));
            }
        }else{
            twoPairs.addAll(temp_two_pair);
        }
        
        return twoPairs;
    }
    
    // CORRECT, CHECKED, FINISH
    public ArrayList<Card> onePair(ArrayList<Card> l){  // detect one pair in list of 7 cards
        ArrayList<Card> onePair = new ArrayList<>();
        ArrayList<Card> temp_one_pair = new ArrayList<>();
        
        for (int i = 0; i < l.size(); i++){
            int occurrence_pair = 0;
            Card temp = l.get(i);
            for (int j = 0; j < l.size(); j++){
                if (temp.getValue() == l.get(j).getValue()){
                    occurrence_pair++;
                }
            }
            if (occurrence_pair == 2){
                temp_one_pair.add(temp);
            }
        }
        
        if (temp_one_pair.size() > 2){
            for (int k = (temp_one_pair.size() - 2); k < temp_one_pair.size(); k++){
                onePair.add(temp_one_pair.get(k));
            }
        }else{
            onePair.addAll(temp_one_pair);
        }
        
        return onePair;
    }        

    public static void main(String [] args){
        Hand h = new Hand();
        
        ArrayList<Card> a = new ArrayList<>();
        a.add(new Card(8, 1));
        a.add(new Card(7, 0));
        a.add(new Card(7, 2));
        a.add(new Card(9, 0));
        a.add(new Card(6, 3));
        a.add(new Card(10, 0));
        a.add(new Card(13, 1));
        
        for (int j = 0; j < a.size(); j++){
            System.out.println(a.get(j).toString());
        }
        System.out.println();
        
        Pair aPair = h.bestHand(a);
        switch(aPair.getNumber()){
            case 0:
                System.out.println("One Pair");
                break;
            case 1:
                System.out.println("Two Pair");
                break;
            case 2:
                System.out.println("Three Of Kind");
                break;
            case 3:
                System.out.println("Straight");
                break;
            case 4:
                System.out.println("Flush");
                break;
            case 5:
                System.out.println("Full House");
                break;
            case 6:
                System.out.println("Four Of Kind");
                break;
            case 7:
                System.out.println("Straight Flush");
                break;
            case 8:
                System.out.println("Royal Flush");
                break;
            default:
                System.out.println("No hand found");
                break;
        }
        
        
        for (int i = 0; i < aPair.getHand().size(); i++){
            System.out.println(aPair.getHand().get(i));
        }
    }
}
