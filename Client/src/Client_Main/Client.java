/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Main;

import Module.*;
/**
 *
 * @author NattapatN
 */
public class Client {
    public static void main(String []args){
        ConnectServer a = new ConnectServer("127.0.0.1",9000);
        int port = a.getNewPort();
        System.out.println("[ Connected ]");
        System.out.println("Host Address : 127.0.0.1");
        System.out.println("Port : "+port);
    }
    
}
