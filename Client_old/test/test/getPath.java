/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;

/**
 *
 * @author NattapatN
 */
public class getPath {
    public static void main(String [] args){
        File file =new File("media/test.mp4");
        System.out.println(file.getPath());
    }
}
