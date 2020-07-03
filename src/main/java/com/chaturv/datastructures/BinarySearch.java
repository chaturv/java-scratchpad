package com.chaturv.datastructures;

import java.util.List;

/**
 * Created by Vineet on 13/10/2017.
 */
public class BinarySearch {

    public static void main(String[] args) {

    }

    private <T extends Comparable> int findIndex(List<T> elements, T toSearch, int currentMid) {
        T midElement = elements.get(currentMid);
        int diff = midElement.compareTo(toSearch);

        if (diff == 0) {
            return currentMid;
        } else  if (diff > 0){

        }

        return 0;
    }


}
