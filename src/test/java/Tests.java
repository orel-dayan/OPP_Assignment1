import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    GroupAdmin groupAdmin = new GroupAdmin();
    GroupAdmin groupAdmin1 = new GroupAdmin();
    GroupAdmin groupAdmin2=new GroupAdmin();
    String s1 = "Alice";
    String s2 = "Bob";
    ConcreteMember member1 = new ConcreteMember(s1);
    ConcreteMember member2 = new ConcreteMember(s2);

    // stub method to check external dependencies compatibility
    @BeforeEach
    void setUp() {
            groupAdmin.register(member1);
            groupAdmin.register(member2);
        }
      @AfterEach
      void testMemory(){
            logger.info(JvmUtilities::jvmInfo);
        }
    // check register and unregister size
    @Test
    public void testMemoryRegisterChanges(){

        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.unregister(member1);
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.unregister(member2);
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
    }
    @Test
    public void testMemoryStrings(){

        groupAdmin.append("Hello");
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.append(" world!");
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.delete(0,5);
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.insert(0,"Hello");
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
        groupAdmin.undo();
        logger.info(() -> JvmUtilities.objectFootprint(groupAdmin));
    }

    @Test
    public void TestGroupAdmin() {
        logger.info(() -> JvmUtilities.objectTotalSize(groupAdmin1));
        groupAdmin1.append("Great");
        logger.info(() -> JvmUtilities.objectTotalSize(groupAdmin1));
        ConcreteMember c1 = new ConcreteMember("A");
        ConcreteMember c2 = new ConcreteMember("B");
        ConcreteMember c3 = new ConcreteMember("C");
        ConcreteMember c4 = new ConcreteMember("D");
        groupAdmin1.register(c1);
        groupAdmin1.register(c2);
        groupAdmin1.register(c3);
        groupAdmin1.register(c4);
        groupAdmin1.register(c4); // return  message about duplicate
        groupAdmin1.append("!!!");
        //Checking if they have the same size
        logger.info(() -> JvmUtilities.objectTotalSize(c1));
        logger.info(() -> JvmUtilities.objectTotalSize(c2));
        assertEquals(JvmUtilities.objectTotalSize(c1), JvmUtilities.objectTotalSize(c2));
        groupAdmin1.undo();
        assertEquals("Great", c1.getUsb().toString());
        groupAdmin1.unregister(c4);
        groupAdmin1.delete(0, 1);
        assertEquals("reat", c1.getUsb().toString());
        assertEquals(c2.getUsb(), c3.getUsb());
        logger.info(() -> (" Size before unregister is:"));
        logger.info(() -> JvmUtilities.objectTotalSize(groupAdmin1));
        groupAdmin1.unregister(c3);
        logger.info(() -> ("Size  after unregister:"));
        logger.info(() -> JvmUtilities.objectTotalSize(groupAdmin1));
    }

    @Test
    public void TestConcreteMember() {

        ConcreteMember m =new ConcreteMember("A");
        logger.info(() -> (" Size before"));
        logger.info(() -> JvmUtilities.objectTotalSize(m));
        groupAdmin2.register(m);
        groupAdmin2.append("to sleep or not to sleep?");
        assertEquals("to sleep or not to sleep?", m.getUsb().toString());
        groupAdmin2.append(" to sleep");
        assertEquals("to sleep or not to sleep? to sleep", m.getUsb().toString());
        logger.info(() -> JvmUtilities.objectTotalSize(m));
        groupAdmin2.append("!");
        groupAdmin2.unregister(m);
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("hello");
        m.update(usb);
        assertEquals("hello", m.getUsb().toString());
        logger.info(() -> (" Size after :"));
        logger.info(() -> JvmUtilities.objectTotalSize(m));
    }
}



