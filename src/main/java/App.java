import config.Setting;
import figures.*;

import java.lang.reflect.Array;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        L s = new L(Setting.get().getLBody());
        System.out.println(s);
        s.turnClockwise();
        System.out.println(s);

        s.turnClockwise();
        System.out.println(s);

        s.turnClockwise();
        System.out.println(s);
        s.turnClockwise();
        System.out.println(s);
        s.turnClockwise();
        System.out.println(s);
        s.turnClockwise();
        System.out.println(s);
        s.turnClockwise();
        System.out.println(s);

    }
}
