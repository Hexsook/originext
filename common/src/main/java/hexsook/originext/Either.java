package hexsook.originext;

import java.util.function.Function;

/**
 * A container that holds either a {@code Left} value or a {@code Right} value.
 *
 * @param <L> the type of the {@code Left} value
 * @param <R> the type of the {@code Right} value
 */
public class Either<L, R> {

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(null, right);
    }

    private final L left;
    private final R right;

    private Either(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public L getLeftOrCompute(Function<R, L> function) {
        if (isLeft()) {
            return left;
        }
        return function.apply(right);
    }

    public R getRightOrCompute(Function<L, R> function) {
        if (isRight()) {
            return right;
        }
        return function.apply(left);
    }
}
