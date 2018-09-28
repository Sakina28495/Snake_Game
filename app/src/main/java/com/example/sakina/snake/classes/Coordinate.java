package com.example.sakina.snake.classes;

/**
 * Created by Sakina on 3/9/2018.
 */

public class Coordinate  {
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

}
