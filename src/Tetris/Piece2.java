package Tetris;

public class Piece2 {
    public int x, y;
    public Block[][] m = new Block[2][4];
    public byte color;

    public byte rot;

    public void set(byte type){
        color = type;
        rot = 0;

        if(type==0){
            m[0][0].show = false;
            m[0][1].show = true;
            m[0][2].show = true;
            m[0][3].show = false;
            m[1][0].show = false;
            m[1][1].show = true;
            m[1][2].show = true;
            m[1][3].show = false;
        }
    }
    public Block get(int x, int y){
        if(rot==0)
            return m[y][x];
        else if(rot==1)
            return
    }
}
