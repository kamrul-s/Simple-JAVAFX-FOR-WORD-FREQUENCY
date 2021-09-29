/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import static javax.management.Query.value;


/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    String s,sh;
    int nemword=0;
    ObservableList<Word>data = null;
    @FXML private TableView<Word> tableView;
    @FXML private TableColumn<Word, String> firstColumn;
    @FXML private TableColumn<Word, Integer> lastColumn;
    @FXML
    private Label label;
    @FXML
    private ListView listvew;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException {
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT Files", "*.txt")); 
        File f=fc.showOpenDialog(null);
        if(f != null){
            s=null;
            Scanner sc;
            sc = new Scanner(f);
            while(sc.hasNext()){
                if(s!=null) s=s+sc.next();
                else s=sc.next();
                s=s+' ';
            }
            //System.out.println(s);
        }
    }
    @FXML
    private void handleButton2Action(ActionEvent event){
        data=null;
        
        sh=s;
        int i;
        nemword=0;
        for(i=0;i<sh.length();i++)
        {
            if((sh.charAt(i)=='.')||(sh.charAt(i)==' ')||(sh.charAt(i)==',')) {
                nemword++;
            }
            //System.out.println(nemword);
            if(sh.charAt(i)=='.'||sh.charAt(i)==',') {
                String temp;
                temp=sh.substring(0,i)+' '+sh.substring(i+1);
                sh=temp;
            }
            /*if(sh.charAt(i)==' '&&sh.charAt(i-1)==' ') {
                String temp;
                temp=sh.substring(0,i)+sh.substring(i+1);
                sh=temp;
            }*/
            sh=sh.toLowerCase();
        }
        //System.out.println(sh);
        String[] keys = sh.split(" ");
        String[] uniqueKeys;
        int count = 0;
        uniqueKeys = getUniqueKeys(keys);
        data = FXCollections.observableArrayList();
        for(String key: uniqueKeys)
        {
            if(null == key)
            {
                break;
            }           
            for(String p : keys)
            {
                if(key.equals(p))
                {
                    count++;
                }               
            }

            data.add(new Word(key,count));
            //System.out.println("Count of ["+key+"] is : "+count);
            count=0;
        }
        /*data = FXCollections.observableArrayList(
            new Word("vfnjfdn",8)
            );*/
        tableView.setItems(data);
        //System.out.println(data);
    }
    
    private static String[] getUniqueKeys(String[] keys)
    {
        String[] uniqueKeys = new String[keys.length];
        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;
        for(int i=1; i<keys.length ; i++)
        {
            for(int j=0; j<=uniqueKeyIndex; j++)
            {
                if(keys[i].equals(uniqueKeys[j]))
                {
                    keyAlreadyExists = true;
                }
            }           
            if(!keyAlreadyExists)
            {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;               
            }
            keyAlreadyExists = false;
        }       
        return uniqueKeys;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        firstColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("Word1"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<Word, Integer>("count1"));
        
        tableView.setItems(data);
    }    
    
}
