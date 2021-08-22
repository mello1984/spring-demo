package ru.butakov.springdemo.reeper2;

public class HuckleberryFinn implements Prank {
    @Override
    public void playPrank() {
        System.out.println(getClass().getSimpleName() + ": I ran away from home");
    }
}
