package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Top150 {

//  88. Merge Sorted Array
//  You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
//
//  Merge nums1 and nums2 into a single array sorted in non-decreasing order.
//
//  The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1;
    int j = n - 1;
    int k = m + n - 1;
    while (i >= 0 && j >= 0) {
      if (nums1[i] < nums2[j]) {
        nums1[k--] = nums2[j--];
      } else {
        nums1[k--] = nums1[i--];
      }
    }
    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }
  }

//  27. Remove Element
//  Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
//
//  Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
//
//  Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
//  Return k.

  public int removeElement(int[] nums, int val) {
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == val) {
        continue;
      } else {
        nums[j++] = nums[i];
      }
    }
    return j;
  }

//  26. Remove Duplicates from Sorted Array
//
//  Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
//
//  Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
//
//  Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
//  Return k.

  public int removeDuplicates(int[] nums) {
    int x = 0;
    for (int y = 0; y < nums.length - 1; y++) {
      if (nums[y] == nums[y + 1]) {
        continue;
      } else {
        nums[++x] = nums[y + 1];
      }
    }
    return x + 1;
  }

//  80. Remove Duplicates from Sorted Array II
//
//  Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
//
//  Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
//
//  Return k after placing the final result in the first k slots of nums.
//
//  Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

  public int removeDuplicates2(int[] nums) {
    int x = 0;
    boolean test = false;
    for (int y = 0; y < nums.length - 1; y++) {
      if (nums[y] == nums[y + 1] && !test) {
        nums[++x] = nums[y + 1];
        test = true;
      } else if (nums[y] == nums[y + 1] && test) {
        continue;
      } else {
        nums[++x] = nums[y + 1];
        test = false;
      }
    }
    return x + 1;
  }

//  169. Majority Element
//  Given an array nums of size n, return the majority element.
//
//  The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

  public int majorityElement(int[] nums) {
    int count = 0;
    int maj = -1;
    for (int i : nums) {
      if (count == 0) {
        maj = i;
      }
      count += (i == maj) ? 1 : -1;
    }
    return maj;
  }

//  189. Rotate Array
//  Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
// *********

  public void rotate(int[] nums, int k) {
    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  private void reverse(int[] nums, int i, int j) {
    while (i < j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
      i++;
      j--;
    }
  }

  //  121. Best Time to Buy and Sell Stock
//
//  You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//  You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//
//  Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
// ***********
  public int maxProfit(int[] prices) {
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

//  122. Best Time to Buy and Sell Stock II
//  You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
//  On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
//
//  Find and return the maximum profit you can achieve.

  public int maxProfit1(int[] prices) {
    int peak, valley;
    int maxProfit = 0;
    int len = prices.length;
    for (int i = 0; i < len - 1; i++) {
      while (i < len - 1 && prices[i] >= prices[i + 1]) {
        i++;
      }
      valley = prices[i];
      while (i < len - 1 && prices[i] <= prices[i + 1]) {
        i++;
      }
      peak = prices[i];
      maxProfit += peak - valley;
    }
    return maxProfit;
  }

//  55. Jump Game
//  You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
//
//  Return true if you can reach the last index, or false otherwise.

  public boolean canJump(int[] nums) {
    boolean[] dp = new boolean[nums.length];
    dp[0] = true;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && nums[j] + j >= i) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[nums.length - 1];
  }

//  45. Jump Game II
//  You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
//
//  Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
//
//      0 <= j <= nums[i] and
//  i + j < n
//  Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

  public int jump(int[] nums) {
    int len = nums.length;
    int[] dp = new int[len];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] != Integer.MAX_VALUE && nums[j] + j >= i) {
          dp[i] = Math.min(dp[i], dp[j] + 1);
        }
      }
    }
    return dp[len - 1];
  }

//  58. Length of Last Word
//  Given a string s consisting of words and spaces, return the length of the last word in the string.
//
//  A word is a maximal
//      substring
//  consisting of non-space characters only.

  public int lengthOfLastWord(String s) {
    String[] split = s.split(" ");
    int len = split.length;
    return split[len - 1].length();
  }

