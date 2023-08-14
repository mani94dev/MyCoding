package com.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class practice {

  private HashMap<Object, Object> MorseCode;

  // Arjun gave the problem
  public void printNumbersInBetween(int[] arr) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    Set<Integer> inputList = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < min) {
        min = arr[i];
      }
      if (arr[i] > max) {
        max = arr[i];
      }
      if (!inputList.contains(arr[i])) {
        inputList.add(arr[i]);
      }
    }
    for (int i = min; i <= max; i++) {
      if (!inputList.contains(i)) {
        System.out.println("Its in the output : " + i);
      }
    }
  }

  /*
   * We need to find the length of longest substring
   * without repeating characters
   * Approach is using sliding window and a set.
   * */
  public int lengthOfLongestSubstring(String s) {
    int n = s.length();
    Set<Character> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    while (i < n && j < n) {
      if (!set.contains(s.charAt(j))) {
        set.add(s.charAt(j++));
        ans = Math.max(ans, j - i);
      } else {
        set.remove(s.charAt(i++));
      }
    }
    return ans;
  }


  /*
   * Given an array of integers nums and an integer target,
   * return indices of the two numbers such that they add up to target.
   * */
  public int[] twoSum(int[] nums, int target) {
    int[] output = new int[2];
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          output[0] = i;
          output[1] = j;
          return output;
        }
      }
    }
    return output;
  }

  /*
   * You are given two non-empty linked lists representing
   * two non-negative integers. The digits are stored in reverse order,
   * and each of their nodes contains a single digit.
   * Add the two numbers and return the sum as a linked list.
   */

  public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    int sum = 0;
    ListNode dummy = new ListNode(0);
    ListNode temp = dummy;
    while (l1 != null || l2 != null) {
      int num1 = l1 != null ? l1.val : 0;
      int num2 = l2 != null ? l2.val : 0;
      sum = num1 + num2 + carry;
      carry = sum / 10;
      ListNode newNode = new ListNode(sum % 10);
      temp.next = newNode;
      temp = temp.next;
      l1 = l1 != null ? l1.next : null;
      l2 = l2 != null ? l2.next : null;
    }
    if (carry > 0) {
      ListNode newNode = new ListNode(carry);
      temp.next = newNode;
    }
    return dummy.next;
  }


  /*
   * Given two sorted arrays nums1 and nums2 of size m and n respectively,
   *  return the median of the two sorted arrays.
   * The overall run time complexity should be O(log (m+n)).
   * Approach : Combining both into one and sorting and finding median
   * */

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len1 = nums1.length;
    int len2 = nums2.length;
    int combinedLength = len1 + len2;
    int[] newArr = new int[combinedLength];
    int j = 0;
    for (int i = 0; i < nums1.length; i++) {
      newArr[j++] = nums1[i];
    }
    for (int i = 0; i < nums2.length; i++) {
      newArr[j++] = nums2[i];
    }
    Arrays.sort(newArr);
    if (combinedLength % 2 == 0) {
      return 0.5 * (newArr[combinedLength / 2] + newArr[combinedLength / 2 - 1]);
    } else {
      return newArr[combinedLength / 2];
    }
  }


  /*
  * Given a string s, return the longest palindromic substring in s.
A string is called a palindrome string if the reverse of
*  that string is the same as the original string.
* Solution 1 : Straight forward to see if each substring is a
*  palindrome or nor.  Complexity : n^3 Space : 1
* Solution 2 : Same approach but now we will store if a
*  substring is palindrom or not.Using dp here. Comple : n^2 Space : n^2
  * */
  public static String longestPalindrome(String s) {
    int size = s.length();
    int max_len = 0;
    int starting_index = 0;
    int[][] dp = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        dp[i][j] = -1;
      }
    }
    for (int i = 0; i < size; i++) {
      for (int j = i; j < size; j++) {
        checkPal(s, i, j, dp);
        if (dp[i][j] == 1) {
          if (j - i + 1 > max_len) {
            max_len = j - i + 1;
            starting_index = i;
          }
        }
      }
    }
    return s.substring(starting_index, starting_index + max_len);
  }

  private static int checkPal(String s, int i, int j, int[][] dp) {
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    dp[i][j] = 0;
    if (i == j) {
      return dp[i][j] = 1;
    }
    if (j - i == 1) {
      if (s.charAt(i) == s.charAt(j)) {
        return dp[i][j] = 1;
      } else {
        return dp[i][j];
      }
    }
    if ((s.charAt(i) == s.charAt(j) && checkPal(s, i + 1, j - 1, dp) == 1)) {
      return dp[i][j] = 1;
    }
    return dp[i][j];
  }

  /**
   * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
   *
   * P   A   H   N
   * A P L S I I G
   * Y   I   R
   * And then read line by line: "PAHNAPLSIIGYIR"
   *
   * Write the code that will take a string and make this conversion given a number of rows:
   *
   * string convert(string s, int numRows);
   **/
  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }
    HashMap<Integer, LinkedList<Character>> map = new HashMap<>();
    boolean direction = true;
    int bucket = 1;
    for (int i = 0; i < s.length(); i++) {
      if (bucket == 1) {
        direction = true;
      } else if (bucket == numRows) {
        direction = false;
      }
      if (direction) {
        if (map.get(bucket) == null) {
          LinkedList<Character> myList = new LinkedList<>();
          myList.add(s.charAt(i));
          map.put(bucket, myList);
        } else {
          LinkedList<Character> characters = map.get(bucket);
          characters.add(s.charAt(i));
        }
        bucket++;
      } else {
        if (map.get(bucket) == null) {
          LinkedList<Character> myList = new LinkedList<>();
          myList.add(s.charAt(i));
          map.put(bucket, myList);
        } else {
          LinkedList<Character> characters = map.get(bucket);
          characters.add(s.charAt(i));
        }
        bucket--;
      }
    }
    StringBuilder output = new StringBuilder();

    for (int i = 1; i <= numRows; i++) {
      LinkedList<Character> characters = map.get(i);
      System.out.println(characters.stream().collect(Collectors.toList()));
      if (!characters.isEmpty()) {
        Iterator<Character> iterator = characters.iterator();
        while (iterator.hasNext()) {
          Character next = iterator.next();
          output.append(next);
        }
      }
    }
    return output.toString();
  }

  public int firstMissingPositive(int[] nums) {
    int length = nums.length;
    for (int i = 0; i < length; i++) {
      if (nums[i] >= 1 && nums[i] < length) {
        int val = nums[i];
        if (nums[val] < 0) {
          continue;
        }
        nums[val] = nums[val] * -1;
      }
    }
    for (int i = 0; i < length; i++) {
      if (nums[i] < 0) {
        return i;
      }
    }
    return length;
  }

