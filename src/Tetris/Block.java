package Tetris;

public class Block {
    public byte color;
    public boolean show;

    public Block(){
        show = false;
    }
    public void setColor(byte color){
        this.color = color;
    }
}
