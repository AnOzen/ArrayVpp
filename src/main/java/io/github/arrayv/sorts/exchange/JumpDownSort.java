package io.github.arrayv.sorts.exchange;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/**
 * @author Flanlaina
 * @author fungamer2
 * @author McDude_73
 */
public final class JumpDownSort extends Sort {
    public JumpDownSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        setSortListName("Jump Down");
        setRunAllSortsName("Jump Down Sort");
        setRunSortName("Jump Down Sort");
        setCategory("Exchange Sorts");
        setAuthors("Flanlaina, fungamer2, McDude_73");
        setConstant("n^2");
        setBucketSort(false);
        setRadixSort(false);
        setUnreasonablySlow(false);
        setUnreasonableLimit(0);
        setBogoSort(false);
    }

    public void sort(int[] array, int a, int b) {
        for (int i = b - 1; i > a; i--)
            for (int j = a; j < i; j++)
                if (Reads.compareIndices(array, i, j, 0.1, true) < 0)
                    Writes.swap(array, i, j, 0.2, true, false);
    }

    public void runSort(int[] array, int length, int bucketCount) {
        sort(array, 0, length);
    }
}