//  public static void main(String[] args) {
//    System.out.println(longestPalindrome("palinilap"));
//  }

//  public class Java8ForEachExample {
//
//    public static void main(String[] args) {
//
//      //creating sample Collection
//      List<Integer> myList = new ArrayList<Integer>();
//      for (int i = 0; i < 10; i++) {
//        myList.add(i);
//      }
//
//      //traversing using Iterator
//      Iterator<Integer> it = myList.iterator();
//      while (it.hasNext()) {
//        Integer i = it.next();
//        System.out.println("Iterator Value::" + i);
//      }
//
//      //traversing through forEach method of Iterable with anonymous class
//      myList.forEach(new Consumer<Integer>() {
//
//        public void accept(Integer t) {
//          System.out.println("forEach anonymous class Value::" + t);
//        }
//
//      });
//
//    }
//
//    public static void loopMapJava8() {
//
//      Map<String, Integer> map = new HashMap<>();
//      map.put("A", 10);
//      map.put("B", 20);
//      map.put("C", 30);
//      map.put("D", 40);
//      map.put("E", 50);
//      map.put("F", 60);
//
//      // lambda
//      map.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
//
//    }
//
//
//    public static String[] uniqueElements(String[] strs) {
//      Map<String, Integer> map = new HashMap<>();
//      for (int i = 0; i < strs.length; i++) {
//        if (map.containsKey(strs[i])) {
//          Integer integer = map.get(strs[i]);
//          map.put(strs[i], integer++);
//        } else {
//          map.put(strs[i], 1);
//        }
//      }
//      String[] arr = new String[strs.length];
//      int i = 0;
//      for (Map.Entry<String, Integer> entry : map.entrySet()) {
//        if (entry.getValue() == 1) {
//          arr[i++] = entry.getKey();
//        }
//      }
//      return arr;
//
//    }
//
//  }

//  public static void main(String[] args) {
//    List<String> words = new ArrayList<>();
//    words.add("EAT");
//    words.add("ATE");
//    words.add("TEA");
//    words.add("CAT");
//    words.add("RAT");
//    List<String> strings = groupTogether(words);
//    for (int i = 0; i < strings.size(); i++) {
//      System.out.println(strings.get(i));
//    }
//  }

  /*     Group together the words which are of the same characters – EAT, ATE, TEA, CAT, RAT. */
  public static List<String> groupTogether(List<String> words) {
    List<String> output = new ArrayList<>();
    if (words == null || words.isEmpty()) {
      return output;
    }
    String input = words.get(0).toLowerCase();
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < input.length(); i++) {
      int val = input.charAt(i) - 'a';
      if (map.containsKey(val)) {
        Integer count = map.get(val);
        map.put(val, count++);
      } else {
        map.put(val, 1);
      }
    }

    for (int i = 1; i < words.size(); i++) {
      String word = words.get(i).toLowerCase();
      Map<Integer, Integer> localMap = new HashMap<>();
      for (int j = 0; j < word.length(); j++) {
        int val = word.charAt(j) - 'a';
        if (localMap.containsKey(val)) {
          Integer count = localMap.get(val);
          localMap.put(val, count++);
        } else {
          localMap.put(val, 1);
        }
      }
      if (map.equals(localMap)) {
        output.add(word);
      }
    }
    return output;
  }

  public int myAtoi(String s) {
    String trimmedString = s.trim();
    char sign = 0;
    if (trimmedString.length() > 0) {
      sign = trimmedString.charAt(0);
    }
    int i = 0;
    if (sign == '+' || sign == '-') {
      i++;
    }
    boolean isNeg = false;
    if (sign == '-') {
      isNeg = true;
    }
    int ans = 0;
    while (i < trimmedString.length() && trimmedString.charAt(i) >= '0'
        && trimmedString.charAt(i) <= '9') {
      int num = trimmedString.charAt(i) - '0';
      if ((isNeg && (ans > -(Integer.MIN_VALUE / 10)) || (ans == -(Integer.MIN_VALUE / 10)
          && num > 8))) {
        return Integer.MIN_VALUE;
      }
      if ((!isNeg && (ans > Integer.MAX_VALUE / 10)) || (ans == Integer.MAX_VALUE / 10
          && num > 7)) {
        return Integer.MAX_VALUE;
      }
      ans = ans * 10 + num;
      i++;
    }
    return isNeg ? -1 * ans : ans;
  }

  public int removeElement(int[] nums, int val) {
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == val) {
        continue;
      } else {
        nums[j] = nums[i];
        j++;
      }
    }
    return j - 1;
  }

  public int strStr(String haystack, String needle) {
    if (needle.length() > haystack.length()) {
      return -1;
    }
    if (needle.equalsIgnoreCase(haystack)) {
      return 0;
    }
    for (int i = 0, k = 0; i < haystack.length() && k <= haystack.length() - needle.length(); i++) {
      for (int j = 0; j < needle.length(); j++) {
        if (haystack.charAt(k) == needle.charAt(j)) {
          k++;
        } else {
          break;
        }
      }
      if (k - i == needle.length()) {
        return i;
      }
      k = i + 1;
    }
    return -1;
  }

  public static int searchInsert(int[] nums, int target) {
    int len = nums.length;
    return searchBinary(nums, 0, len - 1, target);
  }

  private static int searchBinary(int[] nums, int start, int end, int target) {
    int mid;
    while (start <= end) {
      mid = (end - start) / 2 + start;
      if (nums[mid] > target) {
        return searchBinary(nums, start, mid - 1, target);
      } else if (nums[mid] < target) {
        return searchBinary(nums, mid + 1, end, target);
      } else {
        return mid;
      }
    }
    return start;
  }

