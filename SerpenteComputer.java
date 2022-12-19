import java.util.concurrent.ThreadLocalRandom;


public class SerpenteComputer extends Serpente
{   
    public SerpenteComputer(int posX, int posY, int direzione, int codiceSerpente)
    {
        super(posX, posY, direzione, codiceSerpente);
    }

    public void run()
    {
        int[][] aggiunte = new int[][]{{0,-1}, {1,0}, {0,1}, {-1,0}};
        while (Gioco.isGiocando == true)
        {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 3 + 1); i++) {
                int contenuto = calcolaNextCasella();
                if (contenuto >= 1)
                {
                    for (int k = 0; k < 8; k++)
                    {
                        if (contenuto >= 1) {
                            cambiaDirezioneRandom();
                            contenuto = calcolaNextCasella();
                        } else {
                            k = 8;
                        }
                    }
                }
                if (contenuto > 0) {
                    uccidi();
                    stop();
                } else if (contenuto < 0) {
                    cresci(nextX, nextY);
                } else {
                    sposta(nextX, nextY);
                }
                try {
                    sleep(Gioco.timerUpdate);
                } catch (Exception e) {}
            }
            cambiaDirezioneRandom();
        }
    }
    
    private void cambiaDirezioneRandom()
    {
        direzione = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        if((direzione+2)%4 == ultimaDirezione) {
            cambiaDirezioneRandom();
        }
    }
    
}
