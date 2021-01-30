package com.mqz.online.edit.web.dto;

import lombok.Data;


@Data
public class FileHisDTO {

    private String id;
    private String name;
    private int version;
    private double size;
    private long create_time;
    private long modify_time;
    private String download_url;

    private UserDTO creator;
    private UserDTO modifier;

    @Override
    public String toString() {
        return "FileHisDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", size=" + size +
                ", create_time=" + create_time +
                ", modify_time=" + modify_time +
                ", download_url='" + download_url + '\'' +
                ", creator=" + creator +
                ", modifier=" + modifier +
                '}';
    }
}