//  public static void main(String[] args) {
//    int arr[] = {2, 3, 5, 6};
//    System.out.println(searchInsert(arr, -10));
//  }

  int firstBadVersion(int n) {
    int startV = 1;
    int endV = n;
    int midV = startV + (endV - startV) / 2;
    while (startV <= endV) {
      midV = startV + (endV - startV) / 2;
      if (isBadVersion(midV)) {
        if ((midV - 1 >= 1 && !isBadVersion(midV - 1)) || midV == 1) {
          return midV;
        } else {
          endV = midV - 1;
        }
      } else {
        startV = midV + 1;
      }
    }
    return -1;
  }

  private boolean isBadVersion(int midV) {
    return false;
  }

  public boolean isValidSudoku(char[][] board) {
    if (board == null) {
      return false;
    }
    int rows = board.length, columns = board[0].length;
    for (int i = 0; i < rows; i++) {
      List<Character> list = new ArrayList();
      for (int j = 0; j < columns; j++) {
        char val = board[i][j];
        if (val != '.') {
          if (list.contains(val)) {
            return false;
          } else {
            list.add(val);
          }
        }
      }
    }
    for (int i = 0; i < columns; i++) {
      List<Character> list = new ArrayList();
      for (int j = 0; j < rows; j++) {
        char val = board[i][j];
        if (val != '.') {
          if (list.contains(val)) {
            return false;
          } else {
            list.add(val);
          }
        }
      }
    }
    for (int i = 0; i < rows; i += 3) {
      for (int j = 0; j < columns; j += 3) {
        List<Character> list = new ArrayList<>();
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            char var = board[i + k][j + l];
            if (var != '.') {
              if (list.contains(var)) {
                return false;
              } else {
                list.add(var);
              }
            }
          }
        }
      }
    }
    return true;
  }


  // add 1 to the interger obtained
  public static int[] plusOne(int[] digits) {
    int len = digits.length;
    for (int i = len - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      }
      digits[i] = 0;
    }
    int[] result = new int[len + 1];
    result[0] = 1;
    return result;
  }

//  public static void main(String[] args) {
//    int arr[] = {9,8,7,6,5,4,3,2,1,0};
//    System.out.println(plusOne(arr));
//  }


  // square root of a number without using any
  // inbuilt function rounded to the nearest integer
  public int mySqrt(int x) {
    if (x == 0) {
      return 0;
    }
    int left = 0, right = x;
    while (left < right) {
      int mid = (left + right) / 2;
      if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
        return mid;
      } else if (mid <= x / mid) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  // inorder traversal
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    printInOrder(root, result);
    return result;
  }

  private void printInOrder(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    printInOrder(root.left, result);
    result.add(root.val);
    printInOrder(root.right, result);
    return;
  }

  // is symmetric binary tree

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isSym(root.left, root.right);
  }

  private boolean isSym(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    }
    if (left == null || right == null) {
      return false;
    }
    if (left.val != right.val) {
      return false;
    }
    return isSym(left.left, right.right) && isSym(left.right, right.left);
  }

  // find the majority element
  public int majorityElement(int[] nums) {
    int count = 0;
    Integer maj = null;
    for (int i : nums) {
      if (count == 0) {
        maj = i;
      }
      count += (i == maj) ? 1 : -1;
    }
    return maj;
  }

  // move all the 0's to the end making sure the
  // non zeroes are in the same order
  public void moveZeroes(int[] nums) {
    int nonZeroNumIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        int temp = nums[i];
        nums[i] = nums[nonZeroNumIndex];
        nums[nonZeroNumIndex] = temp;
        nonZeroNumIndex++;
      }
    }
  }

  // longest common prefix between strings
  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    String minStr = strs[0];
    for (String str : strs) {
      if (str.length() < minStr.length()) {
        minStr = str;
      }
    }

    for (int i = 0; i < minStr.length(); i++) {
      for (String str : strs) {
        if (minStr.charAt(i) != str.charAt(i)) {
          return minStr.substring(0, i);
        }
      }
    }
    return minStr;
  }

  // merged two sorted linked lists
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode head = new ListNode(-1), temp = head, point1 = list1, point2 = list2;
    while (point1 != null && point2 != null) {
      int val1 = point1.val;
      int val2 = point2.val;
      if (val1 <= val2) {
        temp.next = point1;
        point1 = point1.next;
      } else {
        temp.next = point2;
        point2 = point2.next;
      }
      temp = temp.next;
    }
    if (point1 != null) {
      temp.next = point1;
    } else if (point2 != null) {
      temp.next = point2;
    }
    return head.next;
  }

  // merge two arrays and keep the result in nums1.
  // nums1 has space to fit nums2 numbers as well
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;
    while (i >= 0 && j >= 0) {
      if (nums1[i] <= nums2[j]) {
        nums1[k--] = nums2[j--];
      } else {
        nums1[k--] = nums1[i--];
      }
    }
    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }
  }

  // max depth of binary tree
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }

  // given a sorted array. Convert to BST height balanced
  public TreeNode sortedArrayToBST(int[] nums) {
    int low = 0, high = nums.length - 1;
    return getBST(nums, low, high);
  }

  private TreeNode getBST(int[] nums, int low, int high) {
    if (low > high) {
      return null;
    }
    int mid = (low + high) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    node.left = getBST(nums, low, mid - 1);
    node.right = getBST(nums, mid + 1, high);
    return node;
  }

  // pascal triange
  public List<List<Integer>> generate(int numRows) {
    int[][] matrix = new int[numRows][numRows];
    for (int i = 0; i < numRows; i++) {
      matrix[i][0] = 1;
      matrix[i][i] = 1;
    }
    for (int i = 2; i < numRows; i++) {
      for (int j = 0; j < numRows; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - 1];
        }
      }
    }
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      List<Integer> internal = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        internal.add(matrix[i][j]);
      }
      result.add(internal);
    }
    return result;
  }

  /**
   * Write an algorithm to determine if a number n is happy.
   *
   * A happy number is a number defined by the following process:
   *
   * Starting with any positive integer, replace the number by the sum of the squares of its digits.
   * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
   * Those numbers for which this process ends in 1 are happy.
   * Return true if n is a happy number, and false if not.
   **/
  public static boolean isHappy(int n) {
    Set<Integer> list = new HashSet<>();
    int temp = n;
    int sumsqares = 0;
    while (sumsqares != 1) {
      sumsqares = 0;
      while (temp > 0) {
        int k = temp % 10;
        temp = temp / 10;
        sumsqares = sumsqares + k * k;
      }
      if (list.contains(sumsqares)) {
        break;
      } else {
        list.add(sumsqares);
      }
      temp = sumsqares;
    }
    if (sumsqares == 1) {
      return true;
    }
    return false;
  }

