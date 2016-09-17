package com.csipon.kitchen.ad;

import com.csipon.kitchen.ConsoleHelper;
import com.csipon.kitchen.statistic.StatisticEventManager;
import com.csipon.kitchen.statistic.event.NoAvailableVideoEventDataRow;
import com.csipon.kitchen.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
* class AdvertisementManager
* his job that doing selection best list video
* he is taking all list video advertisement and
* with help algorithm selection, select best
* list video in which minimum video, maximum profit
* and maximum of time showing advertisement
*
* */
public class AdvertisementManager {
//    single instance video storage
    private static final AdvertisementStorage storage = AdvertisementStorage.getInstance();
//    list with all video
    private List<Advertisement> videos = storage.list();
//    time which showing the advertisement
    private int timeSeconds;
//    maximum profit which we can take
    private long maxProfit = 0;
//    minimum time which remain after selection video
    private int minRemainingTime = timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    /*
    * Method for showing list video
    */
    public void processVideos() throws NoVideoAvailableException {
        List<Advertisement> bestVariant = new ArrayList<>();

//      calculate and take best variant of list with video
        bestVariant = pickVideosToList(null, null, timeSeconds, 0, bestVariant);

//      variables for counting, amount of video and time of duration
        long totalAmount = 0;
        int totalDuration = 0;


        for (Advertisement ad : bestVariant) {
//      decrement count video and print in console
            ad.revalidate();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", ad.getName(),
                    ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));

            totalAmount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
        }

//      register of showing video with best list advertisement
        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(bestVariant, totalAmount, totalDuration));
    }


 /*
     algoritm selection best list video
     return list with max time duration and max value money
*/
    private List<Advertisement> pickVideosToList(List<Advertisement> previousList,
                                                 Advertisement previousAd,
                                                 int remainingTime,
                                                 long profit,
                                                 List<Advertisement> bestResult) throws NoVideoAvailableException {

//        create recursive list for finding best list
        List<Advertisement> recursiveList = new ArrayList<>();

//      if we in recursive and previous list not null
//    1. add all previous video list
//    2. take away duration one video from common time showing
//    3. addition our profit
//    4. in recursive list add video which we counted
        if (previousList != null) {
            recursiveList.addAll(previousList);
            remainingTime -= previousAd.getDuration();
            profit += previousAd.getAmountPerOneDisplaying();
            recursiveList.add(previousAd);
        }

        if (remainingTime != 0) {
            for (Advertisement ad : videos) {
                if (recursiveList.contains(ad) || ad.getHits() <= 0) continue;

//           if we yet have time for showing video and it great,
//           that time duration advertisement then we go to the recursive
                if (remainingTime >= ad.getDuration())
                    bestResult = pickVideosToList(recursiveList, ad, remainingTime, profit, bestResult);
            }
        }

        if (profit > maxProfit) {
            maxProfit = profit;
            minRemainingTime = remainingTime;
            bestResult = recursiveList;
        } else if (profit == maxProfit && remainingTime < minRemainingTime) {
            minRemainingTime = remainingTime;
            bestResult = recursiveList;
        } else if (profit == maxProfit && remainingTime == minRemainingTime && bestResult.size() > recursiveList.size())
            bestResult = recursiveList;

//       if result list is empty, then go to the register this case
        if (bestResult.isEmpty()) {
            StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

//        sort video before return
        sortVideo(bestResult);

        return bestResult;
    }


//    sorted video in decreasing order
    private void sortVideo(List<Advertisement> bestResult){
        Collections.sort(bestResult, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long pricePerVideoDiff = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
                if (pricePerVideoDiff != 0)
                    return (int) pricePerVideoDiff;
                else
                    return (int) (o1.getAmountPerOneDisplaying() * 100 / o1.getDuration()
                                - o2.getAmountPerOneDisplaying() * 100 / o2.getDuration());
            }
        });
    }
}