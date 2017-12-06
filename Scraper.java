package com.company;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

class Scraper {

    private  static final Pattern v = Pattern.compile("[\\s\\S]*.?\\s.*?v");
    private  static final Pattern w = Pattern.compile("[\\s\\S]*.?\\s.*?w");
    private  static final Pattern c = Pattern.compile("[\\s\\S]*.?\\s.*?c");
    private  static final Pattern e = Pattern.compile("[\\s\\S]*.?\\s.*?e");
//    private static final Pattern sentencePattern =
//            Pattern.compile("(((\\d+\\.\\s*)*)([^?!.\\(]|\\([^\\)]*\\))*[.?!])");

    Scraper(String url) throws IOException {
        File file = downloadFile(url);
        FileInputStream inputFile = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile));
        StringBuffer buffer = new StringBuffer();
        while(reader.ready()){
            buffer = buffer.append(reader.readLine());
        }
        HtmlToTextPattern cleanText = new HtmlToTextPattern(buffer.toString());
        if (haveArgs(url)) {

            if(v.matcher(url).find()){
                System.out.println("Have v argument: ");
            }
            if(w.matcher(url).find()){
                System.out.println("Have w argument: words on web page "+countNumberOfWords(cleanText.getText()));
            }
            if(c.matcher(url).find()){
                System.out.println("Have c argument: characters on web page "+countNumberOfChar(cleanText.getText()));
            }
            if(e.matcher(url).find()){
                String[] words = (url.split(" "))[1].split(",");//is not work, yet.
                for (String word : words) {
                    System.out.println("Have e argument: " + word);
                }
//                 for (int i=0; i<words.length;i++){
//
//                     Pattern word = Pattern.compile("((Apple)+)");
//                     String[] buffers = cleanText.getText().split("(\\d+\\.\\s*)*[А-ЯA-Z\\@]((т.д.|т.п.|пр.)\\s+[^А-Я]|(т.е.|т.о.)|[^?!.\\(]|\\([^\\)]*\\))*[.?!]((?<=\\sт\\.)д\\.|п\\.)?");
//                     System.out.println(buffers[0]);
 //                       for (int j=0; j<buffers.length;j++){
//                            if(word.matcher(buffers[j]).find()){
//                                System.out.println(buffers[j]);
//                            }
//                        }
//                 }


            }
        }
    }


    private File downloadFile(String address) throws IOException {
        String input;
        URL url;
        if (address.split(" ").length<1) {
            url = new URL(address);
        }else {
            String[] commandLine = address.split(" ");
            url = new URL(commandLine[0]);
        }
        String name = "tempFile.html";
        File htmlFile = new File(name);
        if (!htmlFile.exists()) {
            htmlFile.createNewFile();
        }
        FileWriter writer = new FileWriter(htmlFile);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStreamReader inFile = new InputStreamReader(connection.getInputStream());
        BufferedReader br = new BufferedReader(inFile);
        while ((input = br.readLine()) != null) {
            writer.write(input);
        }
        return htmlFile;
    }

    private boolean haveArgs(String commandLine){
        // if we have any arguments
        return commandLine.split(" ").length > 1;
    }

    private int countNumberOfWords(String buffer){
        return buffer.split(" ").length;
    }
    private int countNumberOfChar(String buffer){
        return buffer.length();
    }
//    public String extractSentences(StringBuffer buffer){
//        StringBuffer sentences;
//        if()
//        System.out.println(sentences);
//    }
}
