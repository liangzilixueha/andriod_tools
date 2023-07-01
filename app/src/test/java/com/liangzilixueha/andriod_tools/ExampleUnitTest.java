package com.liangzilixueha.andriod_tools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int[] arr = {1, 4, 5, 8, 76, 3, 0, 2, 1, 9, 8, -3, 5};
        for (int i : arr) {
            System.out.print(i + " ");
        }
//        BSort(arr);
        QuickSort(arr, 0, arr.length - 1);
        System.out.println();
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private void QuickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = doublePoint(arr, start, end);
        QuickSort(arr, start, p - 1);
        QuickSort(arr, p + 1, end);

    }

    private int doublePoint(int[] arr, int start, int end) {
        int value = arr[start];//记录最开始的值
        int left = start;
        int right = end;//左右指针
        while (left < right) {
            while (left < right && arr[right] > value) {
                right--;
            }
            while (left < right && arr[left] <= value) {
                left++;
            }
            //此时通过双重循环，找到了第一组需要交换的数据
            if (left < right) {
                int k = arr[left];
                arr[left] = arr[right];
                arr[right] = k;//如果发现左边大于右边，那么交换
            }
        }
        //最后，将开头的标兵放回去
        arr[start] = arr[right];
        arr[right] = value;
        return right;
    }

    private void BSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}