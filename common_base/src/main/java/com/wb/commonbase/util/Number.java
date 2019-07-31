package com.wb.commonbase.util;

import java.math.RoundingMode;

/**
 * 说明：Number
 * <p/>
 * 作者：fanly
 * <p/>
 * 类型：Class
 * <p/>
 * 时间：2018/11/27 13:16
 * <p/>
 * 版本：verson 1.0
 */
public class Number {

    private String value;

    private Number(String value){
        double d = NumberUtils.toDouble(value);
        this.value = NumberUtils.toString(d);
    }

    public static Number value(String value){
        return new Number(value);
    }

    public Number add(double a){
        value = NumberUtils.toString(NumberUtils.addDouble(NumberUtils.toString(a),value));
        return this;
    }

    public Number add(String a){
        value = NumberUtils.toString(NumberUtils.addDouble(a,value));
        return this;
    }

    public Number sub(String a){
        value = NumberUtils.toString(NumberUtils.subDouble(value,a));
        return this;
    }

    public Number sub(double a){
        value = NumberUtils.toString(NumberUtils.subDouble(value,NumberUtils.toString(a)));
        return this;
    }

    public Number multipy(double a){
        value = NumberUtils.toString(NumberUtils.multipy(a,toDouble()));
        return this;
    }

    public Number multipy(String a){
        value = NumberUtils.toString(NumberUtils.multipy(NumberUtils.toDouble(a),toDouble()));
        return this;
    }

    public Number divide(String a,RoundingMode mode){
        value = NumberUtils.toString(NumberUtils.divide(NumberUtils.toDouble(value),NumberUtils.toDouble(a),mode));
        return this;
    }

    public Number divide(double a,RoundingMode mode){
        value = NumberUtils.toString(NumberUtils.divide(NumberUtils.toDouble(value),a,mode));
        return this;
    }

    public int toInt(){
        return toInt(0);
    }

    public int toInt(int def){
        retainDecimal(0);
        return NumberUtils.toInt(value,def);
    }

    public double toDouble(){
        return toDouble(0);
    }

    public double toDouble(double def){
        return NumberUtils.toDouble(value,def);
    }

    public Number retainDecimal(int n){
        value = NumberUtils.saveDecimal(NumberUtils.toDouble(value),n);
        return this;
    }

    public String value(){
        return value;
    }

}
