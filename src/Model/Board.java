package Model;

import java.util.ArrayList;

public class Board {
    public int widthTiles;
    public int heightTiles;
    public ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<String> list2 = new ArrayList<>();

    public Board(ArrayList<Integer> list, ArrayList<String> list2, int widthTiles, int heightTiles) {
        this.list = list;
        this.list2 = list2;
        this.heightTiles = heightTiles;
        this.widthTiles = widthTiles;
    }

    public Board() {
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<String> getList2() {
        return list2;
    }

    public void setList2(ArrayList<String> list2) {
        this.list2 = list2;
    }

    public int getWidthTiles() {
        return widthTiles;
    }

    public void setWidthTiles(int widthTiles) {
        this.widthTiles = widthTiles;
    }

    public int getHeightTiles() {
        return heightTiles;
    }

    public void setHeightTiles(int heightTiles) {
        this.heightTiles = heightTiles;
    }
}
