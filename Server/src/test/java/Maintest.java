import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * @className: Maintest
 * @Package: PACKAGE_NAME

 * @description:
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-07 21:49
 **/
public class Maintest {

    @Test
    public void t1() {
        for (Thread.State value : Thread.State.values()) {
            System.out.println("value = " + value);
        }
    }

    @Test
    public void t2() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("1", null);
        hashMap.put("2", null);
        hashMap.put("3", null);
        hashMap.put("4", null);

        Set<Object> keySet = hashMap.keySet();
        for (Object name : keySet) {
            System.out.print(name+" ");
        }
    }
}
