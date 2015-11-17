package Rename;

/**
 * Created by 承俞嘉 on 2015/11/16.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Rename {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入文件夹路径：");
        String s = sc.next();
        File path = new File(s);
        if (path.isDirectory()) {
            System.out.print("输入对照表路径：");
            String t = sc.next();//可改为固定路径
            File list = new File(t);
            if (list.isFile()) {
                BufferedReader reader = null;
                File[] files = path.listFiles();
                try {
                    reader = new BufferedReader(new FileReader(list));
                    String temp = null;
                    while ((temp = reader.readLine()) != null) {
                        String oldName = temp.substring(0, temp.indexOf(','));
                        String newName = temp.substring((temp.indexOf(',') + 1));
                       // System.out.println(oldName + newName);
                        for (int i = 0; i < files.length; i++) {
                            File file = files[i];
                            if (file.isFile()) {
                                String Name = file.getName();
                                String prefix = Name.substring(0, Name.lastIndexOf('.'));
                                if (prefix.contentEquals(oldName)) {
                                    String postfix = Name.substring(Name.lastIndexOf('.'));
                                    System.out.println("重命名" + prefix + postfix + "为" + newName + postfix + ".");
                                    File dest = new File(s + "/" + newName + postfix);
                                    file.renameTo(dest);
                                }
                            }
                        }
                    }
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e1) {
                        }
                    }
                }
                System.out.print("重命名完成！");
            } else {
                System.out.print("您输入的不是文件！");
            }
        } else {
            System.out.print("您输入的不是目录！");
        }
    }
}