//  151. Reverse Words in a String
//  Given an input string s, reverse the order of the words.
//
//  A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
//
//  Return a string of the words in reverse order concatenated by a single space.
//
//  Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

  public String reverseWords(String s) {
    StringBuilder sbr = trimSpaces(s);
    reverse(sbr, 0, sbr.length() - 1);
    int wordStart = 0, wordEnd = 0;
    while (wordStart <= sbr.length() - 1) {
      while (wordEnd <= sbr.length() - 1 && sbr.charAt(wordEnd) != ' ') {
        ++wordEnd;
      }
      reverse(sbr, wordStart, wordEnd - 1);
      wordStart = wordEnd++ + 1;
    }
    return sbr.toString();
  }

  public StringBuilder trimSpaces(String s) {
    int i = 0, j = s.length() - 1;
    while (i <= j && s.charAt(i) == ' ') {
      i++;
    }
    while (j >= i && s.charAt(j) == ' ') {
      j--;
    }
    StringBuilder str = new StringBuilder();
    while (i <= j) {
      if (s.charAt(i) != ' ') {
        str.append(s.charAt(i));
      } else if (str.charAt(str.length() - 1) != ' ') {
        str.append(s.charAt(i));
      }
      i++;
    }
    return str;
  }

  public void reverse(StringBuilder str, int i, int j) {
    while (i < j) {
      char temp = str.charAt(i);
      str.setCharAt(i++, str.charAt(j));
      str.setCharAt(j--, temp);
    }
  }

//  13. Roman to Integer
//  Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

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

//  42. Trapping Rain Water
//  Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

  public int trap(int[] height) {
    int[] leftHeight = new int[height.length];
    int[] rightHeight = new int[height.length];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < height.length; i++) {
      if (height[i] > max) {
        max = height[i];
      }
      leftHeight[i] = max;
    }
    max = Integer.MIN_VALUE;
    for (int i = height.length - 1; i >= 0; i--) {
      if (height[i] > max) {
        max = height[i];
      }
      rightHeight[i] = max;
    }

    int storedWater = 0;
    for (int i = 0; i < height.length; i++) {
      int min = Math.min(leftHeight[i], rightHeight[i]) - height[i];
      storedWater += min;
    }
    return storedWater;
  }
//
//  238. Product of Array Except Self
//  Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
//
//  The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//
//  You must write an algorithm that runs in O(n) time and without using the division operation


  public int[] productExceptSelf(int[] nums) {
    int len = nums.length;
    int[] leftProd = new int[nums.length];
    int[] rightProd = new int[nums.length];
    leftProd[0] = 1;
    for (int i = 1; i < len; i++) {
      leftProd[i] = leftProd[i - 1] * nums[i - 1];
    }
    rightProd[len - 1] = 1;
    for (int i = len - 2; i >= 0; i--) {
      rightProd[i] = rightProd[i + 1] * nums[i + 1];
    }
    for (int i = 0; i < nums.length; i++) {
      nums[i] = leftProd[i] * rightProd[i];
    }
    return nums;
  }

//  202. Happy Number
//  Write an algorithm to determine if a number n is happy.
//
//  A happy number is a number defined by the following process:
//
//  Starting with any positive integer, replace the number by the sum of the squares of its digits.
//  Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
//  Those numbers for which this process ends in 1 are happy.
//  Return true if n is a happy number, and false if not.

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

//  125. Valid Palindrome
//  A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
//
//  Given a string s, return true if it is a palindrome, or false otherwise.


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

//  11. Container With Most Water
//
//  You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
//
//  Find two lines that together with the x-axis form a container, such that the container contains the most water.
//
//  Return the maximum amount of water a container can store.
//
//  Notice that you may not slant the container.

  public int maxArea(int[] height) {
    int maxArea = Integer.MIN_VALUE, i = 0, j = height.length - 1;
    while (i < j) {
      int area;
      if (height[i] <= height[j]) {
        area = height[i] * (j - i);
        i++;
      } else {
        area = height[j] * (j - i);
        j--;
      }
      if (area > maxArea) {
        maxArea = area;
      }
    }
    return maxArea;
  }

