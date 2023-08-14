package com.pluralsight.streamlambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadedSum {

  private static final int NUM_THREADS = 4;
  private static final int ARRAY_SIZE = 20;
  private static final int CHUNK_SIZE = 5;

  private static int[] array = new int[ARRAY_SIZE];
  private static int[] results = new int[NUM_THREADS];

  public static void main(String[] args) {
    // Initialize the array with numbers from 1 to ARRAY_SIZE
    for (int i = 0; i < ARRAY_SIZE; i++) {
      array[i] = i + 1;
    }

    // Create a fixed thread pool with NUM_THREADS threads
    ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    // Submit tasks to calculate the sum of numbers in each chunk
    for (int i = 0; i < NUM_THREADS; i++) {
      int startIndex = i * CHUNK_SIZE;
      int endIndex = (i + 1) * CHUNK_SIZE;
      executor.submit(new SumCalculator(startIndex, endIndex, i));
    }

    // Shutdown the executor and wait for all tasks to complete
    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.MINUTES);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Calculate the final sum by adding the results from each thread
    int finalSum = 0;
    for (int result : results) {
      finalSum += result;
    }

    System.out.println("Sum: " + finalSum);
  }

  static class SumCalculator implements Runnable {
    private int startIndex;
    private int endIndex;
    private int threadIndex;

    public SumCalculator(int startIndex, int endIndex, int threadIndex) {
      this.startIndex = startIndex;
      this.endIndex = endIndex;
      this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
      int sum = 0;
      for (int i = startIndex; i < endIndex; i++) {
        sum += array[i];
      }
      results[threadIndex] = sum;
      System.out.println(Thread.currentThread().getId() + " " + results[threadIndex]);
    }
  }
}

