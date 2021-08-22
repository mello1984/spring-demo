package ru.butakov.springdemo.reeper2;

import org.springframework.stereotype.Component;
import ru.butakov.springdemo.reeper2.context.ChangeTo;

@Component
@ChangeTo(to = JoeHarper.class)
public class TomSawyer implements Prank {
    @Override
    public void playPrank() {
        System.out.println(getClass().getSimpleName() + ": I slapped Becky Thatcher");
    }
}
