package com.nhommot.thitracnghiem.models;


public class Dataset {
private String label;
private int[] data;

// Getters và setters
public String getLabel() {
   return label;
}

public void setLabel(String label) {
   this.label = label;
}

public int[] getData() {
   return data;
}

public void setData(int[] data) {
   this.data = data;
}
}