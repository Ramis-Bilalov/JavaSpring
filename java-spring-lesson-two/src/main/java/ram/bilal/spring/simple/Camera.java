package ram.bilal.spring.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Camera {

    private CameraRoll cameraRoll;

    @Autowired
    @Qualifier("blackAndWhiteRoll")
    public void setCameraRollBW(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    @Autowired
    @Qualifier("colorRoll")
    public void setCameraRollRGB(CameraRoll cameraRoll) {
        this.cameraRoll = cameraRoll;
    }

    public void doPhoto() {
        System.out.println("Click");
        cameraRoll.processing();
    }
}
