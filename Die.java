package edu.macalester.comp124.hw3;

import acm.util.RandomGenerator;


/**
 *
 */
public class Die {

    int faceValue;
    RandomGenerator rgen = RandomGenerator.getInstance() ;
    boolean isSetAside;


    public Die(int faceValue, boolean isSetAside ) {
        this.faceValue = faceValue;
        this.isSetAside = isSetAside;
    }

    public void roll(){
        if (!isSetAside){
            faceValue = rgen.nextInt(1, 6);
        }
    }

    public boolean isSetAside() {
        return isSetAside;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setIsSetAside(){
        isSetAside = true;
    }
}
