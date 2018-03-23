package Tetris;

public class Block {
    public float[] colorVec;
    public boolean show;

    private static final float[] red = {1f, 0f, 0f};
    private static final float[] green = {0f, 1f, 0f};
    private static final float[] blue = {0f, 0f, 1f};
    private static final float[] magenta = {1f, 0f, 1f};
    private static final float[] yellow = {1f, 1f, 0f};
    private static final float[] cyan = {0f, 1f, 1f};
    private static final float[] white = {1f, 1f, 1f};

    public Block(){
        show = false;
    }
    public void setColor(byte color){
        switch (color){
            case 0: colorVec = red; break;
            case 1: colorVec = green; break;
            case 2: colorVec = blue; break;
            case 3: colorVec = magenta; break;
            case 4: colorVec = yellow; break;
            case 5: colorVec = cyan; break;
            default: colorVec = white;
        }
    }
}
