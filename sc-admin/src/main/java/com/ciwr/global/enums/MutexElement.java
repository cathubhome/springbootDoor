package com.ciwr.global.enums;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 互斥对象
 * Time:Create on 2018/7/30 10:00
 */
@SuppressWarnings("all")
public class MutexElement {

    /**
     * 业务唯一标识
     */
    private String uniqueNum;

    /**
     * 锁定业务对象类型
     */
    private MutexElementType type;

    public MutexElement(String uniqueNum, MutexElementType type) {
        this.uniqueNum = uniqueNum;
        this.type = type;
    }

    public String getUniqueNum() {
        return uniqueNum;
    }

    public void setUniqueNum(String uniqueNum) {
        this.uniqueNum = uniqueNum;
    }

    public MutexElementType getType() {
        return type;
    }

    public void setType(MutexElementType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MutexElement{" +
                "uniqueNum='" + uniqueNum + '\'' +
                ", type=" + type +
                '}';
    }
}
