/*
 * class: TriggerDriverTest
 *
 * Version $Id: TriggerDriverTest.java,v 1.2 2005/06/09 21:16:04 toale Exp $
 *
 * Date: June 8 2005
 *
 * (c) 2005 IceCube Collaboration
 */

package icecube.daq.sim.test;

import icecube.daq.sim.TriggerDriver;
import icecube.daq.trigger.impl.TriggerRequestPayload;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * This class defines the tests that any TriggerDriver object should pass.
 *
 * @author pat
 * @version $Id: TriggerDriverTest.java,v 1.2 2005/06/09 21:16:04 toale Exp $
 */
public class TriggerDriverTest
        extends TestCase
{

    /**
     * The object being tested.
     */
    private TriggerDriver testObject;

    /**
     * Constructs and instance of this test.
     *
     * @param name the name of the test.
     */
    public TriggerDriverTest(String name)
    {
        super(name);
    }

    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     *
     * @throws Exception if super class setUp fails.
     */
    protected void setUp()
            throws Exception
    {
        super.setUp();
        testObject = new TriggerDriver("payload-generator/src/icecube/daq/sim/test/trigger-file.txt");
    }

    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     *
     * @throws Exception if super class tearDown fails.
     */
    protected void tearDown()
            throws Exception
    {
        testObject = null;
        super.tearDown();
    }

    /**
     * Test trigger creation
     */
    public void testTrigger()
    {

        TriggerRequestPayload trigger = (TriggerRequestPayload) testObject.nextTrigger();
        int uid = trigger.getUID();
        int type = trigger.getTriggerType();
        int configId = trigger.getTriggerConfigID();
        int sourceId = trigger.getSourceID().getSourceID();
        long firstTime = trigger.getFirstTimeUTC().getUTCTimeAsLong();
        long lastTime = trigger.getLastTimeUTC().getUTCTimeAsLong();

        assertEquals(uid, 1);
        assertEquals(type, 1);
        assertEquals(configId, 1);
        assertEquals(sourceId, 1);
        assertEquals(firstTime, 10);
        assertEquals(lastTime, 20);

    }

    /**
     * Create test suite for this class.
     *
     * @return the suite of tests declared in this class.
     */
    public static Test suite()
    {
        return new TestSuite(TriggerDriverTest.class);
    }

    /**
     * Main routine which runs text test in standalone mode.
     *
     * @param args the arguments with which to execute this method.
     */
    public static void main(String[] args)
    {
        TestRunner.run(suite());
    }
}