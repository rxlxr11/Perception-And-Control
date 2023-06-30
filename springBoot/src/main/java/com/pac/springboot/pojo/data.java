package com.pac.springboot.pojo;

import lombok.*;

import java.util.LinkedList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class data {
    private int id;
    private float temp;
    private float setting_temp;
    private String compressor;
    private boolean[] drawer;
    public static float[] temp_array = new float[20];
    public static float[] setting_array = new float[20];
}
