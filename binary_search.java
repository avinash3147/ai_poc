public class BinarySearch {

    // Function to perform binary search
    public static int binarySearch(int[] arr, int target) {
        // Handle empty or null array case
        if (arr == null || arr.length == 0) {
            return -1;
        }

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
        int[] arr1 = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target1 = 23;
        int target2 = 7;
        int target3 = 2;  // Target at the beginning
        int target4 = 91; // Target at the end
        int target5 = 12; // Target in the middle

        System.out.println("--- Test Cases for arr1 ---");
        testBinarySearch(arr1, target1);
        testBinarySearch(arr1, target2);
        testBinarySearch(arr1, target3);
        testBinarySearch(arr1, target4);
        testBinarySearch(arr1, target5);

        int[] arr2 = {10}; // Single element array
        int target6 = 10;
        int target7 = 5; // Not present in single element array

        System.out.println("\n--- Test Cases for arr2 (single element) ---");
        testBinarySearch(arr2, target6);
        testBinarySearch(arr2, target7);

        int[] arr3 = {}; // Empty array
        int target8 = 5;

        System.out.println("\n--- Test Cases for arr3 (empty array) ---");
        testBinarySearch(arr3, target8);

        int[] arr4 = null; // Null array
        int target9 = 5;

        System.out.println("\n--- Test Cases for arr4 (null array) ---");
        testBinarySearch(arr4, target9);
    }

    // Helper method to run and print test results
    public static void testBinarySearch(int[] arr, int target) {
        int result = binarySearch(arr, target);
        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}

/*
Explanation for Binary Search:

Binary search is an efficient algorithm for finding an item from a *sorted* list of items. It works by repeatedly dividing the search interval in half. This approach drastically reduces the number of comparisons needed, especially for large datasets.

Key Principles:
1.  **Sorted Data**: Binary search *requires* the input array to be sorted in ascending order. If the array is not sorted, the algorithm will not work correctly.
2.  **Divide and Conquer**: In each step, the algorithm divides the search interval into two halves. It eliminates one half based on the comparison of the target value with the middle element of the interval.

Algorithm Steps:

1.  **Handle Edge Cases (Initial Check)**:
    *   `if (arr == null || arr.length == 0)`: Before starting the main search, it's crucial to check if the input array is `null` or empty. If so, the target cannot be found, and `-1` is returned immediately. This prevents `NullPointerException` or `ArrayIndexOutOfBoundsException`.

2.  **Initialization**:
    *   `low = 0`: This pointer marks the beginning of the current search space (inclusive).
    *   `high = arr.length - 1`: This pointer marks the end of the current search space (inclusive).
    *   Initially, the search space covers the entire array: `[low...high]`.

3.  **Iteration (The `while` loop)**:
    *   `while (low <= high)`: The loop continues as long as `low` is less than or equal to `high`. This condition ensures there is still a valid (non-empty) search space to consider. If `low` becomes greater than `high`, it means the search space has become empty, and the target element is not present in the array.

4.  **Calculate Midpoint**:
    *   `int mid = low + (high - low) / 2;`:
        *   This formula is used to calculate the middle index of the current search space.
        *   It's generally preferred over `(low + high) / 2` to prevent potential **integer overflow**. If `low` and `high` are both very large positive integers, their sum `(low + high)` might exceed the maximum value an `int` can hold, leading to an incorrect `mid` value. `low + (high - low) / 2` calculates the difference first, which is smaller, and then adds it to `low`, thus avoiding overflow.

5.  **Comparison and Adjustment**:
    *   **Case A: Target Found** (`if (arr[mid] == target)`):
        *   If the element at the `mid` index is equal to the `target`, the element has been found. The function returns `mid`, which is the index of the target.
    *   **Case B: Target is in the Right Half** (`if (arr[mid] < target)`):
        *   If the element at `mid` is *less than* the `target`, it means the `target` (if it exists) must lie in the elements *after* `mid`. Therefore, we eliminate the left half (including `mid`) and update `low = mid + 1` to search in the new right half `[mid + 1 ... high]`.
    *   **Case C: Target is in the Left Half** (`else { high = mid - 1; }`):
        *   If the element at `mid` is *greater than* the `target`, it means the `target` (if it exists) must lie in the elements *before* `mid`. Therefore, we eliminate the right half (including `mid`) and update `high = mid - 1` to search in the new left half `[low ... mid - 1]`.

6.  **Target Not Found (Post-loop)**:
    *   If the `while` loop terminates, it means `low` has become greater than `high`. This signifies that the target element was not found within any of the repeatedly narrowed search spaces. In this case, the function returns `-1` to indicate that the target is not present in the array.

Dry Run Example 1: Target = 23 in `arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91}`

*   **Initial**: `low = 0`, `high = 9`, `target = 23`
    *   Array: `[2, 5, 8, 12, 16, 23, 38, 56, 72, 91]`

*   **Iteration 1** (`low <= high` is true: `0 <= 9`):
    *   `mid = 0 + (9 - 0) / 2 = 4`
    *   `arr[mid]` (`arr[4]`) = `16`
    *   `16 < 23` (true) -> Target is in the right half.
    *   `low = mid + 1 = 4 + 1 = 5`
    *   Search Space: `[5...9]` -> `{23, 38, 56, 72, 91}`

*   **Iteration 2** (`low <= high` is true: `5 <= 9`):
    *   `mid = 5 + (9 - 5) / 2 = 7`
    *   `arr[mid]` (`arr[7]`) = `56`
    *   `56 > 23` (true) -> Target is in the left half.
    *   `high = mid - 1 = 7 - 1 = 6`
    *   Search Space: `[5...6]` -> `{23, 38}`

*   **Iteration 3** (`low <= high` is true: `5 <= 6`):
    *   `mid = 5 + (6 - 5) / 2 = 5`
    *   `arr[mid]` (`arr[5]`) = `23`
    *   `23 == 23` (true) -> Target found!
    *   **Return `mid` (which is 5)**

Dry Run Example 2: Target = 7 (Not Present) in `arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91}`

*   **Initial**: `low = 0`, `high = 9`, `target = 7`
    *   Array: `[2, 5, 8, 12, 16, 23, 38, 56, 72, 91]`

*   **Iteration 1** (`low <= high` is true: `0 <= 9`):
    *   `mid = 4`, `arr[mid] = 16`
    *   `16 > 7` -> Target is in the left half.
    *   `high = 3`
    *   Search Space: `[0...3]` -> `{2, 5, 8, 12}`

*   **Iteration 2** (`low <= high` is true: `0 <= 3`):
    *   `mid = 0 + (3 - 0) / 2 = 1`
    *   `arr[mid]` (`arr[1]`) = `5`
    *   `5 < 7` -> Target is in the right half.
    *   `low = 2`
    *   Search Space: `[2...3]` -> `{8, 12}`

*   **Iteration 3** (`low <= high` is true: `2 <= 3`):
    *   `mid = 2 + (3 - 2) / 2 = 2`
    *   `arr[mid]` (`arr[2]`) = `8`
    *   `8 > 7` -> Target is in the left half.
    *   `high = 1`
    *   Search Space: `[2...1]` (empty)

*   **Iteration 4** (`low <= high` is false: `2 <= 1` is false):
    *   Loop terminates.
    *   **Return `-1`**

Dry Run Example 3: Target = 10 (Single Element Array) in `arr = {10}`

*   **Initial**: `low = 0`, `high = 0`, `target = 10`
    *   Array: `[10]`

*   **Iteration 1** (`low <= high` is true: `0 <= 0`):
    *   `mid = 0 + (0 - 0) / 2 = 0`
    *   `arr[mid]` (`arr[0]`) = `10`
    *   `10 == 10` (true) -> Target found!
    *   **Return `mid` (which is 0)**

Considerations for Interview Questions:
*   **Recursive vs. Iterative**: Binary search can be implemented both iteratively (as shown above) and recursively. The iterative approach is often preferred to avoid potential stack overflow issues with very large arrays.
*   **Edge Cases**: Always consider arrays with one element, zero elements, and targets at the boundaries (first or last element).
*   **Time Complexity**: O(log n) because the search space is halved in each step.
*   **Space Complexity**: O(1) for the iterative approach (constant extra space).

*/