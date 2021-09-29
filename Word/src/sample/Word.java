/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class Word {
    private SimpleStringProperty word1;
    private SimpleIntegerProperty count1;

    public Word(String word1,int count1) {
        this.word1 =new SimpleStringProperty(word1);
        this.count1 =new SimpleIntegerProperty(count1);
    }

    Word() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getWord1() {
        return word1.get();
    }

    public void setWord1(SimpleStringProperty word1) {
        this.word1 = word1;
    }

    public Integer getCount1() {
        return count1.get();
    }

    public void setCount1(SimpleIntegerProperty count1) {
        this.count1 = count1;
    }
    
    
}
