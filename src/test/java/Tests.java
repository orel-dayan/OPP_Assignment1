import observer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    GroupAdmin tester = new GroupAdmin();


    // stub method to check external dependencies compatibility

    @Test
     void testFooPrintAndTotalSize() {

        ConcreteMember c1 = new ConcreteMember("A");
        ConcreteMember c2 = new ConcreteMember("B");
        ConcreteMember c3 = new ConcreteMember("C");
        System.out.println("Testing footprint and  total size");
        logger.info(() -> JvmUtilities.objectFootprint(tester, c1, c2, c3));
        logger.info(() -> JvmUtilities.objectTotalSize(tester, c1, c2, c3));
        System.out.println();
        tester.register(c1);
        tester.register(c2);
        tester.register(c3);
        logger.info(() -> JvmUtilities.objectFootprint(tester));
        logger.info(() -> JvmUtilities.objectTotalSize(tester));

        tester.append("hello"); // append string
        System.out.println();

        System.out.println("what happen to the memory after we appended the string hello ");
        logger.info(() -> JvmUtilities.objectFootprint(tester));
        logger.info(() -> JvmUtilities.objectTotalSize(tester));
        System.out.println();

        System.out.println("unregister a members, memory allocate: ");
        tester.unregister(c2);
        System.out.println();
        logger.info(() -> JvmUtilities.objectFootprint(tester));
        logger.info(() -> JvmUtilities.objectTotalSize(tester));
        tester.unregister(c3);
        System.out.println();
        logger.info(() -> JvmUtilities.memoryStats(tester)); // return the object-footprint and objectTotalSize

        System.out.println("printing info: ");
        logger.info(JvmUtilities::jvmInfo);
        System.out.println();

    }
    @Test
    void checkSizeDeleteAndInsert() {

        // size of admin
        logger.info(()->JvmUtilities.objectTotalSize(tester));


        ConcreteMember c1 = new ConcreteMember("A");
        ConcreteMember c2 = new ConcreteMember("B");
        ConcreteMember c3 = new ConcreteMember("C");
        System.out.println("c1 memory: before register " + JvmUtilities.objectTotalSize(c1));
        System.out.println("c2 memory: before register " + JvmUtilities.objectTotalSize(c2));


        // register the members
        tester.register(c1);
        tester.register(c2);
        tester.register(c2); // msg


        tester.append("grea");
        System.out.println();


        System.out.println("""

                *************** This Is Before insert  ***************\s
                """);
        System.out.println("c1 memory: " + JvmUtilities.objectTotalSize(c1));
        System.out.println("c2 memory: " + JvmUtilities.objectTotalSize(c2));

        // insert a string at index 4
        tester.insert(4, "t");
        System.out.println("""

                *************** This Is after insert  ***************\s
                """);

        logger.info(()->"total memory after insert:");

        logger.info(()->JvmUtilities.objectTotalSize(c1));
        System.out.println();
        logger.info(()->JvmUtilities.objectTotalSize(c2));
        // check if they have the same size
        assertEquals(JvmUtilities.objectTotalSize(c1),JvmUtilities.objectTotalSize(c2));
        System.out.println();


        // delete a range of characters
        System.out.println("""

                *************** This Is Before delete ***************\s
                """);
        System.out.println("c1 memory: " + JvmUtilities.objectTotalSize(c1));
        System.out.println("c2 memory: " + JvmUtilities.objectTotalSize(c2));

        tester.delete(0,1);
        System.out.println();
        assertEquals("reat", c1.getData());
        assertEquals(c2.getData(),c2.getData());
        System.out.println("""

                *************** This Is after delete ***************\s
                """);
        logger.info(()->"total memory after delete:");
        logger.info(()->JvmUtilities.objectTotalSize(c1));


        // register a member
        tester.register(c3);
        System.out.println("""

                *************** This Is after register member  ***************\s
                """);
        logger.info(()->"total memory after register a member:");
        logger.info(()->JvmUtilities.objectTotalSize(tester));


        // unregister a member
        System.out.println();
        System.out.println("""

                *************** This after unregister member  ***************\s
                """);
        tester.unregister(c3);
        logger.info(()->"total memory after unregister a member:");
        logger.info(()->JvmUtilities.objectTotalSize(tester));

    }

    @Test
     void TestConcrete() {
        ConcreteMember m = new ConcreteMember("A");
        System.out.println("total size concremember before register -");
        logger.info(() -> JvmUtilities.objectTotalSize(m));
        tester.register(m);
        tester.append("to sleep or not to sleep?");
        assertEquals("to sleep or not to sleep?",m.getData());
        tester.append(" to sleep");
        assertEquals("to sleep or not to sleep? to sleep", m.getData());
        System.out.println("total size after register");
        logger.info(() -> JvmUtilities.objectTotalSize(m));
        tester.append("!");
        System.out.println("unregister-");
        tester.unregister(m);
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("hello");
        m.update(usb);
        assertEquals("hello", m.getData());
        System.out.println("size after unregister-");
        logger.info(() -> JvmUtilities.objectTotalSize(m));
    }
    @Test
    void registerMemory() {
        ConcreteMember member1 = new ConcreteMember("A");
        ConcreteMember member2 = new ConcreteMember("B");
        ConcreteMember member3 = new ConcreteMember("C");
        ConcreteMember member4 = new ConcreteMember("D");
        GroupAdmin groupAdmin3 = new GroupAdmin();
        groupAdmin3.register(member1);
        groupAdmin3.register(member1);// cant register
        assertEquals(groupAdmin3.getMembers().size(),1); // size is 1
        groupAdmin3.register(member2);
        groupAdmin3.register(member3);
        groupAdmin3.register(member4);
        assertEquals(groupAdmin3.getMembers().size(),4); // now 4
        logger.info(()->"Before actions on UndoableStringBuilder: "+JvmUtilities.objectTotalSize(groupAdmin3));
        groupAdmin3.append("hi");
        groupAdmin3.append("hello");
        groupAdmin3.append("shalom");
        groupAdmin3.undo();
        groupAdmin3.undo();
        assertEquals(member1.getData(),"hi");
        groupAdmin3.delete(1,2);
        assertEquals(member2.getData(),"h");
        groupAdmin3.insert(1,"i");
        assertEquals(member3.getData(),"hi");
        logger.info(()->"After actions on UndoableStringBuilder: "+JvmUtilities.objectTotalSize(groupAdmin3));
    }
    @Test
    void testMemoryBothGroupAdminAndConcreteMemberAppendAndUndo(){

        GroupAdmin groupAdmin = new GroupAdmin();

        ConcreteMember M1 = new ConcreteMember("A");
        ConcreteMember M2 = new ConcreteMember("B");
        groupAdmin.register(M1);
        groupAdmin.register(M2);
        System.out.println("""

                *************** This Is Before append  ***************\s
                """);
        System.out.println("groupAdmin memory : " + JvmUtilities.objectFootprint(groupAdmin));
        System.out.println("M1 memory: " + JvmUtilities.objectTotalSize(M1));
        System.out.println("M2 memory: " + JvmUtilities.objectTotalSize(M2));


        groupAdmin.append("hello world");
        System.out.println("""

                *************** This Is after append  ***************\s
                """);
        System.out.println("groupAdmin memory : " + JvmUtilities.objectFootprint(groupAdmin));
        System.out.println("M1 memory : " + JvmUtilities.objectTotalSize(M1));
        System.out.println("M2 memory : " + JvmUtilities.objectTotalSize(M2));


        groupAdmin.undo();
        assertEquals("",groupAdmin.toString());
        System.out.println("""

                *************** This Is after undo  ***************\s
                """);
        System.out.println("groupAdmin memory : " + JvmUtilities.objectFootprint(groupAdmin));
        System.out.println("M1 memory : " + JvmUtilities.objectTotalSize(M1));
        System.out.println("M2 memory : " + JvmUtilities.objectTotalSize(M2));
        System.out.println();


      System.out.println("Total memory consumption of program: " + JvmUtilities.jvmInfo());
}

}



