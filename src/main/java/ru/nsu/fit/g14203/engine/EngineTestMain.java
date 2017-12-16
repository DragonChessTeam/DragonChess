package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;

import java.util.ArrayList;

import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class EngineTestMain {

    public static void main(String[] args) {
        Engine eng = new RealEngine();
        Observer o = new ObserverMock();
        eng.registerObserver(o);
        EngineResponse res;// = eng.doMove(WHITE, new Way(new Dot3D(1,1,2), new Dot3D(1,1,1)));
//        o.update(new ArrayList<>());
//        System.out.println("Turn status: " + res);
//        res = eng.doMove(BLACK, new Way(new Dot3D(1,3,2), new Dot3D(1,2,2)));
//        o.update(new ArrayList<>());
//        System.out.println("Turn status: " + res);
//        res = eng.doMove(WHITE, new Way(new Dot3D(1,1,1), new Dot3D(1,2,1)));
//        o.update(new ArrayList<>());
//        System.out.println("Turn status: " + res);
//        res = eng.doMove(WHITE, new Way(new Dot3D(1,1,1), new Dot3D(1,1,2)));
//        o.update(new ArrayList<>());
//        System.out.println("Turn status: " + res);
        res = eng.doMove(WHITE, new Way(new Dot3D(3,0,1), new Dot3D(4,0,1)));
        o.update(new ArrayList<>());
        System.out.println("Turn status: " + res);
    }

}
