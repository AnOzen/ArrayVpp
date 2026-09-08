package io.github.arrayv.sorts.insert;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*

Coded for ArrayV by Ayako-chan

+---------------------------+
| Sorting Algorithm Scarlet |
+---------------------------+

 */

/**
 * @author Ayako-chan
 *
 */
public final class AdaptiveExponentialInsertionSort extends Sort {

    public AdaptiveExponentialInsertionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Adaptive Exponential Insertion");
        this.setRunAllSortsName("Adaptive Exponential Insertion Sort");
        this.setRunSortName("Adaptive Exponential Insertsort");
        this.setCategory("Insertion Sorts");
        this.setAuthors("Flanlaina");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    protected int expSearch(int[] array, int a, int b, int val, double sleep) {
        int i = 1;
        int a1, b1;
        boolean dir = Reads.compareIndexValue(array, a + (b - a) / 2, val, 0, false) <= 0;
        if (dir) {
            while (b - i >= a && Reads.compareValues(val, array[b - i]) < 0)
                i *= 2;
            a1 = Math.max(a, b - i + 1);
            b1 = b - i / 2;
        } else {
            while (a - 1 + i < b && Reads.compareValues(val, array[a - 1 + i]) >= 0)
                i *= 2;
            a1 = a + i / 2;
            b1 = Math.min(b, a - 1 + i);
        }
        while (a1 < b1) {
            int m = a1 + (b1 - a1) / 2;
            Highlights.markArray(1, a1);
            Highlights.markArray(2, m);
            Highlights.markArray(3, b1);
            Delays.sleep(sleep);
            if (Reads.compareValues(val, array[m]) < 0)
                b1 = m;
            else
                a1 = m + 1;
        }
        Highlights.clearAllMarks();
        return a1;
    }

    protected void insertTo(int[] array, int a, int b, double sleep) {
        Highlights.clearMark(2);
        int temp = array[a];
        for (int i = a; i > b; i--)
            Writes.write(array, i, array[i - 1], sleep, true, false);
        if (a != b)
            Writes.write(array, b, temp, sleep, true, false);
    }

    public void segmentReversal(int[] array, int start, int end, double cSleep, double wSleep, boolean mark, boolean aux) {
        for (int i = start; i < end; i++) {
            int left = i;
            while (i < end && Reads.compareIndices(array, i, i + 1, cSleep, true) == 0) i++;
            int right = i;
            if (left != right) Writes.reversal(array, left, right, wSleep, mark, aux);
        }
    }
    
    protected int findRun(int[] array, int a, int b, double compSleep, double writeSleep) {
        int i = a + 1;
        if (i == b) return i;
        boolean lessunique = false;
        boolean different = false;
        int cmp = Reads.compareIndices(array, i - 1, i, compSleep, true);
        while (cmp == 0 && i < b) {
            lessunique = true;
            i++;
            if (i < b) cmp = Reads.compareIndices(array, i - 1, i, compSleep, true);
        }
        if (cmp > 0) {
            while (cmp >= 0 && i < b) {
                if (cmp == 0) lessunique = true;
                else different = true;
                i++;
                if (i < b) cmp = Reads.compareIndices(array, i - 1, i, compSleep, true);
            }
            if (i - a > 1 && different) {
                Writes.reversal(array, a, i - 1, writeSleep, true, false);
                if (lessunique) segmentReversal(array, a, i - 1, compSleep, writeSleep, true, false);
            }
        } else {
            while (cmp <= 0 && i < b) {
                i++;
                if (i < b) cmp = Reads.compareIndices(array, i - 1, i, compSleep, true);
            }
        }
        return i;
    }

    public void insertSort(int[] array, int a, int b, double cSleep, double wSleep) {
        for(int i = findRun(array, a, b, cSleep, wSleep); i < b; i++)
            insertTo(array, i, expSearch(array, a, i, array[i], cSleep), wSleep);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) {
        insertSort(array, 0, sortLength, 1.0, 0.25);

    }

}
