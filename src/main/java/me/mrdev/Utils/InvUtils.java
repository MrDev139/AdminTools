package me.mrdev.Utils;

public class InvUtils {

    public static int getDivisible(int in) {
        if(in % 9 != 0) {
            for (int i = in; i < 54; i++) { //54 is max slots for the inventory to avoid problems
                if (i % 9 == 0) {
                    return i;
                }
            }
        }
        return in;
    }

}
