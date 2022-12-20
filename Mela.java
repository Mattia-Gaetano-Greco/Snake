import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class Mela extends Thread
{
    private int posX;
    private int posY;
    private JLabel img;
    
    public Mela()
    {
        // creazione immagine mela
        img = new JLabel(new ImageIcon("immagini/mela.png"));
        Gioco.finestra.add(img);
        img.setSize(50,50);
        riposizionaMela();
    }
    
    private void riposizionaMela()
    {
        do {
            posX = ThreadLocalRandom.current().nextInt(1, Gioco.matriceGriglia.length - 1);
            posY = ThreadLocalRandom.current().nextInt(1, Gioco.matriceGriglia.length - 1);
            img.setLocation(posX*50,posY*50);
        } while(Gioco.matriceGriglia[posY][posX] != 0);
        Gioco.matriceGriglia[posY][posX] = -1;
    }
    
    public void run()
    {
        while(Gioco.isGiocando == true)
        {
            if (Gioco.matriceGriglia[posY][posX] != -1) {
                riposizionaMela();
            }
            try {
                sleep(Gioco.timerUpdate/10);
            } catch (Exception e) {}
        }
    }
}
