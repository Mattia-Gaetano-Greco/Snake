import javax.swing.*;
import java.awt.*;

public class Gioco
{
    //-1 = mela, 0 = vuoto, 1 = muro, >= 3 = serpente
    private static int posPredSerp[][] = new int[][]{
        {7,4,2}, {12,4,2}, {15,7,3}, {15,12,3}, {12,15,0}, {7,15,0}, {4,12,1}, {4,7,1}};
    public static boolean isGiocando;
    public static int timerUpdate = 2000;
    public static int[][] matriceGriglia = new int[20][20];
    public static JFrame finestra = new JFrame();
    private static Serpente[] serpenti;
    private static Mela[] mele;
    
    public static void init(int timerUpdateValue, int nSerpenti, int nMele)
    {
        Gioco.finestra.setSize(1015,1040);
        Gioco.finestra.setResizable(false);
        Gioco.finestra.show();
        finestra.setContentPane(new JLabel(new ImageIcon("immagini/grass.jpg")));
        // inizializza la matrice
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (j == 0 | j == 19 | i == 0 | i == 19) {
                    matriceGriglia[i][j] = 1;
                    // creazione immagine muro
                    JLabel muro = new JLabel(new ImageIcon("immagini/water.jpg"));
                    finestra.add(muro);
                    muro.setLocation(j*50, i*50);
                    muro.setSize(50,50);
                } else {
                    matriceGriglia[i][j] = 0;
                }
            }
        }
        timerUpdate = timerUpdateValue;
        isGiocando = true;
        serpenti = new Serpente[nSerpenti];
        mele = new Mela[nMele];
        
        //serpenti
        for (int i = 0; i < nSerpenti; i++)
        {
            int iArr = posPredSerp.length/nSerpenti * i;
            if (i == 0) {
                SerpenteGiocatore s = new SerpenteGiocatore(posPredSerp[iArr][0], posPredSerp[iArr][1], posPredSerp[iArr][2], i+2);
                serpenti[i] = s; // LASCIARE COSì!!!
                finestra.addKeyListener(s); // LASCIARE COSì!!!
            } else {
                serpenti[i] = new SerpenteComputer(posPredSerp[iArr][0], posPredSerp[iArr][1], posPredSerp[iArr][2], i+2);
            }
        }
        
        // mele
        for (int i = 0; i < nMele; i++)
        {
            mele[i] = new Mela();
        }
        
        eseguiThread();
    }
    
    private static void eseguiThread()
    {
        for (int i = 0; i < serpenti.length; i++)
        {
            serpenti[i].start();
        }
        
        for (int i = 0; i < mele.length; i++) 
        {
            mele[i].start();
        }
    }
    
}
