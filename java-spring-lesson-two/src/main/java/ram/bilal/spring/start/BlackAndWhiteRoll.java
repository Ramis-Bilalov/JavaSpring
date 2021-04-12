package ram.bilal.spring.start;

public class BlackAndWhiteRoll implements CameraRoll {

//    private int count;
//
//    public BlackAndWhiteRoll(int count) {
//        this.count = count;
//    }

    @Override
    public void processing() {
//        count--;
        System.out.println("-1 b/w frame");
//        System.out.printf("Roll has %d frames(s)\n", count);
    }
}
