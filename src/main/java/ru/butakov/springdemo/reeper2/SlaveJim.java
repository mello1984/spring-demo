package ru.butakov.springdemo.reeper2;

import org.springframework.stereotype.Component;
import ru.butakov.springdemo.reeper2.context.ChangeTo;

@Component
@ChangeTo(to = HuckleberryFinn.class)
public class SlaveJim implements Prank {
    @Override
    public void playPrank() {
        System.out.println(getClass().getSimpleName() + ": I don't know pranks");
    }
}
