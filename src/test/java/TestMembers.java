import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestMembers {
    @Test
    void register() {
        ConcreteMember c1 = new ConcreteMember("orel");
        ConcreteMember c2 = new ConcreteMember("roy");
        ConcreteMember c3 = new ConcreteMember("lili");
        ConcreteMember c4 = new ConcreteMember("shir");
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.register(c1);
        groupAdmin.register(c2);
        groupAdmin.register(c3);
        groupAdmin.register(c4);
        groupAdmin.register(c4); // msg about dup
        ArrayList<Member> list = new ArrayList<Member>();
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
        GroupAdmin ga = new GroupAdmin();
        ga.register(member1);
        ga.register(member2);
        ga.register(member3);
        ga.unregister(member1);
        ga.unregister(member2);
        ga.unregister(member3);
        ga.unregister(member4);
        assertEquals(ga.getMembers().size(), 0);
    }
    @Test
    void notifyMembers() {
        GroupAdmin gad = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember("orel");
        ConcreteMember c2 = new ConcreteMember("roy");
        ConcreteMember c3 = new ConcreteMember("lili");
        gad.register(c1);
        gad.register(c2);
        gad.register(c3);
        gad.append("obj");
        gad.notifyAllMembers();
        assertEquals(c1.getData(), gad.toString());
        gad.undo();
        gad.notifyAllMembers();
        assertEquals(c2.getData(), gad.toString());
    }


}
