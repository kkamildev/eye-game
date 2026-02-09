package components;

public class Vector2 {
    public int x, y;
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 sub(Vector2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }
    public Vector2 mul(int s) {
        this.x *= s;
        this.y *= s;
        return this;
    }
}