//  public static void main(String[] args) {
//    System.out.println(isHappy(19));
//  }

  /**
   * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
   *
   * Given a string s, return true if it is a palindrome, or false otherwise.
   **/
  public boolean isPalindrome(String s) {
    char[] chars = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < chars.length; i++) {
      if (Character.isLetterOrDigit(chars[i])) {
        sb.append(chars[i]);
      }
    }
    String lowerCase = sb.toString().toLowerCase();
    int i = 0, j = lowerCase.length() - 1;
    while (i < j) {
      if (lowerCase.charAt(i++) != lowerCase.charAt(j--)) {
        return false;
      }
    }
    return true;
  }

  public boolean hasCycle(ListNode head) {
    ListNode slowPtr = head, fastPtr = head;
    while (fastPtr != null && fastPtr.next != null) {
      slowPtr = slowPtr.next;
      fastPtr = fastPtr.next.next;
      if (slowPtr == fastPtr) {
        return true;
      }
    }
    return false;
  }

  public int singleNumber(int[] nums) {
    int result = 0;
    for (int num : nums) {
      result ^= num;
    }
    return result;
  }

  public int titleToNumber(String columnTitle) {
    int result = 0;
    for (int i = 0; i < columnTitle.length(); i++) {
      int val = columnTitle.charAt(i) - 'A' + 1;
      result = result * 26 + val;
    }
    return result;
  }

  public boolean isAnagram(String s, String t) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }
    for (int i = 0; i < t.length(); i++) {
      if (map.containsKey(t.charAt(i))) {
        Integer integer = map.get(t.charAt(i));
        if (integer > 1) {
          map.put(t.charAt(i), integer.intValue() - 1);
        } else if (integer == 1) {
          map.remove(t.charAt(i));
        } else {
          return true;
        }
      } else {
        return false;
      }
    }
    if (!map.isEmpty()) {
      return false;
    }
    return true;
  }

  // is valid Paranthesis
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
        continue;
      }
      if (stack.isEmpty()) {
        return false;
      }
      switch (c) {
        case ')':
          Character pop1 = stack.pop();
          if (pop1 != '(') {
            return false;
          }
          break;
        case '}':
          Character pop2 = stack.pop();
          if (pop2 != '{') {
            return false;
          }
          break;
        case ']':
          Character pop3 = stack.pop();
          if (pop3 != '[') {
            return false;
          }
          break;
      }
    }
    if (stack.isEmpty()) {
      return true;
    }
    return false;
  }

  // intersection of two linked lists
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenA = 0, lenB = 0;
    ListNode temp = headA;
    while (temp != null) {
      lenA++;
      temp = temp.next;
    }
    temp = headB;
    while (temp != null) {
      lenB++;
      temp = temp.next;
    }
    int diff = lenA >= lenB ? lenA - lenB : lenB - lenA;
    ListNode temp1 = headA, temp2 = headB;
    if (lenA >= lenB) {
      temp1 = headA;
      while (diff > 0) {
        temp1 = temp1.next;
        diff--;
      }
    } else {
      temp2 = headB;
      while (diff > 0) {
        temp2 = temp2.next;
        diff--;
      }
    }
    while (temp1 != null && temp2 != null && temp1 != temp2) {
      temp1 = temp1.next;
      temp2 = temp2.next;
    }
    return temp1;
  }

  public ListNode reverseList(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode prev = null, curr = head, next;
    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  //Given an array nums containing n distinct numbers
  // in the range [0, n], return the only number in the
  // range that is missing from the array.
  public int missingNumber(int[] nums) {
    int xor = 0;
    for (int i = 0; i < nums.length; i++) {
      xor ^= nums[i];
    }
    for (int i = 0; i <= nums.length; i++) {
      xor ^= i;
    }
    return xor;
  }

  public int firstUniqChar(String s) {
    Map<Character, Integer> charCounts = new LinkedHashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
    }

    for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
      if (entry.getValue() == 1) {
        char c = entry.getKey();
        return s.indexOf(c);
      }
    }
    return -1;
  }

  // Best Time to Buy and Sell Stock in O(n^2)
  public int maxProfit(int[] prices) {
    int maxProfit = 0;
    for (int i = 0; i < prices.length; i++) {
      for (int j = i + 1; j < prices.length; j++) {
        if (prices[j] - prices[i] > maxProfit) {
          maxProfit = prices[j] - prices[i];
        }
      }
    }
    return maxProfit;
  }

  // Best Time to Buy and Sell Stock in O(n)
  public int maxProfitOofn(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }
    int minPrice = prices[0];
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      int profit = prices[i] - minPrice;
      if (profit > maxProfit) {
        maxProfit = profit;
      }
      if (prices[i] < minPrice) {
        minPrice = prices[i];
      }
    }
    return maxProfit;
  }

  public boolean isPowerOfThree(int n) {
    if (n < 1 || n == 2) {
      return false;
    }
    if (n == 1) {
      return true;
    }
    while (n > 3) {
      if (n % 3 != 0) {
        return false;
      }
      n = n / 3;
    }
    if (n == 3) {
      return true;
    }
    return false;
  }

  public List<String> fizzBuzz(int n) {
    List<String> list = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        list.add("FizzBuzz");
      } else if (i % 3 == 0) {
        list.add("Fizz");
      } else if (i % 5 == 0) {
        list.add("Buzz");
      } else {
        list.add(String.valueOf(i));
      }
    }
    return list;
  }

  public void reverseString(char[] s) {
    if (s == null || s.length == 0) {
      return;
    }
    int start = 0, end = s.length - 1;
    while (start < end) {
      char temp = s[start];
      s[start] = s[end];
      s[end] = temp;
      start++;
      end--;
    }
  }

  public boolean isPalindrome(ListNode head) {
    int len = 0;
    if (head == null) {
      return false;
    }
    ListNode temp = head;
    while (temp != null) {
      len++;
      temp = temp.next;
    }
    if (len == 1) {
      return true;
    }
    int[] arr = new int[len / 2];
    temp = head;
    int start = 0;
    while (start < len / 2) {
      arr[start] = temp.val;
      start++;
    }
    if (len % 2 == 1) {
      temp = temp.next;
    }
    int back = start - 1;
    while (start < len) {
      if (temp.val == arr[back]) {
        temp = temp.next;
        back--;
        start++;
        continue;
      } else {
        return false;
      }
    }
    return true;
  }

  public static int romanToInt(String s) {
    int len = s.length();
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);
    int result = 0;
    int prev = Integer.MIN_VALUE;
    for (int i = len - 1; i >= 0; i--) {
      char c = s.charAt(i);
      Integer integer = map.get(c);
      if (integer >= prev) {
        result = result + integer.intValue();
        prev = integer;
      } else {
        result = result - integer.intValue();
      }
    }
    return result;

  }

