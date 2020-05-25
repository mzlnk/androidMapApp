package com.example.hostpitalsMap.models;

public enum HospitalKind {
    NOT_A_HOSPITAL("Wybierz oddział", 0), //hakerskie rozwiazanie :))) ale nalezy o nim pamietac
    SOR("SOR",1),
    ZAKAZNY("Zakaźny",2),
    DZIECIECY("Dziecięcy",3),
    OIOM("Intensywnej terapii",4),
    COVID19("COVID19!", 5);

    private String DisplayName;
    private int value;

    public String getDisplayName() {
        return DisplayName;
    }
    public int getValue(){
        return value;
    }

    HospitalKind(String DisplayName, int value){
        this.DisplayName = DisplayName;
        this.value = value;
    }
}
