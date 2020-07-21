package com.example.zy_1;

public class PbMessage {
    private int flag;
    private int  progress;
    private int max;

    public PbMessage(int flag, int progress, int max) {
        this.flag = flag;
        this.progress = progress;
        this.max = max;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "PbMessage{" +
                "flag=" + flag +
                ", progress=" + progress +
                ", max=" + max +
                '}';
    }
}
