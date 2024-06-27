import integerlist.IntegerList;
import integerlist.IntegerListRealization;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        IntegerList list = new IntegerListRealization();
        for (int i = 0; i < 50; i++) {
            list.add(new Random().nextInt(1000));
        }
        System.out.println(Arrays.toString(list.toArray()));
        list.clear();
    }
}
