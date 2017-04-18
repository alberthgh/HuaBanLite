package com.albert.huabanlite.Entity.event;

/**
 * Created by alberthuang on 2017/3/21.
 */

public class SwitchTypeEvent {

    private String type, title;

    public SwitchTypeEvent(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
