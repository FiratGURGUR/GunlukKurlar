package com.gurgur.gunlukkurlar;

public class ModelDeger {

    private String BirimAdi;
    private String BirimAlis;
    private String BirimSatis;
    private String BirimTur;

    public ModelDeger(String birimAdi, String birimAlis, String birimSatis, String birimTur) {
        BirimAdi = birimAdi;
        BirimAlis = birimAlis;
        BirimSatis = birimSatis;
        BirimTur = birimTur;
    }

    public String getBirimAdi() {
        return BirimAdi;
    }

    public void setBirimAdi(String birimAdi) {
        BirimAdi = birimAdi;
    }

    public String getBirimAlis() {
        return BirimAlis;
    }

    public void setBirimAlis(String birimAlis) {
        BirimAlis = birimAlis;
    }

    public String getBirimSatis() {
        return BirimSatis;
    }

    public void setBirimSatis(String birimSatis) {
        BirimSatis = birimSatis;
    }

    public String getBirimTur() {
        return BirimTur;
    }

    public void setBirimTur(String birimTur) {
        BirimTur = birimTur;
    }
}
