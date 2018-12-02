import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream {
    //写一个方法找偶数，返回一个新的list包含结果
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6);
        List<Integer> list2 = new ArrayList<>();
        System.out.println(search(list1, list2));
    }
    public static List<Integer> search(List<Integer> list1, List<Integer> list2){
        for(Integer num : list1){
            if(num%2==0){
                list2.add(num);
            }
        }
        return list2;
    }
}
