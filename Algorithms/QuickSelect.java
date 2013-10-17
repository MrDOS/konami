import java.util.*;

public class QuickSelect
{
    /**
     * Find the k-th element of a list in average O(n) time.
     * (The element is found as if the list were sorted...)
     * O(n^2) worst case.
     * @param list the list to sift through
     * @param left the left index of the sublist -- to start, this should be 0
     * @param right the right index of the sublist -- to start, this should be
     * list.size() - 1.
     * @param k the kth element to find (1..n not 0..n-1)
     * @return the k-th element of the list
     */
    public static <T extends Comparable<? super T>> T quickSelect(
        List<T> list, int left, int right, int k) {
        if (k < 1 || k > list.size()) return null;
        if (left == right) return list.get(left);
        int pivot = partition(list, left, right);
        int size = pivot - left + 1;
        if (size == k) return list.get(pivot);
        if (size > k) return quickSelect(list, left, pivot - 1, k);
        return quickSelect(list, pivot + 1, right, k - size);
    }

    /**
     * Partitions the a list (using a median of three pivot).
     * @param list the list
     * @param the left index
     * @param the right index
     * @return the index of the pivot
     */
    public static <T extends Comparable<? super T>> int partition(
        List<T> list, int left, int right) {
        int pivot = medianOfThree(list, left, right);
        T tVal = list.get(pivot);
        int store = left;
        swap(list, pivot, right);
        for (int i = left; i < right; i++)
            if (list.get(i).compareTo(tVal) < 0)
                swap(list, store++, i);
        swap(list, right, store);
        return store;
    }

    /**
     * Sort first, center and last elements of a list.  This puts the
     * pivot at last - 1.
     * @param list the list
     * @param left the left index
     * @param right the right index
     * @return the index of the pivot
     */
    public static <T extends Comparable<? super T>> int medianOfThree(
        List<T> list, int left, int right) {
        int center = (left + right) / 2;
        if (list.get(left).compareTo(list.get(right)) > 0)
            swap(list, left, center);
        if (list.get(left).compareTo(list.get(right)) > 0)
            swap(list, left, right);
        if (list.get(center).compareTo(list.get(right)) > 0)
            swap(list, center, right);
        swap(list, center, right - 1);
        return right - 1;
    }

    /**
     * Swap elements of a list.
     * @param list the list
     * @param i one index
     * @param j another index
     */
    public static <T extends Comparable<? super T>> void swap(
        List<T> list, int i, int j) {
        T iT = list.get(i);
        T jT = list.get(j);
        list.set(i, jT);
        list.set(j, iT);
    }
}
