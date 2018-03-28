package Tetris;

public class Piece3{
    public boolean[][] m;
    public int y, x;
    public byte type;

    private Block[][] matrix;

    public Piece3(Block[][] matrix){
        this.matrix = matrix;
        m = new boolean[4][4];
    }
    public void set(byte type){
        this.type = type;

        for(int j = 0; j < 4; j ++) for(int i = 0; i < 4; i++) m[j][i] = false;

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
        } if(type == 2){
            m[1][0] = true;
            m[1][1] = true;
            m[1][2] = true;
            m[2][1] = true;
            y = 17; x = 3;
        } if(type == 3){
            m[1][0] = true;
            m[1][1] = true;
            m[1][2] = true;
            m[2][2] = true;
            y = 17; x = 3;
        } if(type == 4){
            m[2][0] = true;
            m[1][0] = true;
            m[1][1] = true;
            m[1][2] = true;
            y = 17; x = 3;
        } if(type == 5){
            m[1][0] = true;
            m[1][1] = true;
            m[0][1] = true;
            m[0][2] = true;
            y = 18; x = 3;
        } if(type == 6){
            m[0][0] = true;
            m[0][1] = true;
            m[1][1] = true;
            m[1][2] = true;
            y = 18; x = 3;
        }
    }
    public void rotate(){
        if(type == 0) return;
        if(type == 1){
            for(int j = 0; j < 4; j++)
                for(int i = 0; i < 4; i++)
                    if(m[j][i]){
                        if(y + 3 - i < 0 || y + 3 - i > 19) return;
                        if(x + j < 0 || x + j > 9) return;
                        if(matrix[y+3-i][x+j].show) return;
                    }
            for(int i = 0; i < 3; i++){
                boolean temp = m[0][i];
                m[0][i] = m[3-i][0];
                m[3-i][0] = m[3][3-i];
                m[3][3-i] = m[i][3];
                m[i][3] = temp;
            }
            boolean temp = m[1][1];
            m[1][1] = m[2][1];
            m[2][1] = m[2][2];
            m[2][2] = m[1][2];
            m[1][2] = temp;
        } else{
            for(int j = 0; j < 3; j++)
                for(int i = 0; i < 3; i++)
                    if(m[j][i]){
                        if(y + 2 - i < 0 || y + 2 - i > 19) return;
                        if(x + j < 0 || x + j > 9) return;
                        if(matrix[y+2-i][x+j].show) return;
                    }
            for(int i = 0; i < 2; i++){
                boolean temp = m[0][i];
                m[0][i] = m[2-i][0];
                m[2-i][0] = m[2][2-i];
                m[2][2-i] = m[i][2];
                m[i][2] = temp;
            }
        }
    }
    public void aRotate(){
        if(type == 0) return;
        if(type == 1){
            for(int j = 0; j < 4; j++)
                for(int i = 0; i < 4; i++)
                    if(m[j][i]){
                        if(y + i < 0 || y + i > 19) return;
                        if(x + 3 - j < 0 || x + 3 - j > 9) return;
                        if(matrix[y+i][x+3-j].show) return;
                    }
            for(int i = 0; i < 3; i++){
                boolean temp = m[0][i];
                m[0][i] = m[i][3];
                m[i][3] = m[3][3-i];
                m[3][3-i] = m[3-i][0];
                m[3-i][0] = temp;
            }
            boolean temp = m[1][1];
            m[1][1] = m[1][2];
            m[1][2] = m[2][2];
            m[2][2] = m[2][1];
            m[2][1] = temp;
        } else{
            for(int j = 0; j < 3; j++)
                for(int i = 0; i < 3; i++)
                    if(m[j][i]){
                        if(y + i < 0 || y + i > 19) return;
                        if(x + 2 - j < 0 || x + 2 - j > 9) return;
                        if(matrix[y+2-i][x+j].show) return;
                    }
            for(int i = 0; i < 2; i++){
                boolean temp = m[0][i];
                m[0][i] = m[i][2];
                m[i][2] = m[2][2-i];
                m[2][2-i] = m[2-i][0];
                m[2-i][0] = temp;
            }
        }
    }
    public void moveRight(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++){
                if(m[j][i] && x+i >= 9) return;
                if(m[j][i] && matrix[y+j][x+i+1].show) return;
            }
        x++;
    }
    public void moveLeft(){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++) {
                if(m[j][i] && x+i <= 0) return;
                if(m[j][i] && matrix[y+j][x+i-1].show) return;
            }
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
                if(m[j][i] && (y + j == 0 || matrix[y+j-1][x+i].show))
                        return true;
        return false;
    }
}
