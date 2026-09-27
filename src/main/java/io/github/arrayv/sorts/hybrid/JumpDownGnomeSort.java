package io.github.arrayv.sorts.hybrid;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/**
 * @author Flanlaina
 * @author fungamer2
 * @author McDude_73
 */
public class JumpDownGnomeSort extends Sort {
    public JumpDownGnomeSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        setSortListName("Jump Down Gnome");
        setRunAllSortsName("Jump Down Gnome Sort");
        setRunSortName("Jump Down Gnomesort");
        setCategory("Hybrid Sorts");
        setAuthors("Flanlaina, fungamer2, McDude_73");
        setConstant("n^2");
        setBucketSort(false);
        setRadixSort(false);
        setUnreasonablySlow(false);
        setUnreasonableLimit(0);
        setBogoSort(false);
    }

    public void sort(int[] array, int a, int b) {
        for (int i = b - 1; i > a + (b - a) / 2; i--)
            for (int j = a; j < i; j++)
                if (Reads.compareIndices(array, i, j, 0.1, true) < 0)
                    Writes.swap(array, i, j, 0.2, true, false);
        for (int i = a + 1; i < b;) {
            if (Reads.compareIndices(array, i - 1, i, 0.03, true) > 0) {
                Writes.swap(array, i, i - 1, 0.03, true, false);
                if (i > a + 1) i--;
            } else i++;
        }
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        sort(array, 0, sortLength);
    }
}