//  383. Ransom Note
//  Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
//
//  Each letter in magazine can only be used once in ransomNote.

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

//  205. Isomorphic Strings
//
//  Given two strings s and t, determine if they are isomorphic.
//
//  Two strings s and t are isomorphic if the characters in s can be replaced to get t.
//
//  All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.


  public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    char[] sChars = s.toCharArray();
    Map<Character, Integer> smapCount = new HashMap<>();
    for (char c : sChars) {
      Integer orDefault = smapCount.getOrDefault(c, 0);
      smapCount.put(c, orDefault.intValue() + 1);
    }
    char[] tChars = t.toCharArray();
    Map<Character, Integer> tmapCount = new HashMap<>();
    for (char c : tChars) {
      Integer orDefault = tmapCount.getOrDefault(c, 0);
      tmapCount.put(c, orDefault.intValue() + 1);
    }

    for (int i = 0; i < s.length(); i++) {
      Character schar = s.charAt(i);
      Character tchar = t.charAt(i);
      if (smapCount.get(schar) != tmapCount.get(tchar)) {
        return false;
      }
    }
    return true;
  }

  public boolean isIsomorphic1(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    Map<Character, Character> mapping = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      Character schar = s.charAt(i);
      Character tchar = t.charAt(i);
      if (schar == tchar) {
        continue;
      }
      if (mapping.get(schar) != null) {
        if (mapping.get(schar) != tchar) {
          return false;
        }
        continue;
      }
      mapping.put(schar, tchar);
    }
    return true;
  }

//  3. Longest Substring Without Repeating Characters
//  Given a string s, find the length of the longest
//      substring
//  without repeating characters.

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

//  242. Valid Anagram
//  Given two strings s and t, return true if t is an anagram of s, and false otherwise.
//
//  An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

  public boolean isAnagram(String s, String t) {
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        Integer integer = map.get(s.charAt(i));
        map.put(s.charAt(i), integer.intValue() + 1);
      } else {
        map.put(s.charAt(i), 1);
      }
    }
    for (int i = 0; i < t.length(); i++) {
      if (map.containsKey(t.charAt(i))) {
        Integer integer = map.get(t.charAt(i));
        if (integer > 1) {
          map.put(t.charAt(i), integer.intValue() - 1);
        } else if (integer == 1) {
          map.remove(t.charAt(i));
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

//1. Two Sum
//  Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//  You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//  You can return the answer in any order.

  public int[] twoSum(int[] nums, int target) {
    final int[] output = new int[2];
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

//  20. Valid Parentheses
//  Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//  An input string is valid if:
//
//  Open brackets must be closed by the same type of brackets.
//  Open brackets must be closed in the correct order.
//  Every close bracket has a corresponding open bracket of the same type.


  public boolean isValid(String s) {
    final Stack<Character> stack = new Stack<>();
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

//  141. Linked List Cycle
//  Given head, the head of a linked list, determine if the linked list has a cycle in it.
//
//  There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
//
//  Return true if there is a cycle in the linked list. Otherwise, return false.

  class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
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
//
//  219. Contains Duplicate II
//  Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; ++i) {
      if (set.contains(nums[i])) {
        return true;
      }
      set.add(nums[i]);
      if (set.size() > k) {
        set.remove(nums[i - k]);
      }
    }
    return false;
  }

//  2. Add Two Numbers
//  You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//
//  You may assume the two numbers do not contain any leading zero, except the number 0 itself.

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
      temp = temp.next;
    }
    return dummy.next;
  }

//  21. Merge Two Sorted Lists
//  You are given the heads of two sorted linked lists list1 and list2.
//
//  Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
//
//  Return the head of the merged linked list.

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

//  104. Maximum Depth of Binary Tree
//  Given the root of a binary tree, return its maximum depth.
//
//  A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.


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

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }


  public int peakIndexInMountainArray(int[] arr) {
    int low = 0, high = arr.length - 1;
    while (low < high) {
      int mid = (low + high) / 2;
      if (arr[mid] < arr[mid + 1]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return low;
  }
}
