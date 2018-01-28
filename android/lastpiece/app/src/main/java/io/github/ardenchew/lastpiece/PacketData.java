package io.github.ardenchew.lastpiece;

public class PacketData {

    public int columnNumber;
    public int packetNumber;

    public PacketData() {}

    public void setColumnNum(int cn) {
        this.columnNumber = cn;
    }

    public void setPacketNum(int pn) {
        this.packetNumber = pn;
    }

    public int getColumnNum() {
        return this.columnNumber;
    }

    public int getPacketNum() {
        return this.packetNumber;
    }

}