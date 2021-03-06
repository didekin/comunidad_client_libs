package com.didekinlib.model.common;

/**
 * User: pedro@didekin
 * Date: 18/03/16
 * Time: 19:57
 *
 * This class is used as a convenience object wrapper whose internal counter can be modified in different methods, without
 * passing and returning it explicitly.
 */

public class WrapperCounter {

    private long counter;

    public WrapperCounter(long counter)
    {
        this.counter = counter;
    }

    public long getCounter()
    {
        return counter;
    }

    public void addCounter(){
        ++counter;
    }

    public void addCounter(int howMuch){
        counter += howMuch;
    }
}
