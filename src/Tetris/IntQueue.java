package Tetris;

import java.util.concurrent.ThreadLocalRandom;

public class IntQueue{
    private int i;
    private int[] q;

    public IntQueue(){
        i = 0;
        q = new int[7];
        for(int j = 0; j < 7; j++) q[j] = j;

        shuffle();
    }
    private void shuffle(){
        int m = 7, t, k;
        while(m>0){
            k = ThreadLocalRandom.current().nextInt(0, m--);

            t = q[m];
            q[m] = q[k];
            q[k] = t;
        }
    }
    public int next(){
        if(i > 6){
            i = 0;
            shuffle();
        }
        return q[i++];
    }
}
