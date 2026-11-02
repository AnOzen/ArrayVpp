package io.github.arrayv.sorts.select;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

public class TableSelectionSort extends Sort {

    public TableSelectionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);

        this.setSortListName("Table Selection");
        this.setRunAllSortsName("Table Selection Sort");
        this.setRunSortName("Table Selectionsort");
        this.setCategory("Selection Sorts");
        this.setAuthors("AnOzen");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    private boolean stableComp(int[] array, int[] table, int a, int b) {
        int comp = Reads.compareIndices(array, table[a], table[b], 0.05, true);

        return comp > 0 || (comp == 0 && Reads.compareOriginalIndices(table, a, b, 0.05, false) > 0);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        int[] table = Writes.createExternalArray(sortLength);
        for (int i = 0; i < sortLength; i++) {
            Highlights.markArray(1, i);
            Writes.write(table, i, i, 0.1, true, true);
        }

        for (int i = 0; i < sortLength; i++) {
            int minind = i;
            for (int j = i; j < sortLength; j++) {
                if (this.stableComp(array, table, minind, j)) {
                    minind = j;
                }
            }
            Writes.swap(table, minind, i, 0.1, true, true);
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
