public static int minimum_steps(int[] array) {
    int[] min_to_end = new int[array.length];
    for (int i = array.length - 2; i >= 0; --i) {
        if (array[i] <= 0)
            min_to_end[i] = Integer.MAX_VALUE;
        else {
            int minimum = Integer.MAX_VALUE;
            for (int k = 1; k <= array[i]; ++k) {
                if (i + k < array.length)
                    minimum = Math.min(min_to_end[i+k], minimum);
                else
                    break;
            }
            min_to_end[i] = minimum + 1;
        }
    }
    return min_to_end[0];
} 
