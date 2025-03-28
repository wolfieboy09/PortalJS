package dev.wolfieboy09.portaljs.api;

public class BiHolder<L, R> {
    private final L left;
    private final R right;

    public BiHolder(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }
}
