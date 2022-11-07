import org.junit.Test;

/**
 * @className: Maintest
 * @Package: PACKAGE_NAME

 * @description:
 * @author: xiaoqi
 * @version v1.0
 * @create: 2022-11-07 21:49
 **/
public class Maintest {

    @Test
    public void t1(){
        for (Thread.State value : Thread.State.values()) {
            System.out.println("value = " + value);
        }
    }
}
