package ram.bilal.spring.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ColorRoll implements CameraRoll {
    @Value("${frames.count.rgb}")
    private int count;

    @Override
    public void processing() {
        count--;
        System.out.println("-1 colored frame");
        System.out.printf("Roll has %d frames(s)\n", count);
    }
}
