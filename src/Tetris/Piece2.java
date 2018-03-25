package Tetris;

public class Piece2{
    private class Point{
        public int x, y;
        public void set(int i, int j){
            x = i; y = j;
        }
    }

    private Point[] p;
    private Point c;
    public byte color;

    public Piece2(){
        p = new Point[4];
        for(Point i: p)
            i = new Point();
        c = new Point();
    }
    public void set(byte type){
        color = type;

        if(type == 0){
            p[0].set(4, 19);
            p[1].set(5, 19);
            p[2].set(4, 18);
            p[3].set(5, 18);
        }
    }
}