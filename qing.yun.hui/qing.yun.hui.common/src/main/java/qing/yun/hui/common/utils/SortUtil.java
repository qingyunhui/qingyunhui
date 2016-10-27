package qing.yun.hui.common.utils;

/***
 ** @Description: sort排序
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:13:55 AM
 ** @version: V1.0
 ***/
public class SortUtil {
	
	/***
	 ** 数组打印
	 ** @author qing.yunhui
	 ** @email  280672161@.qq.com
	 ** @date  Jul 25, 20155:14:37 PM
	 ***/
	public static void print(int[] srcs){
		for(int i=0;i<srcs.length;i++){
			if(i==srcs.length-1){
				System.out.print(srcs[i]);
			}else{
				System.out.print(srcs[i]+",");
			}
		}
		System.out.println("\n----------------------------------------------\n");
	}
	
	/***
	 ** 冒泡排序算法
	 ** @author qing.yunhui
	 ** @email  280672161@.qq.com
	 ** @date  Jul 25, 20155:10:13 PM
	 ***/
	public static void doBubbleSort(int[] src) {
		int len = src.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int temp;
				if (src[i] > src[j]) {
					temp = src[j];
					src[j] = src[i];
					src[i] = temp;
				}
			}
		}
	}
	
	/***
	 ** 选择排序:对待排序的记录序列进行 n-1 遍的处理，第 1 遍处理是将 L[1..n] 中最小者与 L[1]交换位置，第 2 遍处理是将 L[2..n]中最小者与 L[2]交换位置，......，第 i 遍
	           处理是将 L[i..n]中最小者与 L[i]交换位置。这样，经过 i 遍处理之后，前 i 个记录的位置就已 经按从小到大的顺序排列好了。 选择排序与冒泡排序的区别在：冒泡排序每次比较后，
	           如果发现顺序不对立即进行交换，而 选择排序不立即进行交换，而是找出最小的元素后再进行交换。
	 ** @author qing.yunhui
	 ** @email  280672161@.qq.com
	 ** @date  Jul 25, 20155:18:55 PM
	 ***/
	public static void doChooseSort(int[] src) {
		int len = src.length;
		int temp;
		for (int i = 0; i < len; i++) {
			temp = src[i];
			int j;
			int samllestLocation = i;
			// 最小数的下标
			for (j = i + 1; j < len; j++) {
				if (src[j] < temp) {
					temp = src[j];
					// 取出最小 值 
					samllestLocation = j;// 取出最小值所在下标 
					src[samllestLocation] = src[i];
					src[i] = temp;
				}
			}
		}
	}
	
	/***
	 ** 插入排序:每一步都将一个待排数据按其大小插入到已经排序的数据中的适当位置，直到全部插 入完毕。插入排序方法分直接插入排序和折半插入排序两种，这里只介绍直接插入排序，
	           折半插入排序留 到“查找”内容中进行。
	 ** @author qing.yunhui
	 ** @email  280672161@.qq.com
	 ** @date  Jul 25, 20155:25:35 PM
	 ***/
	public static void doInsertSort(int[] src) {
		int len = src.length;
		for (int i = 1; i < len; i++) {
			int j;
			int temp = src[i];
			for (j = i; j > 0; j--) {
				if (src[j - 1] > temp) {
					src[j] = src[j - 1];
				}
				else  break;// 如果当前的数，不小前面的 数，那就说明不小于 前面所有的 数， 因为前面已经是排好了序的 ，所以直接通出当前 一轮的比较
			}
			src[j] = temp;
		}
	}
	
	/***
	 ** 快速排序:是对冒泡排序的一种改进, 它的基本思想是： 通过一趟排序将要排序的数据分割成独立的两部分， 其中一部分的所有数据都比另外一部分的所有数据都要小，
	 *  然后再按次方法对这两部分数据分别进行快速 排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。quickSort(pData, 0, pData.length - 1);
	 ** @author qing.yunhui
	 ** @email  280672161@.qq.com
	 ** @date  Jul 25, 20155:33:21 PM
	 ***/
	public static int[] quickSort(int[] pData, int left, int right) {
		int i = left, j = right;
		int middle, strTemp;
		middle = pData[(left + right) / 2];
		do {
			while ((pData[i] < middle) && (i < right))
				i++;
			while ((pData[j] > middle) && (j > left))
				j--;
			if (i <= j) {
				strTemp = pData[i];
				pData[i] = pData[j];
				pData[j] = strTemp;
				i++;
				j--;
			}
		} while (i <= j);
		for (int t = 0; t < pData.length; t++)
			System.out.print(pData[t] + " ");
		System.out.println("");
		if (left < j) {
			quickSort(pData, left, j);
		}
		if (right > i)
			quickSort(pData, i, right);
		return pData;
	}

}
