package com.dj.songs.network.model;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/23 11:38 AM
 */
public class IPModel {
    private int code;
    private IpData data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }
    public void setData(IpData data) {
        this.data = data;
    }
    public IpData getData() {
        return this.data;
    }
}
