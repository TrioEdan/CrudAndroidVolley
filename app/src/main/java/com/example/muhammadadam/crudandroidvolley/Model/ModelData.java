package com.example.muhammadadam.crudandroidvolley.Model;

public class ModelData {
    String npm, nama, prodi, kelas;

    public ModelData(){}

    public ModelData(String npm, String nama, String prodi, String kelas) {
        this.npm = npm;
        this.nama = nama;
        this.prodi = prodi;
        this.kelas = kelas;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
