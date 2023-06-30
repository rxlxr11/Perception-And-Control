package com.pac.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class result {
    public int code;
    public String message;
    public Object value;
    public float[] temp;
    public float[] setting_temp;
    public  String[] categories={"1","2","3","4","5","6","7","8","9","10","1","2","3","4","5","6","7","8","9","10"};
}
