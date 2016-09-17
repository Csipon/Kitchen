package com.csipon.kitchen.ad;

public class Advertisement {

//    something content advertisement
    private Object content;
//    name video
    private String name;
//    amount for all video
    private long initialAmount;
//    count all video
    private int hits;
//    duration all video take in seconds
    private int duration;
//    amount per one displaying video
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName()
    {
        return name;
    }

    public int getDuration()
    {
        return duration;
    }

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }

    public int getHits()
    {
        return hits;
    }


//    method which decrement count video displaying, where hits little 0 throw unchecked exception
    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        if (hits == 1) { amountPerOneDisplaying += initialAmount % amountPerOneDisplaying; }
        hits--;
    }
}