package com.algebra.basic.algorithm.bubble;

import java.util.Arrays;

/**
 * @author al
 * @date 2019/12/17 9:13
 * @description
 */
public class BubbleTest01 {

    /**
     * 最简单冒泡排序
     * 时间复杂度分析： O(n^2)
     * @param ary 数组
     * @return 循环执行次数
     */
    private static int bubbleSort01(int[] ary){
        int tmp = 0;
        int count = 0;
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary.length-i-1; j++) {
                if (ary[j] > ary[j+1]) {
                    tmp = ary[j];
                    ary[j] = ary[j+1];
                    ary[j+1] = tmp;
                }
                count ++;
            }
            System.out.println("第"+(i+1)+"遍排序结果："+Arrays.toString(ary));
        }
        return count;
    }

    /**
     * 冒泡排序改进-1：标记元素是否有交换，没有则跳出大循环
     * 时间复杂度分析： O(n^2)
     * @param ary 数组
     * @return 循环执行次数
     */
    private static int bubbleSort02(int[] ary){
        int tmp = 0;
        int count = 0;
        for (int i = 0; i < ary.length; i++) {
            // 每一轮初始状态都是true，有序标记(期望数组有序)
            boolean isSorted = true;
            for (int j = 0; j < ary.length-i-1; j++) {
                if (ary[j] > ary[j+1]) {
                    tmp = ary[j];
                    ary[j] = ary[j+1];
                    ary[j+1] = tmp;
                    // 如果存在元素交换，就表示无序
                    isSorted = false;
                }
                count ++;
            }
            System.out.println("第"+(i+1)+"遍排序结果："+Arrays.toString(ary));
            if(isSorted){
                break;
            }
        }
        return count;
    }

    /**
     * 冒泡排序改进-1：sortBorder就是无序数列的边界。每一轮排序过程中，sortBorder之后的元素就完全不需要比较了，肯定是有序的。
     * 时间复杂度分析： O(n^2)
     * @param ary 数组
     * @return 循环执行次数
     */
    private static int bubbleSort03(int[] ary){
        int tmp = 0;
        int count = 0;
        //记录最后一次交换的位置
        int lastChangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = ary.length - 1;
        for (int i = 0; i < ary.length; i++) {
            // 每一轮初始状态都是true，有序标记(期望数组有序)
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (ary[j] > ary[j+1]) {
                    tmp = ary[j];
                    ary[j] = ary[j+1];
                    ary[j+1] = tmp;
                    // 如果存在元素交换，就表示无序
                    isSorted = false;
                    // 把无序数列的边界更新为最后一次交换元素的位置
                    lastChangeIndex = j;
                }
                count ++;
            }
            System.out.println("第"+(i+1)+"遍排序结果："+Arrays.toString(ary));
            sortBorder = lastChangeIndex;
            if(isSorted){
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[] a = {3,4,2,1,5,6,7,8};

//        int count = bubbleSort01(a); // 28
//        int count = bubbleSort02(a); // 22
        int count = bubbleSort03(a); // 10

        System.out.println("最终结果："+Arrays.toString(a));
        System.out.println("数组长度："+a.length+", 排序次数："+count);

    }

}
