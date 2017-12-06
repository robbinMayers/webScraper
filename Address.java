package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Address {
    private ArrayList<String> addressList = new ArrayList<>();

    private  static final Pattern addressPattern =
            Pattern.compile("^https://|http://[a-z1-9]*$");

    Address(String address) throws IOException {
        if (urlOrPath(address)) {
            setAddressList(address);
        } else {
            File folder = new File(address);
            setAddressList(findFileInFolder(folder));
        }
    }
    ArrayList<String> getAddressList() {
        return addressList;
    }

    private void setAddressList(ArrayList<String> addressList) {
        this.addressList = addressList;
    }

    private void setAddressList(String address) {
        this.addressList.add(address);
    }

    private boolean urlOrPath(String address){
        String[] addressArray = address.split(" ");

        Matcher matcher = addressPattern.matcher(addressArray[0]);
        return matcher.find();
    }
    private ArrayList<String> findFileInFolder(File folder) throws IOException {

        File[] folderEntries = folder.listFiles();
        ArrayList<String> addressList = null;
        if (folderEntries != null) {
            for (File entry : folderEntries) {
                if (entry.isDirectory()) {
                    findFileInFolder(entry);
                    continue;
                } //We catch file here.
                else {
                    FileInputStream inputStream = new FileInputStream(entry);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    addressList = new ArrayList<>();
                    while (reader.ready()) {
                        addressList.add(reader.readLine());
                    }
                    return addressList;
                }
            }
        }
        return null;
    }
}
