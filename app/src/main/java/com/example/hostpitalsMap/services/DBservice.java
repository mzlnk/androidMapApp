package com.example.hostpitalsMap.services;

import android.util.Log;
import com.example.hostpitalsMap.models.Hospital;
import com.example.hostpitalsMap.models.HospitalKind;
import java.util.ArrayList;
import java.util.Arrays;

public class DBservice{

    private ArrayList<HospitalKind> testowy1 = new ArrayList<>();
    private ArrayList<HospitalKind> testowy2 = new ArrayList<>();
    private ArrayList<HospitalKind> testowy3 = new ArrayList<>();
    private ArrayList<HospitalKind> testowy4 = new ArrayList<>();

    public DBservice(){
        testowy1.addAll(Arrays.asList(HospitalKind.values()).subList(1, 5));
        testowy2.addAll(Arrays.asList(HospitalKind.values()).subList(2, 5));
        testowy3.addAll(Arrays.asList(HospitalKind.values()).subList(3, 5));
        testowy4.addAll(Arrays.asList(HospitalKind.values()).subList(4, 5));
    }

    public void addMark (String id, int mark){
        Log.d("MARK", "dziala!");
    }

    public ArrayList<Hospital> getHospitalList(HospitalKind kind){
        ArrayList<Hospital> list = new ArrayList<>();

        switch (kind){
            case SOR:
                for(int i=0; i<10; i++) {
                    list.add(new Hospital("id", "test sor ".concat(String.valueOf(i)), 45 + i, 15 + i, "losowy adres", testowy1,""));
                }
                break;
            case ZAKAZNY:
                for(int i=0; i<10; i++) {
                    list.add(new Hospital("id", "test zakazny ".concat(String.valueOf(i)), 45 - i, 15 - i, "losowy adres", testowy2,"zamknięty do odwołania"));
                }
                break;
            case DZIECIECY:
                for(int i=0; i<10; i++) {
                    list.add(new Hospital("id", "test dziciecy ".concat(String.valueOf(i)), 60 + i, 5 + i, "losowy adres", testowy3,""));
                }
                break;
            default:
                for(int i=0; i<10; i++) {
                    list.add(new Hospital("id", "test oiom ".concat(String.valueOf(i)), 60 - i, 5 + i, "losowy adres", testowy4,""));
                }
                break;
        }

        return list;
    }
}
