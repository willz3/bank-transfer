package com.example.banktransfer.core.shared.logic;

import java.util.Optional;
import java.util.function.Function;

public class Either<L, R> {

    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L,R> Either<L,R> Left( L value) {
        return new Either<>(value, null);
    }

    public static <L,R> Either<L,R> Right( R value) {
        return new Either<>(null, value);
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }



    public String toString() {
        if (isLeft()) {
            return "Left(" + left +")";
        }
        return "Right(" + right +")";
    }
}