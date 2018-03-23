package Tetris;

import java.nio.FloatBuffer;

public class Piece2 {
    public int x, y;
    public boolean[][] m = new boolean[4][4];
    public byte colorByte;

    private static final float[][] color = {
            {1f, 0f, 0f},
            {0f, 1f, 0f},
            {0f, 0f, 1f},
            {1f, 0f, 1f},
            {1f, 1f, 0f},
            {0f, 1f, 1f},
            {1f, 1f, 1f}
    };

    public byte rot = 0;

    private static final float scale = 0.09f; //static?
    private static final float translateX = -0.45f;
    private static final float translateY = -0.9f;
    private static final float border = 0.005f;

    public void set(byte type){
        colorByte = type;
        rot = 0;

        if(type==0){
            x = 3; y = 17;

            m[0][0] = false;
            m[0][1] = false;
            m[0][2] = false;
            m[0][3] = false;
            m[1][0] = false;
            m[1][1] = true;
            m[1][2] = true;
            m[1][3] = false;
            m[2][0] = false;
            m[2][1] = true;
            m[2][2] = true;
            m[2][3] = false;
            m[3][0] = false;
            m[3][1] = false;
            m[3][2] = false;
            m[3][3] = false;
        }
        else if(type==1){
            x = 3; y = 16;

            m[0][0] = false;
            m[0][1] = false;
            m[0][2] = false;
            m[0][3] = false;
            m[1][0] = true;
            m[1][1] = true;
            m[1][2] = true;
            m[1][3] = true;
            m[2][0] = false;
            m[2][1] = false;
            m[2][2] = false;
            m[2][3] = false;
            m[3][0] = false;
            m[3][1] = false;
            m[3][2] = false;
            m[3][3] = false;
        }
    }
    public void rotate(){
        rot++;
        if(rot > 3) rot = 0;
    }
    public void put(FloatBuffer buffer){
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++){
                if(m[j][i])
                    if(rot==0){
                        buffer.put((x+i) * scale + translateX + border).put((y+j) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+i+1) * scale + translateX - border).put((y+j) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+i+1) * scale + translateX - border).put((y+j+1) * scale + translateY - border)
                                .put(color[colorByte]);

                        buffer.put((x+i) * scale + translateX + border).put((y+j) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+i+1) * scale + translateX - border).put((y+j+1) * scale + translateY - border)
                                .put(color[colorByte]);
                        buffer.put((x+i) * scale + translateX + border).put((y+j+1) * scale + translateY - border)
                                .put(color[colorByte]);
                    } else if(rot==1){
                        buffer.put((x+j) * scale + translateX + border).put((y+3-i) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+j+1) * scale + translateX - border).put((y+3-i) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+j+1) * scale + translateX - border).put((y+3-i+1) * scale + translateY - border)
                                .put(color[colorByte]);

                        buffer.put((x+j) * scale + translateX + border).put((y+3-i) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+j+1) * scale + translateX - border).put((y+3-i+1) * scale + translateY - border)
                                .put(color[colorByte]);
                        buffer.put((x+j) * scale + translateX + border).put((y+3-i+1) * scale + translateY - border)
                                .put(color[colorByte]);
                    } else if(rot==2){ //finish this
                        buffer.put((x+3-i) * scale + translateX + border).put((y+j) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+i+1) * scale + translateX - border).put((y+j) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+i+1) * scale + translateX - border).put((y+j+1) * scale + translateY - border)
                                .put(color[colorByte]);

                        buffer.put((x+i) * scale + translateX + border).put((y+j) * scale + translateY + border)
                                .put(color[colorByte]);
                        buffer.put((x+i+1) * scale + translateX - border).put((y+j+1) * scale + translateY - border)
                                .put(color[colorByte]);
                        buffer.put((x+i) * scale + translateX + border).put((y+j+1) * scale + translateY - border)
                                .put(color[colorByte]);
                    }
            }
    }
}
