package com.csipon.kitchen;

/**
 * Created by HOUSE on 9/17/2016.
 */
public class PrinterStatisticForDirector {
    private DirectorTablet directorTablet = new DirectorTablet();

    public void printStatistic() {
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}

