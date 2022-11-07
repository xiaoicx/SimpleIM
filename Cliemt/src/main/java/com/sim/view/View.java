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

                    //登录
                    if (true) {
                        System.out.println("===========welcom [user " + userId + " ]=============");
                        while (loop) {
                            System.out.println("\n=========IM sub menu [user" + userId + "]=======");
                            System.out.println("\t\t1 show online of user list");
                            System.out.println("\t\t2 send group of msg");
                            System.out.println("\t\t3 send private chat of msg");
                            System.out.println("\t\t4 send local of file");
                            System.out.println("\t\t9 Exit");

                            key = Utility.readInt(0);
                            switch (key) {
                                case 1:
                                    System.out.println("\t\t1 show online of user list");
                                    break;
                                case 2:
                                    System.out.println("\t\t2 send group of msg");
                                    break;
                                case 3:
                                    System.out.println("\t\t3 send private chat of msg");
                                    break;
                                case 4:
                                    System.out.println("\t\t4 send local of file");
                                    break;
                                case 9:
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("Fail Input key!!!");
                                    continue;
                            }
                        }
                    } else {
                        System.out.println("login fial!!!");
                    }
                    break;
                case 9:

                    loop = false;
                    break;
                default:
                    System.out.println("Fail Input key!!!");
                    continue;
            }
        }
        System.out.println("exit system!!!");
    }
}
