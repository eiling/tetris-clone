package Tetris;

public class Piece3{
    public boolean[][] m;
    private boolean[][] rot;
    public int y, x;
    public byte type;

    private Block[][] matrix;

    public Piece3(Block[][] matrix){
        this.matrix = matrix;
        m = new boolean[4][4];
        rot = new boolean[4][4];
    }
    public void set(byte type){
        this.type = type;

        for(boolean[] j: m) for(boolean i: j) i = false;

        if(type == 0){
            m[1][0] = true; m[1][1] = true;
            m[0][0] = true; m[0][1] = true;
            y = 18; x = 4;
        }
        if(type == 1){
            m[1][0] = true;
            m[1][1] = true;
            m[1][2] = true;
            m[1][3] = true;
            y = 18; x = 3;
        }
    }
    public void rotate(){
        if(type == 0) return;
        if(type == 1){
            for(int j = 0; j < 4; j++)
                for(int i = 0; i < 4; i++)
                    rot[j][i] = m[3-i][j];
            boolean[][] temp = m;
            m = rot;
            try{
                if(collides()) m = temp;
            } catch (ArrayIndexOutOfBoundsException e){
                m = temp;
            }
            return;
        }
    }
    public void moveRight(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
                if(m[j][i] && x+i > 9) return;
        x++;
    }
    public void moveLeft(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
                if(m[j][i] && x+i < 0) return;
        x--;
    }
    public void moveDown(){
        y--;
    }
    public void hardDrop(){
        while(!shouldStop()) y--;
    }
    public void place(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
                if(m[j][i]){
                    matrix[y+j][x+i].show = true;
                    matrix[y+j][x+i].color = type;
                }
    }
    public boolean collides(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
                if(m[j][i] && matrix[y+j][x+i].show)
                    return true;
        return false;
    }
    public boolean shouldStop(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
                if(m[j][i] && (y + j == 0 || matrix[y+j][x+i].show))
                        return true;
        return false;
    }
}
