package com.csipon.kitchen.statistic.event;

import java.util.Date;

public interface EventDataRow {
    public EventType getType();
    public Date getDate();
    int getTime();
}
