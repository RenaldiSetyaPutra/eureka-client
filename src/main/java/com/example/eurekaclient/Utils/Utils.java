package com.example.eurekaclient.Utils;

import java.util.*;

public class Utils {

    public static void main(String[] args) {
        nilaiPrima();
        nilaiGanjil();
        nilaiGenap();
        nilaiFibonacci();
        heru();
        ilham();
        vikrie();
//        weird();
        randomString();
    }

    static void nilaiPrima(){
        int maxValue = 100;
        List<Integer> listPrima = new ArrayList<>();

        for (int i = 2; i < maxValue; i++){
            boolean isPrima = true;

            for (int j = 2; j < i; j++){
                if (i%j == 0){
                    isPrima = false;
                }
            }

            if (isPrima == true){
                listPrima.add(i);
            }
        }
        System.out.println("===== Bilangan Prima =====");
        System.out.println(listPrima);
        System.out.println("===== Bilangan Prima =====");
    }

    static void nilaiGanjil(){
        int input = 100;
        List<Integer> listGanjil = new ArrayList<>();

        for (int i = 0; i<100; i++){
            if (i%2 != 0){
                listGanjil.add(i);
            }
        }
        System.out.println("===== Bilangan Ganjil =====");
        System.out.println(listGanjil);
        System.out.println("===== Bilangan Ganjil =====");
    }

    static void nilaiGenap(){
        int input = 100;
        List<Integer> listGenap = new ArrayList<>();

        for (int i = 0; i<input; i++){
            if (i%2 == 0){
                listGenap.add(i);
            }
        }
        System.out.println("===== Bilangan Genap =====");
        System.out.println(listGenap);
        System.out.println("===== Bilangan Genap =====");
    }

    static void nilaiFibonacci(){
        long input1 = 0;
        long input2 = 1;
        int n = 20;
        List<Long> listFibonancci = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            listFibonancci.add(input1);
            long fibonancci = input1 + input2;

            input1 = input2;
            input2 = fibonancci;
        }
        System.out.println("===== Bilangan Fibonancci =====");
        System.out.println(listFibonancci);
        System.out.println("===== Bilangan Fibonancci =====");
    }

    static void heru(){
        int uang = 850000;
        double bunga = (double) 5 /100;
        int tahun = 1;

        int bayar = (int) (uang*bunga) * tahun;

        System.out.println("Bunga yang harus dibayar Heru dalam satu tahun = " + bayar);
    }

    static void ilham(){
        int harga = 165000;
        double diskon = (double) 15/100;
        int totalDiskon = (int) (harga * diskon);
        int totalBayar = harga - totalDiskon;
        int bayar = 150000;

        int kembalian = bayar - totalBayar;

        System.out.println("Kembalian Ilham = " + kembalian);
    }

    static void vikrie(){
        int gross = 144;
        int lusin = 12;

        int vikrie = 6 * gross;
        int tio = 4 * lusin;
        int david = 2 * gross;

        int dipinjam = tio + david;
        int sisa = vikrie - dipinjam;

        System.out.println("Total piring di tangan Vikri = " + sisa);
    }

    static void weird (){
        for (int i = 1; i<100; i++){
            if (i%2 != 0){
                System.out.println(i + " Ganjil weird");
            }
            if(i%2 == 0 && i%5==0){
                System.out.println(i + " Genap not weird");
            }
            if(i%2 == 0 && i%6 == 0 && i%20==0){
                System.out.println(i + " Genap weird");
            } if(i%2 == 0 && i > 20) {
                System.out.println(i + " Genap not weird");
            }
        }
    }

    static void randomString(){
        List<String> lisRandomString = new ArrayList<>();
        List<Integer>listNumber = new ArrayList<>();
        for (int i = 1; i<6; i++){
            int random_int = (int)Math.floor(Math.random() * (100 - 1 + 1) + 1);
            listNumber.add(random_int);
        }

        for (int i : listNumber){
            if(i%5==0){
                if (i <= 60){
                    lisRandomString.add(i + " Kurang");
                }

                if (i>60 && i<=70){
                    lisRandomString.add(i + " Cukup");
                }

                if (i>70 && i<=80){
                    lisRandomString.add(i + " Baik");
                }

                if (i>80){
                    lisRandomString.add(i + " Luar Biasa");
                }
            }
        }
        System.out.println(lisRandomString);
    }

    static void kelipatanDua(){
        int input = 100;
        List<Integer> listKelipatanDua = new ArrayList<>();

        for (int i = 1; i<=input; i++){
            if(i%2 == 0){
                listKelipatanDua.add(i);
            }
        }
        System.out.println(listKelipatanDua);
    }

    static void kelipatanEmpat(){
        int input = 100;
        List<Integer> listKelipatanEmpat = new ArrayList<>();

        for (int i = 1; i<=input; i++){
            if(i%4 == 0){
                listKelipatanEmpat.add(i);
            }
        }
        System.out.println(listKelipatanEmpat);
    }

    static void kelipatanLima(){
        int input = 100;
        List<Integer> listKelipatanLima = new ArrayList<>();

        for (int i = 1; i<=input; i++){
            if(i%5 == 0){
                listKelipatanLima.add(i);
            }
        }
        System.out.println(listKelipatanLima);
    }
}
