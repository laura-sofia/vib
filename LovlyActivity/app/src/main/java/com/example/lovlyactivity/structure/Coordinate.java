package com.example.lovlyactivity.structure;

public class Coordinate {
    public int i;
    public int j;

    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate c = (Coordinate) o;
        return (c.i == i && c.j == j);
    }

    @Override
    public int hashCode() {
        return (i * 10) + j;
    }
}
