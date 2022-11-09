package com.sim.view;

import com.sim.Utils.Utility;
import com.sim.service.MessageClientService;
import com.sim.service.UserClientService;

/**
 * @className: View
 * @Package: com.sim.view

 * @description:
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-07 21:54
 **/
@SuppressWarnings("all")
public class View {
    private boolean loop = true;
    private int key = 0;

    //用户客户端服务
    private UserClientService userClientService = new UserClientService();

    //用户消息服务
    private MessageClientService messageClientService = new MessageClientService();

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
                    if (userClientService.checkUser(userId,pwd)) {
                        System.out.println("===========welcom [user " + userId + " login success]=============");
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

                                    userClientService.onLineFriendList();
                                    break;
                                case 2:
                                    System.out.print("请输入消息内容: ");
                                    String groupContent = Utility.readString(1000);

                                    messageClientService.sendGroupMsg(groupContent,userId);
                                    break;
                                case 3:
                                    System.out.print("请输入私聊ID(在线): ");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入聊天内容: ");
                                    String OneToOneContent = Utility.readString(1000);

                                    //发送消息请求
                                    messageClientService.sendMsgToOne(OneToOneContent,userId,getterId);
                                    break;
                                case 4:
                                    System.out.println("\t\t4 send local of file");
                                    break;
                                case 9:
                                    userClientService.loginOut();
                                    loop = false;
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Fail Input key!!!");
                                    continue;
                            }
                        }
                    } else {
                        System.out.println("login fial!!!");
                        break;
                    }
                    break;
                case 9:
                    //退出登录
                    userClientService.loginOut();
                    loop = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Fail Input key!!!");
                    continue;
            }
        }
        System.out.println("exit system!!!");
    }
}