//  public static void main(String[] args) {
//    romanToInt("MCMXCIV");
//  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);
    return root;
  }

  public int binarySearch(int[] nums, int target) {
    int len = nums.length;
    int start = 0, end = len - 1;
    while (start <= end) {
      int mid = (start + end) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }

//  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
//    return image;
//  }
//
//  private void fill(int[][] image, int sr, int sc, int color) {
//    int rows = image.length;
//    int columns = image[0].length;
//    if (sr < 0 || sr > rows - 1 || sc < 0 || sc > columns - 1) {
//      return;
//    }
//    if(image[sr-1][sc] == image[])
//  }

  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root.val >= p.val && root.val <= q.val ||
        root.val >= q.val && root.val <= p.val) {
      return root;
    } else if (root.val > p.val && root.val > q.val) {
      return lowestCommonAncestor(root.left, p, q);
    } else if (root.val < p.val && root.val < q.val) {
      return lowestCommonAncestor(root.right, p, q);
    }
    return null;
  }

  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    int lheight = calDepth(root.left);
    int rheight = calDepth(root.right);
    if (Math.abs(lheight - rheight) > 1) {
      return false;
    }
    return isBalanced(root.left) && isBalanced(root.right);
  }

  private int calDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(calDepth(root.left), calDepth(root.right));
  }

  class MyQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue() {
      stack1 = new Stack<>();
      stack2 = new Stack<>();
    }

    public void push(int x) {
      while (!stack2.isEmpty()) {
        stack1.push(stack2.pop());
      }
      stack1.push(x);
    }

    public int pop() {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
      return stack2.pop();
    }

    public int peek() {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
      return stack2.peek();
    }

    public boolean empty() {
      if (stack1.isEmpty() && stack2.isEmpty()) {
        return true;
      }
      return false;
    }
  }

  public boolean canConstruct(String ransomNote, String magazine) {
    char[] magChars = magazine.toCharArray();
    Map<Character, Integer> mapCount = new HashMap<>();
    for (char c : magChars) {
      Integer orDefault = mapCount.getOrDefault(c, 0);
      mapCount.put(c, orDefault.intValue() + 1);
    }
    char[] ransomChars = ransomNote.toCharArray();
    for (char c : ransomChars) {
      if (mapCount.containsKey(c) && mapCount.get(c) > 0) {
        Integer orDefault = mapCount.get(c);
        mapCount.put(c, orDefault.intValue() - 1);
      } else {
        return false;
      }
    }
    return true;
  }

  public int climbStairs(int n) {
    if (n == 1) {
      return 1;
    }
    int[] dp = new int[n];
    dp[0] = 1;
    dp[1] = 2;
    for (int i = 2; i < n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n - 1];
  }

  public int longestPalindrome1(String string) {
    char[] magChars = string.toCharArray();
    Map<Character, Integer> mapCount = new HashMap<>();
    for (char c : magChars) {
      Integer orDefault = mapCount.getOrDefault(c, 0);
      mapCount.put(c, orDefault.intValue() + 1);
    }
    final AtomicInteger[] length = {new AtomicInteger()};
    final boolean[] al = {false};
    mapCount.forEach((key, value) -> {
      if (value % 2 == 0) {
        length[0].set(length[0].get() + value);
      } else if (value > 1) {
        if (al[0] == false) {
          length[0].getAndIncrement();
          al[0] = true;
        }
        length[0].set(length[0].get() + value - 1);
      } else if (value == 1) {
        if (al[0] == false) {
          length[0].getAndIncrement();
          al[0] = true;
        }
      }
    });
    return length[0].get();
  }

  public static String addBinary(String a, String b) {
    List<Integer> list = new ArrayList<>();
    char[] charsa = a.toCharArray();
    char[] charsb = b.toCharArray();
    int n = charsa.length - 1;
    int m = charsb.length - 1;
    int aVal = 0;
    int yVal = 0;
    int carry = 0;
    int sum = 0;
    while (n >= 0 || m >= 0) {
      sum = 0;
      if (n >= 0) {
        char x = charsa[n];
        aVal = x - '0';
        sum = sum + aVal;
        n--;
      }
      if (m >= 0) {
        char y = charsb[m];
        yVal = y - '0';
        sum = sum + yVal;
        m--;
      }
      sum = sum + carry;
      if (sum == 0) {
        list.add(0);
        carry = 0;
      } else if (sum == 1) {
        list.add(1);
        carry = 0;
      } else if (sum == 2) {
        list.add(0);
        carry = 1;
      } else if (sum == 3) {
        list.add(1);
        carry = 1;
      }
    }
    if (carry == 1) {
      list.add(1);
    }
    Collections.reverse(list);
    String reversedString = "";
    for (int element : list) {
      reversedString += element;
    }
    return reversedString;
  }

