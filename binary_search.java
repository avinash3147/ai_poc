public class BinarySearch {

    // Function to perform binary search
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Calculate mid to prevent overflow

            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid; // Target found, return its index
            }

            // If target is greater, ignore left half
            if (arr[mid] < target) {
                low = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                high = mid - 1;
            }
        }

        // Target not found
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target1 = 23;
        int target2 = 7;

        int result1 = binarySearch(arr, target1);
        if (result1 != -1) {
            System.out.println("Target " + target1 + " found at index: " + result1);
        } else {
            System.out.println("Target " + target1 + " not found in the array.");
        }

        int result2 = binarySearch(arr, target2);
        if (result2 != -1) {
            System.out.println("Target " + target2 + " found at index: " + result2);
        } else {
            System.out.println("Target " + target2 + " not found in the array.");
        }
    }
}

/*
Explanation for Binary Search:

Binary search is an efficient algorithm for finding an item from a *sorted* list of items. It works by repeatedly dividing the search interval in half.

1.  Initialization:
    *   `low`: Points to the first element's index (0).
    *   `high`: Points to the last element's index (`arr.length - 1`).
    *   The search space is initially the entire array `[low...high]`.

2.  Loop Condition (`while (low <= high)`): The loop continues as long as `low` is less than or equal to `high`. This means there is still a valid search space. If `low` becomes greater than `high`, it implies the target element is not in the array.

3.  Calculate Midpoint (`int mid = low + (high - low) / 2;`):
    *   This formula for `mid` is preferred over `(low + high) / 2` to prevent potential integer overflow if `low` and `high` are very large.

4.  Comparison:
    *   Case 1: `arr[mid] == target`: If the element at the midpoint is the target, we have found it, and its index `mid` is returned.
    *   Case 2: `arr[mid] < target`: If the element at `mid` is less than the `target`, it means the target (if it exists) must be in the *right half* of the current search space. So, we update `low = mid + 1` to search in `[mid + 1 ... high]`.
    *   Case 3: `arr[mid] > target`: If the element at `mid` is greater than the `target`, it means the target (if it exists) must be in the *left half* of the current search space. So, we update `high = mid - 1` to search in `[low ... mid - 1]`.

5.  Target Not Found: If the loop finishes (i.e., `low > high`), it means the target was not found in the array, and the function returns `-1`.

Dry Run for Binary Search (Target = 23):

Array: `arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91}`
Target: `23`

*   Initial: `low = 0`, `high = 9`

*   Iteration 1:
    *   `low <= high` (0 <= 9) is true.
    *   `mid = 0 + (9 - 0) / 2 = 4`
    *   `arr[mid]` (`arr[4]`) is `16`.
    *   `arr[mid] < target` (16 < 23) is true.
    *   `low = mid + 1 = 4 + 1 = 5`
    *   Current search space: `arr[5...9]` -> `{23, 38, 56, 72, 91}`

*   Iteration 2:
    *   `low <= high` (5 <= 9) is true.
    *   `mid = 5 + (9 - 5) / 2 = 5 + 2 = 7`
    *   `arr[mid]` (`arr[7]`) is `56`.
    *   `arr[mid] > target` (56 > 23) is true.
    *   `high = mid - 1 = 7 - 1 = 6`
    *   Current search space: `arr[5...6]` -> `{23, 38}`

*   Iteration 3:
    *   `low <= high` (5 <= 6) is true.
    *   `mid = 5 + (6 - 5) / 2 = 5 + 0 = 5`
    *   `arr[mid]` (`arr[5]`) is `23`.
    *   `arr[mid] == target` (23 == 23) is true.
    *   Return `mid`, which is `5`.

Result: Target 23 found at index 5.
*/