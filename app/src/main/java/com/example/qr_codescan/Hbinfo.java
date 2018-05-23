package com.example.qr_codescan;

/**
 * Created by x on 2018/5/8.
 */

public class Hbinfo {
    private int Id;
    private String Ph;
    private String Cpmc;
    private String Date;
    private String Jth;
    private String Czy;
    private String Lpsl;
    private String Blpsl;

    public Hbinfo(int id,String ph,String cpmc,String date,String jth,String czy,String lpsl,String blpsl) {
        this.Id = id;
        this.Ph = ph;
        this.Cpmc = cpmc;
        this.Date = date;
        this.Jth = jth;
        this.Czy = czy;
        this.Lpsl = lpsl;
        this.Blpsl = blpsl;
    }

    public int getId() {
        return Id;
    }
    public String getPh(){
        return Ph;
    }
    public String getCpmc() {
        return Cpmc;
    }
    public String getDate() {
        return Date;
    }
    public String getJth() {
        return Jth;
    }
    public String getCzy() {
        return Czy;
    }
    public String getLpsl() {
        return Lpsl;
    }
    public String getBlpsl() {
        return Blpsl;
    }

    public void setId(int id) {
        this.Id = id;
    }
    public void setPh(String ph) {
        this.Ph = ph;
    }
    public void setCpmc(String cpmc) {
        this.Cpmc = cpmc;
    }
    public void setDate(String date) {
        this.Date = date;
    }
    public void setJth(String jth) {
        this.Jth = jth;
    }
    public void setCzy(String czy) {
        this.Czy = czy;
    }
    public void setLpsl(String lpsl) {
        this.Lpsl = lpsl;
    }
    public void setBlpsl(String blpsl) {
        this.Blpsl = blpsl;
    }
}
