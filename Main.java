import java.util.*;

public class Main {

    // 1 Find missing number from 1 to N -> 1,2,3,5,6 answer 4
    static int missingNumber(int[] arr, int n) {
        int total = n * (n + 1) / 2;
        int sum = 0;
        for (int num : arr)
            sum += num;
        return total - sum;
    }

    // 2 Rotate an array by K positions
    static void rotate(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }

    static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    // 3 Move all zeroes to the end
    static void moveZeroes(int[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }

    // 4 Maximum subarray sum
    static int maxSubArray(int[] arr) {
        int curr = arr[0], max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            max = Math.max(max, curr);
        }
        return max;
    }

    // 5 Two sum
    static int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];
            if (map.containsKey(diff)) {
                return new int[] { map.get(diff), i };
            }
            map.put(arr[i], i);
        }
        return new int[] { -1, -1 };
    }

    // 6 Find all duplicates in an array
    static List<Integer> findDuplicates(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> dup = new HashSet<>();
        for (int num : arr) {
            if (!seen.add(num))
                dup.add(num);
        }
        return new ArrayList<>(dup);
    }

    // 7 Find the majority element n/3
    static List<Integer> majorityElement(int[] nums) {
        int c1 = 0, c2 = 0, v1 = 0, v2 = 0;

        for (int num : nums) {
            if (num == c1)
                v1++;
            else if (num == c2)
                v2++;
            else if (v1 == 0) {
                c1 = num;
                v1 = 1;
            } else if (v2 == 0) {
                c2 = num;
                v2 = 1;
            } else {
                v1--;
                v2--;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int x : new int[] { c1, c2 }) {
            int count = 0;
            for (int num : nums)
                if (num == x)
                    count++;
            if (count > nums.length / 3)
                res.add(x);
        }
        return res;
    }

    // 8 Sort and array 0s 1s 2s
    static void sortColors(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 0)
                swap(arr, low++, mid++);
            else if (arr[mid] == 1)
                mid++;
            else
                swap(arr, mid, high--);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 9 Best time to sell and buy stock
    static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            min = Math.min(min, price);
            profit = Math.max(profit, price - min);
        }
        return profit;
    }

    // 10 Trapping rainwater
    static int trap(int[] h) {
        int l = 0, r = h.length - 1;
        int lmax = 0, rmax = 0, water = 0;

        while (l < r) {
            if (h[l] < h[r]) {
                if (h[l] >= lmax)
                    lmax = h[l];
                else
                    water += lmax - h[l];
                l++;
            } else {
                if (h[r] >= rmax)
                    rmax = h[r];
                else
                    water += rmax - h[r];
                r--;
            }
        }
        return water;
    }

    // 11 Maximum product subarray negatives and 0s
    static int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    // 12 Find all triplets with 0 sum
    static List<List<Integer>> threeSum(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1])
                continue;

            int l = i + 1, r = arr.length - 1;

            while (l < r) {
                int sum = arr[i] + arr[l] + arr[r];

                if (sum == 0) {
                    res.add(Arrays.asList(arr[i], arr[l], arr[r]));
                    l++;
                    r--;
                } else if (sum < 0)
                    l++;
                else
                    r--;
            }
        }
        return res;
    }

    // 13 Count occurrences of an element in a sorted array
    static int countOccurrences(int[] arr, int x) {
        int count = 0;
        for (int num : arr)
            if (num == x)
                count++;
        return count;
    }

    // 14 Merge two sorted arrays without extra space
    static void merge(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[0]) {
                int temp = a[i];
                a[i] = b[0];
                b[0] = temp;
                Arrays.sort(b);
            }
        }
    }

    // MAIN
    public static void main(String[] args) {

        // 1 Missing
        int[] arr1 = { 1, 2, 3, 5, 6 };
        System.out.println("Missing: " + missingNumber(arr1, 6));

        // 2 Rotate
        int[] arr2 = { 1, 2, 3, 4, 5 };
        rotate(arr2, 2);
        System.out.println("Rotate: " + Arrays.toString(arr2));

        // 3 Move Zeroes
        int[] arr3 = { 0, 1, 0, 3, 12 };
        moveZeroes(arr3);
        System.out.println("Zeroes: " + Arrays.toString(arr3));

        // 4 Max Subarray
        int[] arr4 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println("Max Sum: " + maxSubArray(arr4));

        // 5 Two Sum
        int[] arr5 = { 2, 7, 11, 15 };
        System.out.println("Two Sum: " + Arrays.toString(twoSum(arr5, 9)));

        // 6 Duplicates
        int[] arr6 = { 1, 2, 3, 1, 2, 4 };
        System.out.println("Duplicates: " + findDuplicates(arr6));

        // 7 Majority
        int[] arr7 = { 1, 2, 3, 1, 2, 1, 2 };
        System.out.println("Majority: " + majorityElement(arr7));

        // 8 Sort 0 1 2
        int[] arr8 = { 2, 0, 2, 1, 1, 0 };
        sortColors(arr8);
        System.out.println("Sorted: " + Arrays.toString(arr8));

        // 9 Stock
        int[] arr9 = { 7, 1, 5, 3, 6, 4 };
        System.out.println("Profit: " + maxProfit(arr9));

        // 10 Rainwater
        int[] arr10 = { 0, 1, 0, 2, 1, 0, 1, 3 };
        System.out.println("Water: " + trap(arr10));

        // 11 Max Product
        int[] arr11 = { 2, 3, -2, 4 };
        System.out.println("Max Product: " + maxProduct(arr11));

        // 12 Three Sum
        int[] arr12 = { -1, 0, 1, 2, -1, -4 };
        System.out.println("Triplets: " + threeSum(arr12));

        // 13 Count
        int[] arr13 = { 1, 2, 2, 2, 3 };
        System.out.println("Count: " + countOccurrences(arr13, 2));

        // 14 Merge
        int[] a = { 1, 3, 5 };
        int[] b = { 2, 4, 6 };
        merge(a, b);
        System.out.println("Merged: " + Arrays.toString(a) + Arrays.toString(b));
    }
}