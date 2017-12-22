package ru.nsu.fit.g14203.engine;

import org.junit.Test;
import ru.nsu.fit.g14203.engine.initializer.NormalEngineInitializer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;

import static org.junit.Assert.*;
import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class RealEngineTest {

    public RealEngineTest() {}
    @Test
    public void goodMoveTest() {
        RealEngine eng = new RealEngine(new NormalEngineInitializer());
        assertEquals(EngineResponse.OK, eng.doMove(WHITE, new Way(new Dot3D(0,6,2), new Dot3D(1,5,2))));
    }
    @Test
    public void checkTest() {
        RealEngine eng = new RealEngine(new NormalEngineInitializer());
        assertEquals(EngineResponse.OK, eng.doMove(WHITE, new Way(new Dot3D(6,6,2), new Dot3D(5,5,2))));
        assertEquals(EngineResponse.OK, eng.doMove(BLACK, new Way(new Dot3D(6,1,2), new Dot3D(5,2,2))));
        assertEquals(EngineResponse.OK, eng.doMove(WHITE, new Way(new Dot3D(6,7,2), new Dot3D(9,4,2))));
        assertEquals(EngineResponse.OK, eng.doCapture(BLACK, new Way(new Dot3D(6,0,2), new Dot3D(0,6,2))));
        assertEquals(EngineResponse.CHECK_MATE, eng.doMove(WHITE, new Way(new Dot3D(9,4,2), new Dot3D(6,1,2))));
    }

    @Test
    public void goodCaptureTest() {
        RealEngine eng = new RealEngine(new NormalEngineInitializer());
        assertEquals(EngineResponse.OK, eng.doMove(WHITE, new Way(new Dot3D(1,6,1), new Dot3D(1,5,1))));
        assertEquals(EngineResponse.OK, eng.doMove(BLACK, new Way(new Dot3D(0,1,1), new Dot3D(0,2,1))));
        assertEquals(EngineResponse.OK, eng.doMove(WHITE, new Way(new Dot3D(1,5,1), new Dot3D(1,4,1))));
        assertEquals(EngineResponse.OK, eng.doMove(BLACK, new Way(new Dot3D(0,2,1), new Dot3D(0,3,1))));
        assertEquals(EngineResponse.OK, eng.doCapture(WHITE, new Way(new Dot3D(1,4,1), new Dot3D(0,3,1))));
    }

    @Test
    public void notAvailableCaptureToTheVoidTest() {
        RealEngine eng = new RealEngine(new NormalEngineInitializer());
        assertEquals(EngineResponse.WRONG_WAY, eng.doCapture(WHITE, new Way(new Dot3D(0,0,2), new Dot3D(0,1,2))));
    }

    @Test
    public void notAvailableCaptureToTheAllyTest() {
        RealEngine eng = new RealEngine(new NormalEngineInitializer());
        assertEquals(EngineResponse.WRONG_WAY, eng.doCapture(WHITE, new Way(new Dot3D(0,0,1), new Dot3D(0,1,1))));
    }

}