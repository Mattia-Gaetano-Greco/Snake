import javax.swing.*;

public class Serpente extends Thread
{
    protected int[][] aggiunte = new int[][]{{0,-1}, {1,0}, {0,1}, {-1,0}};
    protected JLabel[] labelPezziCorpo; // coda = posizioni[0], testa = [lunghezza]
    protected int lunghezza;
    protected int[][] posizioni; // coda = posizioni[0], testa = posizioni[lunghezza]
    protected int direzione;
    protected int ultimaDirezione; // ultima direzione verso dove è andato
    protected int codiceSerpente;
    protected int nextX;
    protected int nextY;
    
    public Serpente(int xIniziale, int yIniziale, int direzione, int codiceSerpente)
    {
        posizioni = new int[1000][2];
        posizioni[0] = new int[]{xIniziale, yIniziale};
        this.direzione = direzione;
        ultimaDirezione = direzione;
        this.codiceSerpente = codiceSerpente;
        labelPezziCorpo = new JLabel[1000];
        Gioco.matriceGriglia[yIniziale][xIniziale] = codiceSerpente;
        // creazione immagine testa
        labelPezziCorpo[0] = new JLabel(new ImageIcon());
        Gioco.finestra.add(labelPezziCorpo[0]);
        labelPezziCorpo[0].setSize(50,50);
        posizionaCorpo(xIniziale, yIniziale);
    }
    
    public void run()
    {
		boolean alive = true;
        while (Gioco.isGiocando == true && alive)
        {
            int contenuto = calcolaNextCasella();
            if (contenuto > 0) {
                uccidi();
                alive = false;
            } else if (contenuto < 0) {
                cresci(nextX, nextY);
            } else {
                sposta(nextX, nextY);
            }
            try {
                sleep(Gioco.timerUpdate);
            } catch (Exception e) {}
        }
        
    }
    
    protected void sposta(int nextPosX, int nextPosY)
    {
        Gioco.matriceGriglia[posizioni[0][1]][posizioni[0][0]] = 0;
        for (int i = 1; i <= lunghezza; i++) {
            posizioni[i-1] = posizioni[i];
        }
        posizioni[lunghezza] = new int[] {nextPosX, nextPosY};
        Gioco.matriceGriglia[posizioni[lunghezza][1]][posizioni[lunghezza][0]] = codiceSerpente;
        ultimaDirezione = direzione;
        posizionaCorpo(nextPosX, nextPosY);
    }
    
    protected void uccidi()
    {
        for (int i = 0; i < lunghezza+1; i++)
        {
            Gioco.matriceGriglia[posizioni[i][1]][posizioni[i][0]] = 0;
            labelPezziCorpo[i].setLocation(3000,3000);
            Gioco.finestra.remove(labelPezziCorpo[i]);
        }
    }
    
    protected void cresci(int nextX, int nextY)
    {
        lunghezza++;
        posizioni[lunghezza] = new int[]{nextX, nextY};
        Gioco.matriceGriglia[nextY][nextX] = codiceSerpente;
        // creazione corpo
        for (int i = lunghezza+1; i > 0; i--) {
            labelPezziCorpo[i] = labelPezziCorpo[i-1];
        }
        //labelPezziCorpo[lunghezza] = labelPezziCorpo[lunghezza-1];
        labelPezziCorpo[0] = new JLabel(new ImageIcon("immagini/serpenti/serpente"+codiceSerpente+"/corpo.png"));
        Gioco.finestra.add(labelPezziCorpo[0]);
        labelPezziCorpo[0].setSize(50,50);
        labelPezziCorpo[0].setLocation(posizioni[lunghezza-1][0]*50, posizioni[lunghezza-1][1]*50);
        posizionaCorpo(nextX, nextY);
    }
    
    protected int calcolaNextCasella()
    {
        nextX = posizioni[lunghezza][0] + aggiunte[direzione][0];
        nextY = posizioni[lunghezza][1] + aggiunte[direzione][1];
        return Gioco.matriceGriglia[nextY][nextX];
    }
    
    protected void posizionaCorpo(int x,int y)
    {
        if (lunghezza > 0) {
            labelPezziCorpo[0].setLocation(labelPezziCorpo[lunghezza].getLocation());
            JLabel l = labelPezziCorpo[0];
            for (int i = 1; i < lunghezza; i++) {
                labelPezziCorpo[i-1] = labelPezziCorpo[i];
            }
            labelPezziCorpo[lunghezza-1] = l;
        }
        // spostamento testa
        labelPezziCorpo[lunghezza].setLocation(x*50, y*50);
        labelPezziCorpo[lunghezza].setIcon(new ImageIcon("immagini/serpenti/serpente"+codiceSerpente+"/testa"+direzione+".png"));
    }
}
