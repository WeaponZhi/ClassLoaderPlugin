package org.sojex.stockquotes.appbundle;

/**
 * Created by WeaponZhi on 2017/8/23.
 */

public class Test {
    @org.junit.Test
    public void main(){
        Test test = new Test();
        test.stackoverflowTest();
    }

    public void stackoverflowTest(){
        stackoverflowTest();
    }
}
