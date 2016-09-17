package com.csipon.kitchen;

import com.csipon.kitchen.kitchen.Cook;
import com.csipon.kitchen.kitchen.Order;
import com.csipon.kitchen.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by HOUSE on 9/17/2016.
 */
public class RestaurantManager {
//    time which new thread will be sleep after made order
    private final static int ORDER_CREATING_INTERVAL = 100;
//    list order
    private final static LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();
//    printer for director
    private PrinterStatisticForDirector printerStat = new PrinterStatisticForDirector();
//    Waiter orders
    private Waiter waiter = new Waiter();
//    defualt name cookers
    private String nameFirstCooker = "Amigo";
    private String nameSecondCooker = "Pablo";

    public void doStart(){
        Locale.setDefault(Locale.ENGLISH);

        Cook cook0 = new Cook(nameFirstCooker);
        Cook cook1 = new Cook(nameSecondCooker);
        cook0.setQueue(QUEUE);
        cook1.setQueue(QUEUE);
        cook0.addObserver(waiter);
        cook1.addObserver(waiter);

        List<Tablet> tabletList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(QUEUE);
            tabletList.add(tablet);
        }

        ThreadLocalRandom generatorTask = new ThreadLocalRandom(tabletList, ORDER_CREATING_INTERVAL);

        Thread thread = new Thread(generatorTask);
        Thread cookThread0 = new Thread(cook0);
        Thread cookThread1 = new Thread(cook1);

//        start all threads
        startThreads(thread, cookThread0, cookThread1);

        try {
            Thread.sleep(1000); }
        catch (InterruptedException e)
        {}

//       stop all threads
        killThreads(thread, cookThread0, cookThread1);

        printerStat.printStatistic();
    }

    public void setNameSecondCooker(String nameSecondCooker) {
        this.nameSecondCooker = nameSecondCooker;
    }

    public void setNameFirstCooker(String nameFirstCooker) {
        this.nameFirstCooker = nameFirstCooker;
    }

    private void startThreads(Thread t, Thread...ts){
        t.start();
        if (ts != null){
            for (Thread temp : ts){
                temp.start();
            }
        }
    }

    private void killThreads(Thread t, Thread...ts) {
        t.interrupt();
        if (ts != null) {
            for (Thread temp : ts) {
                temp.interrupt();
            }
        }
    }


}