//  public static void main(String[] args) {
//    System.out.println(addBinary("11111", "111"));
//  }

  public int diameterOfBinaryTree(TreeNode root) {
    int[] maxDiameter = new int[1];
    dfs(root, maxDiameter);
    return maxDiameter[0];
  }

  public int dfs(TreeNode root, int[] maxDiameter) {
    if (root == null) {
      return 0;
    }
    int lDepth = dfs(root.left, maxDiameter);
    int rDepth = dfs(root.right, maxDiameter);
    maxDiameter[0] = Math.max(maxDiameter[0], lDepth + rDepth);
    return 1 + Math.max(lDepth, rDepth);
  }

  public ListNode middleNode(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null) {
      return head;
    }
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) {
        return true;
      }
      set.add(num);
    }
    return false;
  }

//  public int maxSubArray(int[] nums) {
//    int startIndex = -1;
//    int maxSum = 0;
//    for(int i = 0; i< nums.length;i++){
//      if()
//    }
//    return 1;
//  }

  public String spinWords(String sentence) {
    List<String> list = Arrays.stream(sentence.split(" ")).map(word -> {
      if(word.length() >= 5){
        return new StringBuilder(word).reverse().toString();
      }
      return word;
    }).collect(Collectors.toList());
    return String.join(" ", list);
  }

  // Morse Code decipher
  public String decode(String morseCode){
    String newString = morseCode.trim();
    StringBuilder finalOutput = new StringBuilder();
    int startWord = 0, i=0;
    for(;i<newString.length();i++){
      if(newString.charAt(i)!=' '){
        continue;
      }
      String subString = newString.substring(startWord,i);
      System.out.println(subString);
      // uncomment this below line
      finalOutput.append(MorseCode.get(subString));
      if( (i + 2 <= newString.length() - 1) && newString.charAt(i+1) == ' ' && newString.charAt(i+2) == ' '){
        finalOutput.append(" ");
        i = i + 2;
        startWord = i + 1;
        System.out.println(i + " " + startWord);
        continue;
      }
      startWord = i+1;
    }
    return finalOutput.append(MorseCode.get(newString.substring(startWord,i))).toString();
  }

  public String decode2(String morseCode) {
    String[] words = morseCode.trim().split("\\s{3}");
    StringBuilder finalOutput = new StringBuilder();
    for (String word : words) {
      String[] letters = word.split("\\s");
      for (String letter : letters) {
        if (MorseCode.containsKey(letter)) {
          finalOutput.append(MorseCode.get(letter));
        }
      }
      finalOutput.append(" ");
    }
    finalOutput.deleteCharAt(finalOutput.length() - 1);
    return finalOutput.toString();
  }

  public String highAndLow(String input){
    StringBuilder output = new StringBuilder();
    String[] s = input.split(" ");
    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    for(String word : s){
      Integer integer = Integer.valueOf(word);
      if(integer.intValue() > max){
        max = integer.intValue();
      }
      if(integer.intValue() < min){
        min = integer.intValue();
      }
    }
    return output.append(max).append(" ").append(min).toString();
  }

  public int findShort(String input){
    String[] s = input.trim().split(" ");
    int min = Integer.MAX_VALUE;
    for(String word : s){
      int len = word.length();
      if(len < min){
        min = len;
      }
    }
    return min;
  }

  public boolean getXO(String str){
    char[] chars = str.trim().toCharArray();
    int xcount = 0, ocount = 0;
    for(char c : chars){
      if(c == 'x' || c == 'X'){
        xcount++;
        continue;
      }
      if(c == 'o' || c == 'O'){
        ocount++;
        continue;
      }
    }
    return xcount == ocount;
  }

  public static int rowSumOddNumbers(int n){
    int skipNumbers = (n * (n-1))/2;
    int startNumber = 1 + skipNumbers * 2;
    int sum = startNumber;
    if(n == 1){
      return 1;
    }
    while(n > 1){
      int num = startNumber + 2;
      sum = sum + num;
      startNumber = startNumber + 2;
      n--;
    }
    return sum;
  }

