import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

//        Set<Object> keySet = hashMap.keySet();
//        for (Object name : keySet) {
//            System.out.print(name+" ");
//        }
//        for (Map.Entry<Object, Object> objectEntry : hashMap.entrySet()) {
//            System.out.println(objectEntry.getKey());
//            System.out.println(objectEntry.getValue());
//        }

//        Iterator<Object> iterator = hashMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().toString());
//        }

        Iterator<Object> iterator = hashMap.values().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }

    }

    @Test
    public void t3(){
        Date date = new Date();
        System.out.println(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Y-m-d HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);

        System.out.println(LocalDateTime.now());


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("Y-M-d HH:mm:ss");
        String s = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(s);
    }
}
