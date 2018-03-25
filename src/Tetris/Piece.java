package Tetris;

import java.nio.FloatBuffer;

public class Piece {
    private int x1,x2,x3,x4;
    private int y1,y2,y3,y4;

    private byte colorByte;

    private float cx, cy;

    private static final float[][] color = {
            {1f, 0f, 0f},
            {0f, 1f, 0f},
            {0f, 0f, 1f},
            {1f, 0f, 1f},
            {1f, 1f, 0f},
            {0f, 1f, 1f},
            {1f, 1f, 1f}
    };

    private static final float scale = 0.09f; //static?
    private static final float translateX = -0.45f;
    private static final float translateY = -0.9f;
    private static final float border = 0.005f;

    public void set(byte type){
        colorByte = type;

        if(type==0){
            x1 = 4; y1 = 18;
            x2 = 5; y2 = 18;
            x3 = 4; y3 = 19;
            x4 = 5; y4 = 19;

            cx = 4.5f; cy = 18.5f;
        } else if(type==1){
            x1 = 3; y1 = 19;
            x2 = 4; y2 = 19;
            x3 = 5; y3 = 19;
            x4 = 6; y4 = 19;

            cx = 5.0f; cy = 19.0f;
        } else if(type==2){
            x1 = 3; y1 = 18;
            x2 = 4; y2 = 18;
            x3 = 4; y3 = 19;
            x4 = 5; y4 = 18;

            cx = 4.0f; cy = 18.0f;
        } else if(type==3){
            x1 = 3; y1 = 18;
            x2 = 4; y2 = 18;
            x3 = 5; y3 = 18;
            x4 = 5; y4 = 19;

            cx = 4.0f; cy = 18.0f;
        } else if(type==4){
            x1 = 3; y1 = 19;
            x2 = 3; y2 = 18;
            x3 = 4; y3 = 18;
            x4 = 5; y4 = 18;

            cx = 4.0f; cy = 18.0f;
        } else if(type==5){
            x1 = 3; y1 = 18;
            x2 = 4; y2 = 18;
            x3 = 4; y3 = 19;
            x4 = 5; y4 = 19;

            cx = 4.0f; cy = 19.0f;
        } else if(type==6){
            x1 = 3; y1 = 19;
            x2 = 4; y2 = 19;
            x3 = 4; y3 = 18;
            x4 = 5; y4 = 18;

            cx = 4.0f; cy = 19.0f;
        }
    }
    public void rotate(Block[][] matrix){
        int x, y;

        x = x1; y = y1;
        x1 = -y+(int)(cy+cx); y1 = x-(int)(cx-cy);

        x = x2; y = y2;
        x2 = -y+(int)(cy+cx); y2 = x-(int)(cx-cy);

        x = x3; y = y3;
        x3 = -y+(int)(cy+cx); y3 = x-(int)(cx-cy);

        x = x4; y = y4;
        x4 = -y+(int)(cy+cx); y4 = x-(int)(cx-cy);

        int leftmost = x1;
        if(x2 < leftmost) {
            leftmost = x2;
        }
        if(x3 < leftmost) {
            leftmost = x3;
        }
        if(x4 < leftmost) {
            leftmost = x4;
        }

        while(leftmost < 0){
            leftmost++;
            x1++;
            x2++;
            x3++;
            x4++;
            cx++;
        }

        int rightmost = x1;
        if(x2 > rightmost) {
            rightmost = x2;
        }
        if(x3 > rightmost) {
            rightmost = x3;
        }
        if(x4 > rightmost) {
            rightmost = x4;
        }

        while(rightmost > 9){
            rightmost--;
            x1--;
            x2--;
            x3--;
            x4--;
            cx--;
        }

        int upmost = y1;
        if(y2 > upmost) {
            upmost = y2;
        }
        if(y3 > upmost) {
            upmost = y3;
        }
        if(y4 > upmost) {
            upmost = y4;
        }

        while(upmost > 19){
            upmost--;
            y1--;
            y2--;
            y3--;
            y4--;
            cy--;
        }
    }
    public void moveLeft(Block[][] matrix){
        int leftmost = x1;
        int y = y1;
        if(x2 < leftmost) {
            leftmost = x2;
            y = y2;
        }
        if(x3 < leftmost) {
            leftmost = x3;
            y = y3;
        }
        if(x4 < leftmost) {
            leftmost = x4;
            y = y4;
        }

        if(leftmost <= 0) return;
        if(!matrix[y][leftmost-1].show) {
            x1--;
            x2--;
            x3--;
            x4--;

            cx--;
        }
    }
    public void moveRight(Block[][] matrix){
        int rightmost = x1;
        int y = y1;
        if(x2 > rightmost) {
            rightmost = x2;
            y = y2;
        }
        if(x3 > rightmost) {
            rightmost = x3;
            y = y3;
        }
        if(x4 > rightmost) {
            rightmost = x4;
            y = y4;
        }

        if(rightmost >= 9) return;
        if(!matrix[y][rightmost+1].show) {
            x1++;
            x2++;
            x3++;
            x4++;

            cx++;
        }
    }
    public void hardDrop(Block[][] matrix){
        while(!stop(matrix)) moveDown();
    }
    public void moveDown(){
        y1--;
        y2--;
        y3--;
        y4--;

        cy--;
    }
    private int leftmost(){
        int l = x1;
        if(x2 < l) l = x2;
        if(x3 < l) l = x3;
        if(x4 < l) l = x4;
        return l;
    }
    private int rightmost(){
        int r = x1;
        if(x2 > r) r = x2;
        if(x3 > r) r = x3;
        if(x4 > r) r = x4;
        return r;
    }
    private int downmost(){
        int d = y1;
        if(y2 < d) d = y2;
        if(y3 < d) d = y3;
        if(y4 < d) d = y4;
        return d;
    }
    private int upmost(){
        int u = y1;
        if(y2 > u) u = y2;
        if(y3 > u) u = y3;
        if(y4 > u) u = y4;
        return u;
    }
    public boolean stop(Block[][] matrix){
        if(y1 == 0) return true;
        if(matrix[y1-1][x1].show) return true;

        if(y2 == 0) return true;
        if(matrix[y2-1][x2].show) return true;

        if(y3 == 0) return true;
        if(matrix[y3-1][x3].show) return true;

        if(y4 == 0) return true;
        if(matrix[y4-1][x4].show) return true;

        return false;
    }
    public void place(Block[][] matrix){
        matrix[y1][x1].show = true;
        matrix[y2][x2].show = true;
        matrix[y3][x3].show = true;
        matrix[y4][x4].show = true;

        matrix[y1][x1].setColor(colorByte);
        matrix[y2][x2].setColor(colorByte);
        matrix[y3][x3].setColor(colorByte);
        matrix[y4][x4].setColor(colorByte);
    }
    public boolean checkOverlap(Block[][] matrix){
        if(matrix[y1][x1].show) return true;
        if(matrix[y2][x2].show) return true;
        if(matrix[y3][x3].show) return true;
        if(matrix[y4][x4].show) return true;
        return false;
    }
    public void put(FloatBuffer buffer){
        buffer.put(x1 * scale + translateX + border).put(y1 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x1+1) * scale + translateX - border).put(y1 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x1+1) * scale + translateX - border).put((y1+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x1 * scale + translateX + border).put(y1 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x1+1) * scale + translateX - border).put((y1+1) * scale + translateY - border).put(color[colorByte]);
        buffer.put(x1 * scale + translateX + border).put((y1+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x2 * scale + translateX + border).put(y2 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x2+1) * scale + translateX - border).put(y2 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x2+1) * scale + translateX - border).put((y2+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x2 * scale + translateX + border).put(y2 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x2+1) * scale + translateX - border).put((y2+1) * scale + translateY - border).put(color[colorByte]);
        buffer.put(x2 * scale + translateX + border).put((y2+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x3 * scale + translateX + border).put(y3 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x3+1) * scale + translateX - border).put(y3 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x3+1) * scale + translateX - border).put((y3+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x3 * scale + translateX + border).put(y3 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x3+1) * scale + translateX - border).put((y3+1) * scale + translateY - border).put(color[colorByte]);
        buffer.put(x3 * scale + translateX + border).put((y3+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x4 * scale + translateX + border).put(y4 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x4+1) * scale + translateX - border).put(y4 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x4+1) * scale + translateX - border).put((y4+1) * scale + translateY - border).put(color[colorByte]);

        buffer.put(x4 * scale + translateX + border).put(y4 * scale + translateY + border).put(color[colorByte]);
        buffer.put((x4+1) * scale + translateX - border).put((y4+1) * scale + translateY - border).put(color[colorByte]);
        buffer.put(x4 * scale + translateX + border).put((y4+1) * scale + translateY - border).put(color[colorByte]);
    }
}
