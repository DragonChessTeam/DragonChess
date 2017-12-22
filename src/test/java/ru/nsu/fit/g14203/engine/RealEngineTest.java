package ru.nsu.fit.g14203.engine;

import org.junit.Test;
import ru.nsu.fit.g14203.engine.initializer.TestEngineInitializer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;

import static org.junit.Assert.*;
import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class RealEngineTest {

    public RealEngineTest() {}
    @Test
    public void goodMove() {
        RealEngine eng = new RealEngine(new TestEngineInitializer());
        assertEquals(EngineResponse.OK, eng.doMove(WHITE, new Way(new Dot3D(3,0,1), new Dot3D(4,0,1))));
    }

    @Test
    public void goodCapture() {
        RealEngine eng = new RealEngine(new TestEngineInitializer());
        assertEquals( EngineResponse.OK,    eng.doMove(WHITE, new Way(new Dot3D(3,0,1), new Dot3D(4,0,1))));
        assertEquals( EngineResponse.OK,    eng.doMove(BLACK, new Way(new Dot3D(6,7,1), new Dot3D(5,7,1))));
        assertEquals( EngineResponse.CHECK, eng.doMove(WHITE, new Way(new Dot3D(4,0,1), new Dot3D(5,0,1))));
        assertEquals( EngineResponse.OK,    eng.doCapture(BLACK, new Way(new Dot3D(6,1,1), new Dot3D(5,0,1))));
    }

    @Test
    public void notAvailableCaptureToTheVoid() {
        RealEngine eng = new RealEngine(new TestEngineInitializer());
        assertEquals(EngineResponse.WRONG_WAY, eng.doCapture(WHITE, new Way(new Dot3D(3,0,1), new Dot3D(4,0,1))));
    }

    @Test
    public void notAvailableCaptureToTheAlly() {
        RealEngine eng = new RealEngine(new TestEngineInitializer());
        assertEquals(EngineResponse.WRONG_WAY, eng.doCapture(WHITE, new Way(new Dot3D(3,0,1), new Dot3D(3,1,1))));
    }

}