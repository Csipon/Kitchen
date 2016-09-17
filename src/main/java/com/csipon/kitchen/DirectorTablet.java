package com.csipon.kitchen;


import com.csipon.kitchen.ad.Advertisement;
import com.csipon.kitchen.ad.StatisticAdvertisementManager;
import com.csipon.kitchen.statistic.StatisticEventManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    private DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

    public void printAdvertisementProfit() {
        double total = 0d;
        ConsoleHelper.writeMessage("===============================");
        for (Map.Entry<Date, Double> entry : StatisticEventManager.getInstance().getAdRevenue().entrySet()) {
            double profit = entry.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f", df.format(entry.getKey()), profit));
            total += profit;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f", total));
        ConsoleHelper.writeMessage("===============================");
    }

    public void printCookWorkloading() {
        ConsoleHelper.writeMessage("===============================");
        for (Map.Entry<Date, Map<String, Integer>> entry : StatisticEventManager.getInstance().getCookWorkload().entrySet()) {
            ConsoleHelper.writeMessage(df.format(entry.getKey()));
            for (Map.Entry<String, Integer> cooksEntry : entry.getValue().entrySet())
            {
                ConsoleHelper.writeMessage(String.format("%s - %d min", cooksEntry.getKey(), cooksEntry.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
        ConsoleHelper.writeMessage("===============================");
    }

    public void printActiveVideoSet() {
        List<Advertisement> activeVideos = StatisticAdvertisementManager.getInstance().getActiveVideos();
        Collections.sort(activeVideos, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        ConsoleHelper.writeMessage("===============================");
        for (Advertisement advertisement : activeVideos) {
            ConsoleHelper.writeMessage(String.format("%s - %d", advertisement.getName(), advertisement.getHits()));
        }
        ConsoleHelper.writeMessage("===============================");
    }
    public void printArchivedVideoSet() {
        List<Advertisement> inactiveVideos = StatisticAdvertisementManager.getInstance().getInactiveVideos();
        Collections.sort(inactiveVideos, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        ConsoleHelper.writeMessage("===============================");
        for (Advertisement advertisement : inactiveVideos) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
        ConsoleHelper.writeMessage("===============================");
    }
}