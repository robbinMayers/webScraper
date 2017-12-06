package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{
        String s;
        if (args.length==0){
            System.out.println("Hello, please get me path with file or URL.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            s = reader.readLine();
        } else {
            StringBuilder comand = new StringBuilder();
            for (String arg : args) {
                comand.append(arg).append(" ");
            }
            s = comand.toString();
        }

        Address address = new Address(s);

            try {
                for(int i=0; i<address.getAddressList().size();i++) {
                    System.out.println("Now scraping : "+address.getAddressList().get(i));
                    Scraper scraper = new Scraper(address.getAddressList().get(i));
                }
            } catch (Exception e) {
                StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
                String message = "";
                if(stackTraceElements.length >= 2) {
                    StackTraceElement element = stackTraceElements[1];
                    String className = element.getClassName();
                    String methodName = element.getMethodName();
                    message = className + ": " + methodName;
                }
                System.out.println("Maybe, it's not Path or URL. Try again.");
                System.out.println("message: " + message);
            }
    }
}
