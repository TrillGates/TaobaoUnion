package com.sunofbeaches.taobaounion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public static void main(String[] args) {
        //        int max = getMax();
        //        System.out.println(max);
        //
        int[] nums = new int[]{2,1,4,5,3,1,1,3};
        maxAddition(nums);
    }

    public static int maxAddition(int[] data) {
        //如果没人预约，则返回0
        if(data.length == 0) {
            return 0;
        }
        //如果一个人预约
        if(data.length == 1) {
            return data[0];
        }
        //如果二个人预约
        if(data.length == 2) {
            return Math.max(data[0],data[1]);
        }
        //3个以上的情况
        //都有拒绝和接受的case
        List<Integer> perAdditaion = new ArrayList<>();
        //====================================================== i = 0
        //第一个拒绝
        //==>0
        //perAdditaion.add(0);
        //第一个接受
        //==>1
        //perAdditaion.add(data[0]);
        //====================================================== i = 1
        //1、第一个接受，第二个拒绝
        //==>2
        //perAdditaion.add(perAdditaion.get(1));
        //2、第一个拒绝，第二个拒绝
        //==>3
        //perAdditaion.add(perAdditaion.get(0));
        //3、第一个拒绝，第二个接受
        //==>4
        //perAdditaion.add(perAdditaion.get(0) + data[1]);
        //======================================================= i = 2
        //1、第二个接受，第三个拒绝
        //==>5
        //perAdditaion.add(perAdditaion.get(4));
        //2、第二个拒绝，第三个拒绝
        //==>6
        //perAdditaion.add(Math.max(perAdditaion.get(2),perAdditaion.get(3)));
        //2、第二个拒绝，第三个接受
        //==>7
        //perAdditaion.add(perAdditaion.get(6) + data[2]);
        //======================================================= i = 3
        //1、第三个接受，第四个拒绝
        //==>8
        //perAdditaion.add(perAdditaion.get(7));
        //2、第三个拒绝，第四个拒绝
        //==>9
        //perAdditaion.add(Math.max(perAdditaion.get(5),perAdditaion.get(6)));
        //3、第三个拒绝，第四个接受
        //==>10
        //perAdditaion.add(perAdditaion.get(9) + data[3]);
        //========================================================= i = 4
        //1、第四个接受，第五个拒绝
        //==>11
        //perAdditaion.add(perAdditaion.get(10));
        //2、第四个拒绝，第五个拒绝
        //==>12
        //perAdditaion.add(Math.max(perAdditaion.get(8),perAdditaion.get(9)));
        //3、第四个拒绝，第五个接受
        //==>13
        //perAdditaion.add(perAdditaion.get(12) + data[4]);
        //====================================================== i = ....
        //1、第i个接受，第i+1个拒绝
        //==> 3*i -1
        //perAdditaion.add(perAdditaion.get(3*i-2));
        //2、第i个拒绝，第i+1个拒绝
        //==> 3*i
        //perAdditaion.add(Math.max(perAdditaion.get(3*(i-1)-1),perAdditaion.get(3*(i-1))));
        //3、第i个拒绝，第i+1个接受
        //==> 3*i+1
        //perAdditaion.add(perAdditaion.get(3*i) + data[i]);
        // ....
        //求出列表的最大值即可

        //实现
        for(int i = 0; i < data.length; i++) {
            if(i == 0) {
                //接受
                perAdditaion.add(0);
                //拒绝
                perAdditaion.add(data[0]);
            } else if(i == 1) {
                //1、第一个接受，第二个拒绝
                //==>2
                perAdditaion.add(perAdditaion.get(1));
                //2、第一个拒绝，第二个拒绝
                //==>3
                perAdditaion.add(perAdditaion.get(0));
                //3、第一个拒绝，第二个接受
                //==>4
                perAdditaion.add(perAdditaion.get(0) + data[1]);
            } else {
                //1、第i个接受，第i+1个拒绝
                //==> 3*i -1
                perAdditaion.add(perAdditaion.get(3 * i - 2));
                //2、第i个拒绝，第i+1个拒绝
                //==> 3*i
                perAdditaion.add(Math.max(perAdditaion.get(3 * (i - 1) - 1),perAdditaion.get(3 * (i - 1))));
                //3、第i个拒绝，第i+1个接受
                //==> 3*i+1
                perAdditaion.add(perAdditaion.get(3 * i) + data[i]);
            }
        }
        Collections.sort(perAdditaion);
        Integer mini = perAdditaion.get(0);
        Integer  max = perAdditaion.get(perAdditaion.size() - 1);
        System.out.println("max is === > " + max);
        System.out.println("mini is === > " + mini);
        return max;
    }

}