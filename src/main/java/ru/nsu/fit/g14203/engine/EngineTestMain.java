package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.Initializer.TestEngineInitializer;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;

import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class EngineTestMain {

    public static void main(String[] args) {
        Engine eng = new RealEngine(new TestEngineInitializer());
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
        //o.update(new ArrayList<>());
        System.out.println(WHITE+"Turn status: " + res);
        res = eng.doMove(BLACK, new Way(new Dot3D(6,7,1), new Dot3D(5,7,1)));
        //o.update(new ArrayList<>());
        System.out.println(BLACK+"Turn status: " + res);
        res = eng.doMove(WHITE, new Way(new Dot3D(4,0,1), new Dot3D(5,0,1)));
        //o.update(new ArrayList<>());
        System.out.println(WHITE+"Turn status: " + res);
        res = eng.doCapture(BLACK, new Way(new Dot3D(6,1,1), new Dot3D(5,0,1)));
        //o.update(new ArrayList<>());
        System.out.println(BLACK+"Turn status: " + res);
    }

}