//  public static void main(String[] args) {
//    rowSumOddNumbers(3);
//  }


  public long findNextSquare(long sq) {
    double sqrt = Math.sqrt(sq);
    if(sqrt == (int) sqrt) {
      return (long) ((sqrt + 1) * (sqrt + 1));
    }
    return -1;
  }

  public String[] towerBuilder(int nFloors)
  {
    List<String> tower = new ArrayList<String>();
    for (int i = 0; i < nFloors; i++) {
      String row = "";
      int numSpaces = nFloors - i - 1;
      int numAsterisks = 2 * i + 1;
      for (int j = 0; j < numSpaces; j++) {
        row += " ";
      }
      for (int j = 0; j < numAsterisks; j++) {
        row += "*";
      }
      for (int j = 0; j < numSpaces; j++) {
        row += " ";
      }
      tower.add(row);
    }
    return tower.toArray(String[]::new);

  }

  public static double findUniq(double arr[]) {
    Map<Double,Integer> map = new HashMap<>();
    for(double val : arr){
        Integer orDefault = map.getOrDefault(val, 0);
        map.put(val,orDefault.intValue() + 1);
    }
    Set<Double> doubles = map.keySet();
    for(double val : doubles){
      if(map.get(val) == 1){
        return val;
      }
    }
    return -1;
  }

  public static boolean isNarcissistic(int number) {
    int result = 0;
    int number1 = number;
    int numOfDigits = 0;
    while(number1 > 0){
      number1 /= 10;
      numOfDigits++;
    }
    number1 = number;
    while(number1 > 0){
      int rem = number1%10;
      result += Math.pow(rem,numOfDigits);
      number1 /= 10;
    }
    return result == number;
  }

//  public static void main(String[] args) {
//    isNarcissistic(1634);
//  }

  public static String incrementString(String str) {
    if(str == null || str.length() == 0){
      return "1";
    }
    int i, end = str.length();
    boolean numberFound = false;
    for(i=str.length() - 1;i >= 0;i--){
      if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
        numberFound = true;
        continue;
      }
      break;
    }
    if(i == -1 && !numberFound){
      return str + "1";
    }
    String substring = str.substring(i+ 1, end);
    char[] result = getNumberPlusOneChars(substring);
    StringBuilder sb = new StringBuilder();
    for(char c : result){
      sb.append(c);
    }
    return str.substring(0,i + 1) + sb;
  }

  private static char[] getNumberPlusOneChars(String substring) {
    char[] chars = substring.toCharArray();
    int len = chars.length;
    for (int i = len - 1; i >= 0; i--) {
      if (chars[i] < '9') {
        chars[i]++;
        return chars;
      }
      chars[i] = '0';
    }
    char[] result = new char[len + 1];
    Arrays.fill(result, '0');
    result[0] = '1';
    return result;
  }

//  public static void main(String[] args) {
//      System.out.println(incrementString("abcd"));
//  }

  public static int bouncingBall(double h, double bounce, double window) {
    // your code
    if(h <= 0 || bounce <= 0 || bounce >= 1 || window >= h){
      return -1;
    }
    double calHeight = h;
    int visible = 0;
    while(calHeight >= window){
      calHeight =  calHeight * bounce;
      visible++;
    }
    return visible;
  }

  public static int persistence(long n) {
    long temp = n;
    int count = 0;
    while(temp > 9){
      long localTemp = temp;
      long mul = 1;
      while(localTemp > 0){
        long rem = localTemp % 10;
        mul *= rem;
        localTemp /= 10;
      }
      count++;
      temp = mul;
    }
    return count;
  }

