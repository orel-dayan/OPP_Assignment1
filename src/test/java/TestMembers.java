import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestMembers {
    GroupAdmin groupAdmin;
    @BeforeEach
    void setUp() {
        this.groupAdmin = new GroupAdmin();
    }


    @Test
    void register() {
        ConcreteMember c1 = new ConcreteMember("orel");
        ConcreteMember c2 = new ConcreteMember("roy");
        ConcreteMember c3 = new ConcreteMember("lili");
        ConcreteMember c4 = new ConcreteMember("shir");
        groupAdmin.register(c1);
        groupAdmin.register(c2);
        groupAdmin.register(c3);
        groupAdmin.register(c4);
        groupAdmin.register(c4); // msg about dup
        ArrayList<Member> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        for (int i = 0; i < 4; i++) {
            assertEquals(groupAdmin.getMembers().get(i), list.get(i));
        }
    }

    @Test
    void unregister() {
        ConcreteMember member1 = new ConcreteMember("A");
        ConcreteMember member2 = new ConcreteMember("B");
        ConcreteMember member3 = new ConcreteMember("C");
        ConcreteMember member4 = new ConcreteMember("D");
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.register(member1);
        groupAdmin.register(member2);
        groupAdmin.register(member3);
        groupAdmin.unregister(member1);
        groupAdmin.unregister(member2);
        groupAdmin.unregister(member3);
        groupAdmin.unregister(member4);
        assertEquals(groupAdmin.getMembers().size(), 0);
    }
    @Test
    void notifyMembers() {

        ConcreteMember c1 = new ConcreteMember("orel");
        ConcreteMember c2 = new ConcreteMember("roy");
        ConcreteMember c3 = new ConcreteMember("lili");
        groupAdmin.register(c1);
        groupAdmin.register(c2);
        groupAdmin.register(c3);
        groupAdmin.append("obj");
        groupAdmin.notifyAllMembers();
        assertEquals(c1.getData(), groupAdmin.toString());
        groupAdmin.undo();
        groupAdmin.notifyAllMembers();
        assertEquals(c2.getData(), groupAdmin.toString());
    }
    @Test
    void insert() {

        ConcreteMember member = new ConcreteMember("A");
        groupAdmin.register(member);
        groupAdmin.insert(0, "test");
        assertEquals("test", groupAdmin.toString()); // check if update
        groupAdmin.insert(4, " insert");
        assertEquals("test insert", groupAdmin.toString()); //check if update
        groupAdmin.insert(-2, "!"); // print error and see if update

    }
    @Test
    void append() {

        ConcreteMember member = new ConcreteMember("A");
        groupAdmin.register(member);
        groupAdmin.append("TEST");
        assertEquals("TEST", groupAdmin.toString());
        ConcreteMember observer2 = new ConcreteMember("B");  // Create another member and register him.
        groupAdmin.register(observer2);
        groupAdmin.append("!");  //  append " !" and check if updated.
        assertEquals("TEST!", groupAdmin.toString());
    }

    @Test
    void delete() {
        ConcreteMember concreteMember = new ConcreteMember("A");
        groupAdmin.register(concreteMember);
        groupAdmin.delete(0,5);
        assertEquals("", groupAdmin.toString());
        groupAdmin.append("Test");  // append and check to see if updated.
        groupAdmin.delete(0,3);
        assertEquals("t", groupAdmin.toString());
        groupAdmin.delete(0, -9); // out of bounds
    }

    @Test
    void undo() {
     ConcreteMember concreteMember = new ConcreteMember("A");
     ConcreteMember member = new ConcreteMember("B");
     groupAdmin.register(member);
     groupAdmin.register(concreteMember);
     groupAdmin.append("TEST");
     groupAdmin.append("test");
     groupAdmin.undo();
     assertEquals(groupAdmin.toString(),concreteMember.getData(),member.getData());
     groupAdmin.undo();
     assertEquals(groupAdmin.toString(),concreteMember.getData(), concreteMember.getData());

    }
    @Test
    void update() {
    ConcreteMember member = new ConcreteMember("A");
    ConcreteMember member1 = new ConcreteMember("B");
    groupAdmin.register(member);
    groupAdmin.register(member1);
    groupAdmin.append("hello");
    assertEquals("hello",groupAdmin.toString());
    groupAdmin.unregister(member1);
    groupAdmin.undo();
    assertEquals("", groupAdmin.toString());
    }

    }

