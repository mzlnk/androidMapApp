package com.example.hostpitalsMap.models;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Hospital implements Serializable {
    private String id;
    private String name;
    private String address;
    private int x;
    private int y;
    private ArrayList<HospitalKind> kindsAvailable = new ArrayList<>();
    private int reviewsCount;
    private int reviewsSum;
    private String info;

    public Hospital(String id,String name, int x, int y, String address){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.reviewsCount = 0;
        this.reviewsSum = 0;
        this.address = address;
    }

    public Hospital(String id, String name, int x, int y, String address, ArrayList<HospitalKind> kinds, String info){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.reviewsCount = 0;
        this.reviewsSum = 0;
        this.address = address;
        this.kindsAvailable = kinds;
        this.info = info;
    }

    public void addAvailableKind(HospitalKind kind){
        this.kindsAvailable.add(kind);
    }

    public String getId(){return this.id;}

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getAddress() {
        return address;
    }

    public double getMark(){
        return ((double) reviewsSum/reviewsCount);
    }

    public ArrayList<HospitalKind> getKindsAvailable() {
        return kindsAvailable;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void addMark(int mark){
        this.reviewsSum += mark;
        this.reviewsCount++;
    }

    public String getInfo() {
        return info;
    }
}