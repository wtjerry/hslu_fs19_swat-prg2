/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.subPanel;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author dane
 */
public abstract class CardsClass extends JPanel{
    public CardsClass(JPanel cards){
        super();
        this.cards = cards;
    }
    
    abstract void initComponent();
    
    protected void switchToCard(String cardName){
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardName);
        
    }
    private final JPanel cards;
}
