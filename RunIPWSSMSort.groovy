import io.github.arrayv.utils.Distributions
import io.github.arrayv.utils.Shuffles
def runSorts(shuffleName) {
  setCategory(shuffleName)
  run InPlaceWeaveSafeStalinMergeSort go 64.numbers, (0.25).speed
}

runGroup(81) {
  arrayv.arrayManager.setDistribution(Distributions.LINEAR) // 1
  arrayv.arrayManager.setShuffleSingle(Shuffles.RANDOM)
  runSorts("Random")

  arrayv.arrayManager.setShuffleSingle(Shuffles.REVERSE) // 2
  runSorts("Reversed")

  arrayv.arrayManager.setShuffleSingle(Shuffles.ALMOST) // 3
  runSorts("Almost Sorted")

  arrayv.arrayManager.setShuffleSingle(Shuffles.RANDOM) // 4
  arrayv.getArrayFrame().setUniqueSlider(16)
  runSorts("Many Similar")

  arrayv.setComparator(2)
  arrayv.arrayManager.setShuffleSingle(Shuffles.RANDOM) // 4.5
  arrayv.getArrayFrame().setUniqueSlider(16)
  runSorts("Stability Test")

  arrayv.setComparator(0)
  arrayv.getArrayFrame().setUniqueSlider(arrayv.getCurrentLength()) // 5
  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUFFLED_TAIL_INDEXSORT)
  runSorts("Scrambled Tail")

  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUFFLED_HEAD_INDEXSORT) // 6
  runSorts("Scrambled Head")

  arrayv.arrayManager.setShuffleSingle(Shuffles.FINAL_MERGE) // 7
  runSorts("Final Merge")

  arrayv.arrayManager.setShuffleSingle(Shuffles.SAWTOOTH) // 8
  runSorts("Sawtooth Input")

  arrayv.arrayManager.setShuffleSingle(Shuffles.HALF_ROTATION) // 9
  runSorts("Final Merge of Reversed Array")

  arrayv.arrayManager.setShuffleSingle(Shuffles.FINAL_MERGE).addSingle(Shuffles.REVERSE) // 10
  runSorts("Reversed Final Merge")

  arrayv.arrayManager.setShuffleSingle(Shuffles.ORGAN) // 11
  runSorts("Pipe Organ")

  arrayv.arrayManager.setShuffleSingle(Shuffles.FINAL_RADIX) // 12
  runSorts("Final Radix Pass")

  arrayv.arrayManager.setShuffleSingle(Shuffles.PAIRWISE) // 13
  runSorts("Final Pairwise Pass")

  arrayv.arrayManager.setShuffleSingle(Shuffles.BST_TRAVERSAL) // 14
  runSorts("Binary Search Tree")

  arrayv.arrayManager.setShuffleSingle(Shuffles.HEAPIFIED) // 15
  runSorts("Heap")

  arrayv.arrayManager.setShuffleSingle(Shuffles.REVERSE).addSingle(Shuffles.SMOOTH) // 16
  runSorts("Smooth Heap")

  arrayv.arrayManager.setShuffleSingle(Shuffles.REVERSE).addSingle(Shuffles.POPLAR) // 17
  runSorts("Poplar Heap")

  arrayv.arrayManager.setShuffleSingle(Shuffles.PARTIAL_REVERSE) // 18
  runSorts("Half-Reversed Input")

  arrayv.arrayManager.setShuffleSingle(Shuffles.DOUBLE_LAYERED) // 19
  runSorts("Evens Reversed, Odds In-Order")

  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUFFLED_ODDS) // 20
  runSorts("Evens In-Order, Scrambled Odds")

  arrayv.arrayManager.setShuffleSingle(Shuffles.INTERLACED) // 21
  runSorts("Evens Ascending, Odds Descending")

  arrayv.arrayManager.setShuffleSingle(Distributions.BELL_CURVE) // 22
  runSorts("Bell Curve")

  arrayv.arrayManager.setShuffleSingle(Distributions.PERLIN_NOISE_CURVE) // 23
  runSorts("Perlin Noise Curve")

  arrayv.arrayManager.setShuffleSingle(Distributions.PERLIN_NOISE) // 24
  runSorts("Perlin Noise Curve")

  arrayv.arrayManager.setShuffleSingle(Shuffles.TRIANGULAR) // 25
  runSorts("Triangular Input")

  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUFFLED_HALF_BACK); // 26
  runSorts("Scrambled Second Half");

  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUFFLED_HALF_FRONT); // 27
  runSorts("Scrambled First Half");

  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUFFLED_ENDS); // 28
  runSorts("Both Sides Scrambled");

  arrayv.arrayManager.setShuffleSingle(Shuffles.NOISY); // 29
  runSorts("Noisy Input");

  arrayv.arrayManager.setShuffleSingle(Shuffles.PARTITIONED); // 30
  runSorts("Partitioned");

  arrayv.arrayManager.setShuffleSingle(Shuffles.SAWTOOTH).addSingle(Shuffles.REVERSE); // 31
  runSorts("Reversed Sawtooth");

  arrayv.arrayManager.setShuffleSingle(Shuffles.FINAL_BITONIC); // 32
  runSorts("Final Bitonic Pass");

  arrayv.arrayManager.setShuffleSingle(Shuffles.DOUBLE_LAYERED).addSingle(Shuffles.HALF_ROTATION); // 33
  runSorts("Diamond");

  arrayv.arrayManager.setShuffleSingle(Shuffles.INV_BST); // 34
  runSorts("Inverted Binary Search Tree");

  arrayv.arrayManager.setShuffleSingle(Shuffles.REC_RADIX); // 35
  runSorts("Recursive Final Radix");

  arrayv.arrayManager.setShuffleSingle(Shuffles.REC_REV); // 36
  runSorts("Recursive Reversal");

  arrayv.arrayManager.setShuffleSingle(Shuffles.TRI_HEAP).setSleepRatio(1.5); // 37
  runSorts("Triangular Heap");

  arrayv.arrayManager.setShuffleSingle(Shuffles.BIT_REVERSE); // 38
  runSorts("Bit Reversed");

  arrayv.arrayManager.setShuffleSingle(Shuffles.BLOCK_RANDOMLY); // 39
  runSorts("Block Shuffled");

  arrayv.arrayManager.setShuffleSingle(Shuffles.BLOCK_REVERSE); // 40
  runSorts("Block Reversed");

  arrayv.arrayManager.setShuffleSingle(Shuffles.QSORT_BAD); // 41
  runSorts("Quicksort Killer");

  arrayv.arrayManager.setShuffleSingle(Shuffles.PDQ_BAD); // 42
  runSorts("PDQ Killer");

  arrayv.arrayManager.setShuffleSingle(Shuffles.GRAIL_BAD); // 43
  runSorts("Grailsort Killer");

  arrayv.arrayManager.setShuffleSingle(Shuffles.SHUF_MERGE_BAD); // 44
  runSorts("Shuffle Merge Killer");

  arrayv.arrayManager.setShuffleSingle(Shuffles.CIRCLE); // 45
  runSorts("Circle Pass");

  arrayv.arrayManager.setShuffleSingle(Shuffles.SORTED); // 46
  runSorts("Already Sorted");

  arrayv.arrayManager.setShuffleSingle(Shuffles.WEAVE); // 47
  runSorts("Final Weave Pass");

  arrayv.arrayManager.setShuffleSingle(Shuffles.RANDOM_ROTATION); // 48
  runSorts("Random Rotation");

  arrayv.arrayManager.setShuffleSingle(Shuffles.MODULO); // 49
  runSorts("Modulo");

  arrayv.arrayManager.setShuffleSingle(Shuffles.XORSWAP); // 50
  runSorts("XOR Swap");

  arrayv.arrayManager.setShuffleSingle(Shuffles.FINAL_MERGE).addSingle(Shuffles.PARTIAL_REVERSE_ALT); // 51
  runSorts("Penultimate Bitonic Pass");

  arrayv.arrayManager.setShuffleSingle(Shuffles.GRAY_CODE); // 52
  runSorts("Gray Code Fractal");

  arrayv.arrayManager.setShuffleSingle(Shuffles.PARTITIONED).addSingle(Shuffles.FINAL_RADIX); // 53
  runSorts("Weaved Partition");

  arrayv.arrayManager.setShuffleSingle(Shuffles.PRIMES_REVERSED); // 54
  runSorts("Primes Reversed");

  arrayv.arrayManager.setShuffleSingle(Shuffles.BST_PREORDER); // 55
  runSorts("Pre-order BST Traversal");

  arrayv.arrayManager.setShuffleSingle(Shuffles.BST_POSTORDER); // 56
  runSorts("Post-order BST Traversal");

  arrayv.arrayManager.setShuffleSingle(Shuffles.RBST_BREADTH); // 57
  runSorts("Breadth RBST Traversal");

  arrayv.arrayManager.setShuffleSingle(Shuffles.ONLY_RUNS); // 58
  runSorts("Random Runs");

  arrayv.arrayManager.setShuffleSingle(Shuffles.LOG_SLOPES); // 59
  runSorts("Logpile");

  arrayv.arrayManager.setShuffleSingle(Distributions.RANDOM); // 60
  runSorts("White Noise");

  arrayv.arrayManager.setShuffleSingle(Distributions.MODULO); // 61
  runSorts("Modulo Function");

  arrayv.arrayManager.setShuffleSingle(Distributions.CUBIC).addSingle(Shuffles.RANDOM).setSleepRatio(2); // 62
  runSorts("Shuffled Cubic");

  arrayv.arrayManager.setShuffleSingle(Distributions.QUINTIC).addSingle(Shuffles.RANDOM).setSleepRatio(2); // 63
  runSorts("Shuffled Quintic");

  arrayv.arrayManager.setShuffleSingle(Distributions.SINE); // 64
  runSorts("Sine Wawe");

  arrayv.arrayManager.setShuffleSingle(Distributions.COSINE); // 65
  runSorts("Cosine Wawe");

  arrayv.arrayManager.setShuffleSingle(Distributions.SIMILAR).addSingle(Shuffles.RANDOM).setSleepRatio(2); // 66
  runSorts("Few Unique");

  arrayv.arrayManager.setShuffleSingle(Distributions.EQUAL); // 67
  runSorts("No Unique");

  arrayv.arrayManager.setShuffleSingle(Distributions.RULER); // 68
  runSorts("Ruler");

  arrayv.arrayManager.setShuffleSingle(Distributions.BLANCMANGE); // 69
  runSorts("Blancmange Curve");

  arrayv.arrayManager.setShuffleSingle(Distributions.CANTOR).addSingle(Shuffles.RANDOM).setSleepRatio(2); // 70
  runSorts("Shuffled Cantor Function");

  arrayv.arrayManager.setShuffleSingle(Distributions.DIVISORS); // 71
  runSorts("Sum of Divisors");

  arrayv.arrayManager.setShuffleSingle(Distributions.DIGITS_PROD); // 72
  runSorts("Product of Digits");

  arrayv.arrayManager.setShuffleSingle(Distributions.DIGITS_SUM); // 73
  runSorts("Sum of Digits");

  arrayv.arrayManager.setShuffleSingle(Distributions.TOTIENT); // 74
  runSorts("Euler Totient Function");

  arrayv.arrayManager.setShuffleSingle(Distributions.FSD); // 75
  runSorts("Fly Straight, Dammit!");

  arrayv.arrayManager.setShuffleSingle(Distributions.NOISY_UNIQUES); // 76
  runSorts("Noisy Uniques");

  arrayv.arrayManager.setShuffleSingle(Distributions.RAMP); // 77
  runSorts("Ramps");

  arrayv.arrayManager.setShuffleSingle(Distributions.REVLOG); // 78
  runSorts("Decreasing Random");

  arrayv.arrayManager.setShuffleSingle(Distributions.INCREASING_RANDOM); // 79
  runSorts("Increasing Random");

  arrayv.arrayManager.setShuffleSingle(Distributions.WEIERSTRASS); // 80
  runSorts("Weierstrass Function");

  setCategory("In-Place Weave Safe Stalin Merge Sort")
}
