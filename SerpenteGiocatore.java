import java.awt.event.*;
import javax.swing.*;

public class SerpenteGiocatore extends Serpente implements KeyListener
{   
    public SerpenteGiocatore(int posX, int posY, int direzione, int codiceSerpente){
        super(posX, posY, direzione, codiceSerpente);
    }
    
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            direzione = 0;
        } else if (code == KeyEvent.VK_RIGHT) {
            direzione = 1;
        } else if (code == KeyEvent.VK_DOWN) {
            direzione = 2;
        } else if (code == KeyEvent.VK_LEFT) {
            direzione = 3;
        }
        if((direzione+2)%4 == ultimaDirezione) {
            direzione = ultimaDirezione;
        }
    }
    
    public void keyReleased(KeyEvent e) {};
    
    public void keyTyped(KeyEvent e) {};
}
