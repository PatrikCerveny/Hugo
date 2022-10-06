package com.example.hugo;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ListOfWebsites {
    private HashMap<String, Double> list;

    public ListOfWebsites() {
//        this.list = new HashMap<>();
        loadValues("zoznam.txt");
    }

    public Double getWebsiteScore(String siteToSearch) {
        if(siteToSearch.startsWith("www.")) {
            siteToSearch.substring(3);
        } if(this.list.get(siteToSearch) == null) {
            return 1.0;
        }
            return this.list.get(siteToSearch);
    }

    @NonNull
    public String toString() {
        return "Toto je zoznam stránok";
    }

    public void loadValues(String fileName) {
        this.list = new HashMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
          while ((line = reader.readLine()) != null) {
              String[] keyValuePair = line.split(",", 2);
              if (keyValuePair.length > 0) {
                  String key = keyValuePair[0];
                  Double value = Double.parseDouble(keyValuePair[1]);
                  this.list.put(key, value);
              }
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
