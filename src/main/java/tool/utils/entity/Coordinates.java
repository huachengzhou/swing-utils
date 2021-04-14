package tool.utils.entity;

public class Coordinates {
    private int x;
    private int y;
    private int column ;
    private int row;

    public int getY() {
        return y;
    }

    public Coordinates setY(int y) {
        this.y = y;
        return this;
    }

    public int getRow() {
        return row;
    }

    public Coordinates setRow(int row) {
        this.row = row;
        return this;
    }

    public int getX() {
        return x;
    }

    public Coordinates setX(int x) {
        this.x = x;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public Coordinates setColumn(int column) {
        this.column = column;
        return this;
    }

    public Coordinates(int y, int row) {
        this.y = y;
        this.row = row;
    }

    public Coordinates(int x, int y, int column, int row) {
        this.x = x;
        this.y = y;
        this.column = column;
        this.row = row;
    }

    public Coordinates() {
    }
}
