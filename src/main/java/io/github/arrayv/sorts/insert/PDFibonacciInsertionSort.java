package io.github.arrayv.sorts.insert;

import io.github.arrayv.main.ArrayVisualizer;
import io.github.arrayv.sorts.templates.Sort;

/*
 *
MIT License

Copyright (c) 2020 fungamer2
Copyright (c) 2021-2026 Flanlaina, Sorting Algorithm Scarlet

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 *
 */

/**
 * @author Flanlaina
 * @author fungamer2
 *
 */
public final class PDFibonacciInsertionSort extends Sort {

    public PDFibonacciInsertionSort(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        this.setSortListName("Pattern-Defeating Fibonacci Insertion");
        this.setRunAllSortsName("Pattern-Defeating Fibonacci Insertion Sort");
        this.setRunSortName("Pattern-Defeating Fibonacci Insertion Sort");
        this.setCategory("Insertion Sorts");
        this.setAuthors("Flanlaina, fungamer2");
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }

    public int fibonacciSearch(int[] array, int start, int end, int item) {
        int fibM2 = 0;
        int fibM1 = 1;
        int fibM = 1;
        while (fibM <= end - start) {
            fibM2 = fibM1;
            fibM1 = fibM;
            fibM = fibM2 + fibM1;
        }
        int offset = start - 1;
        while (fibM > 1) {
            int i = Math.min(offset + fibM2, end);
            Highlights.markArray(1, offset + 1);
            Highlights.markArray(2, i);
            if (Reads.compareValues(array[i], item) <= 0) {
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
            } else {
                fibM = fibM2;
                fibM1 -= fibM2;
                fibM2 = fibM - fibM1;
            }
            Delays.sleep(0.6);
        }
        int position = ++offset;
        if (Reads.compareValues(array[position], item) <= 0) {
            position++;
        }
        return position;
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

    public void fibonacciInsertSort(int[] array, int a, int b) {
        for(int i = findRun(array, a, b, 0.6, 0.15, false); i < b; i++) {
            int tmp = array[i];
            int dest = this.fibonacciSearch(array, a, i - 1, tmp);
            int pos = i;
            while (pos > dest) {
                Writes.write(array, pos, array[pos - 1], 0.15, true, false);
                pos--;
            }
            if (pos < i) Writes.write(array, pos, tmp, 0.15, true, false);
        }
    }

    @Override
    public void runSort(int[] array, int sortLength, int bucketCount) {
        fibonacciInsertSort(array, 0, sortLength);

    }

}
