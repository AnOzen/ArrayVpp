package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class TablePancakeSort extends Sort {

    public TablePancakeSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Table Pancake");
        this.setRunAllSortsName("Table Pancake Sorting");
        this.setRunSortName("Table Pancake Sort");
        this.setCategory("Miscellaneous Sorts");
        this.setAuthors("AnOzen");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    private boolean stableComp(int[] array, int[] table, int a, int b) {
        int comp = Reads.compareIndices(array, table[a], table[b], 0.5, true);

        return comp > 0 || (comp == 0 && Reads.compareOriginalIndices(table, a, b, 0.5, false) > 0);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        int[] table = Writes.createExternalArray(sortLength);
        for (int i = 0; i < sortLength; i++) {
            Writes.write(table, i, i, 1, true, true);
        }
        Highlights.clearAllMarksPossible();

        for (int i = sortLength - 1; i >= 0; i--) {
            int maxind = i;
            for (int j = i - 1; j >= 0; j--) {
                if (this.stableComp(array, table, j, maxind)) {
                    maxind = j;
                }
            }
            Writes.reversal(table, 0, maxind, 1, true, true);
            Writes.reversal(table, 0, i, 1, true, true);
        }

        for (int i = 0; i < sortLength; i++) {
            if (Reads.compareOriginalValues(i, table[i]) != 0) {
                int t = array[i];
                int j = i;
                int n = table[j];
                while (Reads.compareOriginalValues(n, i) != 0) {

                    Writes.write(array, j, array[n], 1, true, false);
                    Writes.write(table, j, j, 1, true, true);

                    j = n;
                    n = table[j];
                }
                Writes.write(array, j, t, 1, true, false);
                Writes.write(table, j, j, 1, true, false);
            }

        }
    }

}
