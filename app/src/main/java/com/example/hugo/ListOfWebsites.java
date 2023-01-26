package com.example.hugo;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ListOfWebsites {
    private HashMap<String, Double> list;
    private final AssetManager assetManager;

    public ListOfWebsites(AssetManager assetManager) {
        this.assetManager = assetManager;
        loadValues("zoznam.txt");
    }

    public Double getWebsiteScore(String siteToSearch) {
        String site = siteToSearch.toLowerCase();

        if (siteToSearch.startsWith("www.")) {
            site = siteToSearch.substring(4);
        }

        if (this.list.get(site) == null) {
            return 5.0;
        } else {
            return this.list.get(site);
        }
    }

    @NonNull
    public String toString() {
        return "Toto je zoznam stránok";
    }

    public void loadValues(String fileName) {
        this.list = new HashMap<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("zoznam.txt")));

            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(",", 2);
                if (keyValuePair.length > 1) {
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
