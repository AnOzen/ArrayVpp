package io.github.arrayv.sorts.misc;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sortdata.SortMeta;
import io.github.arrayv.sorts.templates.Sort;

@SortMeta(
    name = "Simple Safe Stalin",
    category = "Miscellaneous Sorts",
    authors = "Flanlaina"
)
public class SimpleSafeStalinSort extends Sort {
    public SimpleSafeStalinSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
    }

    public int maxSorted(int[] array, int start, int end, double delay, boolean mark) {
        int a = end - 1;
        int b = end - 1;
        boolean segment = true;
        while (segment) {
            if (b - 1 < start) return start;
            if (Reads.compareIndices(array, b - 1, b, delay, mark) > 0) segment = false;
            else b--;
        }
        int sel = b - 1;
        for (int s = b - 2; s >= start; s--) if (Reads.compareIndices(array, sel, s, delay, mark) < 0) sel = s;
        while (Reads.compareIndices(array, sel, a, delay, mark) <= 0) {
            a--;
            if (a < start) break;
        }
        return a + 1;
    }

    public void sort(int[] array, int a, int b) {
        int length = b - a;
        int[] aux = Writes.createExternalArray(length);
        b = maxSorted(array, a, b, 0.25, true);
        while (b - a > 1) {
            int j = a + 1, k = 0;
            for (int i = a + 1; i < b; i++) {
                if (Reads.compareIndices(array, j - 1, i, 0.25, true) <= 0)
                    Writes.write(array, j++, array[i], 0.25, true, false);
                else
                    Writes.write(aux, k++, array[i], 0.25, true, true);
            }
            int pEnd = b;
            while (j > a) {
                Highlights.markArray(2, --j);
                Writes.write(array, --pEnd, array[j], 0.25, true, false);
            }
            Highlights.clearMark(2);
            Writes.arraycopy(aux, 0, array, a, pEnd - a, 0.25, true, false);
            b = maxSorted(array, a, b, 0.25, true);
        }
        Writes.deleteExternalArray(aux);
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) throws Exception {
        sort(array, 0, sortLength);
    }
}
