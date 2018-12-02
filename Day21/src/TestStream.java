import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
        List list2 = list1.stream().filter(i -> i%2 == 0).map(i -> i*2).collect(Collectors.toList());
        System.out.println(list2);
    }
}
