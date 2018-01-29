package io.github.ardenchew.lastpiece;

import java.util.ArrayList;

public class Board {

    public ArrayList<Column> boardList;
    public int[] boardDim;
    public int size;

    Board(int[] bd) {
        this.boardList = new ArrayList<Column>();
        this.resize(bd);
    }

    public Column get(int position) {
        return this.boardList.get(position);
    }

    public Column remove(int cIdx) {
        Column temp = this.boardList.remove(cIdx);
        this.size--;
        return temp;
    }

    public Packet remove(int pIdx, int cIdx) {
        if (cIdx < this.size) {
            Column temp = this.boardList.get(cIdx);
            Packet tempRet = temp.remove(pIdx);
            this.boardList.set(cIdx, temp);
            return tempRet;
        }
        return null;
    }

    public Packet get(int pIdx, int cIdx) {
        if (cIdx < this.size) {
            return this.boardList.get(cIdx).get(pIdx);
        }
        return null;
    }

    public boolean has(int pIdx, int cIdx) {
        if (cIdx < this.size) {
            return this.boardList.get(cIdx).has(pIdx);
        }
        return false;
    }

    public boolean add(Column c) {
        this.size++;
        return this.boardList.add(c);
    }

    public void clear() {
        this.boardList.clear();
        this.size = 0;
    }

    public void reset() {
        this.clear();
        this.resize(this.boardDim);

    }

    public void resize(int[] bd) {
        this.clear();
        this.boardDim = bd;
        this.size = this.boardDim.length;

        for (int i = 0; i < this.size; i++) {
            Column temp = new Column(i);
            temp.fill(this.boardDim[i]);
            this.boardList.add(temp);
        }
    }

    public boolean isEmpty() {
        return (this.getPacketNum() == 0);
    }

    public int size() {
        return this.size;
    }

    public int getPacketNum() {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            count += this.get(i).getPacketCount();
        }
        return count;
    }

}