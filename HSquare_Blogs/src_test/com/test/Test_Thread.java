package com.test;

public class Test_Thread {  
    public static void main(String[] args) {  
        final Outputter output = new Outputter();  
        new Thread() {  
            public void run() {  
                output.output("11111111111111111111111111111111111111111111111111111111111111111111111111111111111");  
            };  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("2222222222222222222222222222222222222222222222222222222222222222222222222222222222");  
            };  
        }.start();  
    }  
}  
class Outputter {  
    public synchronized void output(String name) {  
        // 为了保证对name的输出不是一个原子操作，这里逐个输出name的每个字符  
        for(int i = 0; i < name.length(); i++) {  
            System.out.print(name.charAt(i));  
            // Thread.sleep(10);  
        }  
    }  
}
