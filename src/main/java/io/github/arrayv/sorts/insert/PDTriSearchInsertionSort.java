package io.github.arrayv.sorts.insert;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/**
 * @author Flanlaina
 * @author Gaming32
 * @author thatsOven
 *
 */
public final class PDTriSearchInsertionSort extends Sort {

    public PDTriSearchInsertionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Pattern-Defeating TriSearch Insertion");
        this.setRunAllSortsName("Pattern-Defeating TriSearch Insertion Sort");
        this.setRunSortName("Pattern-Defeating TriSearch Insertsort");
        this.setCategory("Insertion Sorts");
        this.setAuthors("Flanlaina, Gaming32, thatsOven");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
    
    public int triSearch(int[] array, int a, int b, int val, double sleep) {
        while (a < b) {
            int m = a + (b - a) / 2;
            Highlights.markArray(1, a);
            Highlights.markArray(2, m);
            Highlights.markArray(3, b - 1);
            Delays.sleep(sleep);
            if (Reads.compareValues(val, array[a]) < 0) break;
            if (Reads.compareValues(val, array[b - 1]) >= 0) {
                Highlights.clearAllMarks();
                return b;
            }
            if (Reads.compareValues(val, array[m]) < 0) {
                a = a + 1;
                b = m;
            } else {
                a = m + 1;
                b = b - 1;
            }
        }
        Highlights.clearAllMarks();
        return a;
    }

    protected int findRun(int[] array, int a, int b, double rSleep, double wSleep, boolean auxwrite) {
        int i = a + 1;
        if (i >= b) return i;
        boolean dir = Reads.compareIndices(array, i - 1, i++, rSleep, true) <= 0;
        while (i < b) {
            if (dir ^ Reads.compareIndices(array, i - 1, i, rSleep, true) <= 0) break;
            i++;
        }
        if (!dir) Writes.reversal(array, a, i - 1, wSleep, true, auxwrite);
        Highlights.clearMark(2);
        return i;
    }
    
    /**
     * Sorts the range {@code [a, b)} of {@code array} using Tri-Search Insertion Sort.
     * 
     * @param array the array
     * @param a the start of the range, inclusive
     * @param b the end of the range, exclusive
     * @param rSleep the comparison delay
     * @param wSleep the write delay
     */
    public void triInsertSort(int[] array, int a, int b, double rSleep, double wSleep) {
        for (int i = findRun(array, a, b, rSleep, wSleep, false); i < b; i++) {
            int current = array[i];
            int dest = triSearch(array, a, i, current, rSleep);
            int pos = i;
            while (pos > dest) {
                Writes.write(array, pos, array[pos - 1], wSleep, true, false);
                pos--;
            }
            if (pos < i) Writes.write(array, pos, current, wSleep, true, false);
        }
    }


    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        triInsertSort(array, 0, sortLength, 40.0, 1.0);

    }

}