//    public static void main(String[] args) {
//      System.out.println(persistence(39));
//  }

  public static int solveSuperMarketQueue(int[] customers, int n) {
    int totalTime = 0;
    int[] checkoutTills = new int[n]; // [0,0,0,0,0]
    Queue<Integer> queue = new LinkedList<>(); // initialize the queue
    for (int i = 0; i < customers.length; i++) {
      queue.offer(customers[i]);
    }
    // process the queue until all customers have checked out
    while (!queue.isEmpty()) {
      // find the earliest available checkout till
      int minTime = Integer.MAX_VALUE;
      int minIndex = -1;
      for (int i = 0; i < n; i++) {
        if (checkoutTills[i] < minTime) {
          minTime = checkoutTills[i];
          minIndex = i;
        }
      }
      // assign the next customer to the earliest available checkout till
      int customerTime = queue.poll();
      checkoutTills[minIndex] += customerTime;
      totalTime = Math.max(totalTime, checkoutTills[minIndex]);
    }
    return totalTime;
  }

  public static List<String> topThreeWords(String text) {
    // Replace any non-letter or apostrophe characters with whitespace
    String cleanedText = text.replaceAll("[^\\w']+", " ");

    // Split the text into an array of words, and convert to lowercase
    String[] words = cleanedText.toLowerCase().split(" ");

    // Create a map to store the word counts
    Map<String, Integer> wordCounts = new HashMap<>();

    // Loop through each word and count its occurrences
    for (String word : words) {
      if (word.matches("^'+\\w+$")) {
        // Remove apostrophes at the beginning of the word
        word = word.replaceFirst("^'+", "");
      }
      if (word.matches("^\\w+'+$")) {
        // Remove apostrophes at the end of the word
        word = word.replaceFirst("'+$", "");
      }
      if (word.matches("^\\w+'+\\w+$")) {
        // Remove apostrophes in the middle of the word
        word = word.replaceAll("'+", "");
      }
      if (!word.isEmpty()) {
        // Increment the word count
        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
      }
    }

    // Convert the word counts map to a list of [word, count] pairs
    List<Map.Entry<String, Integer>> wordCountsList = new ArrayList<>(wordCounts.entrySet());
    wordCountsList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    // Return the top 3 words, or fewer if there are fewer than 3 unique words
    List<String> topWords = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : wordCountsList) {
      topWords.add(entry.getKey());
      if (topWords.size() == 3) {
        break;
      }
    }

    return topWords;
  }

  public static int calculateMaxProfit(int savings, int[] currentVal, int[] futureVal) {
    int maxProfit = 0;

    for (int i = 0; i < currentVal.length; i++) {
      if (currentVal[i] <= savings) {
        int profit = futureVal[i] - currentVal[i];
        maxProfit += Math.max(profit, 0);
        savings -= currentVal[i];
      }
    }

    return maxProfit;
  }

  public static void main(String[] args) {
    int savings = 449;
    int[] currentVal = {125, 158, 1, 210, 104,243,238,161,227,264};
    int[] futureVal = {298,268,287,84,267,64,269,284,273,96};

    int maxProfit = calculateMaxProfit(savings, currentVal, futureVal);
    System.out.println("Maximum Profit: " + maxProfit);
  }

  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> finalList = new ArrayList<>();
    for (int i = 0; i < nums.length - 3; i++) {
      int x = nums[i];
      int remaining = 0 - x;
      for (int j = i + 1; j < nums.length - 2; j++) {
        for (int k = j + 1; k < nums.length - 1; k++) {
          if (nums[j] + nums[k] == remaining) {
            List<Integer> list = Arrays.asList(nums[i],nums[j],nums[k]);
            finalList.add(list);
          }
        }
      }
    }
    List<List<Integer>> result = removeDuplicates(finalList);
    return result;
  }

  public List<List<Integer>> removeDuplicates(List<List<Integer>> listOfLists) {
    List<List<Integer>> result = new ArrayList<>();
    Set<List<Integer>> set = new HashSet<>();

    for (List<Integer> list : listOfLists) {
      List<Integer> sortedList = new ArrayList<>(list);
      sortedList.sort(null); // Sort the sublist to handle duplicates

      if (set.add(sortedList)) {
        result.add(list);
      }
    }

    return result;
  }

  // works but uses n^2 approach
  public int maxSubArray(int[] nums) {
    int maxVal = Integer.MIN_VALUE;
    for(int i=0;i<nums.length;i++){
      if(nums[i]> maxVal){
        maxVal = nums[i];
      }
    }
    for(int i=0;i<nums.length;i++){
      int val = nums[i];
      for(int j=i+1;j<nums.length;j++){
        val = val + nums[j];
        if(val>maxVal){
          maxVal = val;
        }
      }
    }
    return maxVal;
  }

  // need to continue
  // 2 5   7 10  17 25   28 38  40 42
  // 9 15
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<Integer> combinedList = new ArrayList<>();
    for(int i=0;i<intervals.length;i++){
      int[] interval = intervals[i];
      combinedList.add(interval[0]);
      combinedList.add(interval[1]);
    }
    int[] intArray = combinedList.stream().mapToInt(Integer::intValue).toArray();
    int small = newInterval[0];
    int big = newInterval[1];
    int smallIndex = -1, largeindex = -1;
    for(int i=0;i<intArray.length;i++){
      if(small<=intArray[i]){
        smallIndex = i;
        break;
      }
    }
    for(int i=intArray.length - 1;i>=0;i--){
      if(big>=intArray[i]){
        largeindex = i;
        break;
      }
    }
    System.out.println(smallIndex + " " + largeindex);
    return null;
  }

  // solution i tried. Giving only half test cases passed
  public String shiftingLetters(String s, int[] shifts) {
    for(int i=0;i<shifts.length;i++){
      int sum = 0;
      for(int j=i;j<shifts.length;j++){
        sum = sum + shifts[j];
      }
      shifts[i] = sum;
    }

    StringBuilder str = new StringBuilder();

    for(int i = 0;i<s.length();i++){
      char c = s.charAt(i);
      int newChar = c + shifts[i];
      int rem =  ( newChar - 97 ) % 26 + 'a';
      str.append((char) rem);
    }

    return str.toString();
  }

  public String shiftingLetters1(String s, int[] shifts) {
    int n = s.length();
    long cumulativeShift = 0;
    StringBuilder sb = new StringBuilder();

    for (int i = n - 1; i >= 0; i--) {
      cumulativeShift += shifts[i];
      char c = s.charAt(i);
      long shiftedValue = (c - 'a' + cumulativeShift) % 26;
      char shiftedChar = (char) (shiftedValue + 'a');
      sb.insert(0, shiftedChar);
    }

    return sb.toString();
  }

  }
