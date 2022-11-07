package com.sim.view;

import com.sim.Utils.Utility;

/**
 * @className: View
 * @Package: com.sim.view

 * @description:
 * @author: xiaoqi
 * @version v1.0
 * @create: 2022-11-07 21:54
 **/
@SuppressWarnings("all")
public class View {
    private boolean loop = true;
    private int key = 0;

    public void mainView() {

        while (loop) {
            System.out.println("============SimpleIM=============");
            System.out.println("\t\t1 Login");
            System.out.println("\t\t9 Exit");
            System.out.print("please input select ID:");

            key = Utility.readInt(0);

            switch (key) {
                case 1:
                    System.out.print("input login id:");
                    String userId = Utility.readString(50);
                    System.out.print("input login id pwd:");
                    String pwd = Utility.readString(50);



                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Fail Input key!!!");
                    continue;
            }
        }
    }

}
