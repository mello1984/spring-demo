package ru.butakov.springdemo.reeper2;

import org.springframework.stereotype.Component;

@Component
public class JoeHarper implements Prank {
    @Override
    public void playPrank() {
        System.out.println(getClass().getSimpleName() + ": I got into a fight");
    }
}
