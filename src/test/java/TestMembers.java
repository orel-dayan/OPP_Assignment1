import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestMembers {

    @Test
    public void testRegister(){
        GroupAdmin groupAdmin = new GroupAdmin();
        ConcreteMember orel;
        orel = new ConcreteMember("orel");
        groupAdmin.register(orel);
        assertTrue(groupAdmin.getMembers().contains(orel));
    }
    @Test
    public void testUnregister()
    {
        GroupAdmin groupAdmin = new GroupAdmin();
        ConcreteMember orel = new ConcreteMember("orel");
        groupAdmin.register(orel);
        assertTrue(groupAdmin.getMembers().contains(orel));
        groupAdmin.unregister(orel);
        assertFalse(groupAdmin.getMembers().contains(orel));

    }
    @Test void testNotify()
    {
        GroupAdmin gad = new GroupAdmin();
        ConcreteMember roy = new ConcreteMember("roy");
        gad.register(roy);
        assertNull(roy.getUsb());
        gad.append("");
        assertEquals(gad.getUsb(),roy.getUsb());
    }
    @Test
    public void testAppend(){
        GroupAdmin gd= new GroupAdmin();
        ConcreteMember orel = new ConcreteMember("A");
        ConcreteMember roy = new ConcreteMember("B");
        gd.register(roy);
        gd.register(orel);
        gd.append("HEY");
        assertEquals("HEY",orel.getUsb().toString(),roy.getUsb().toString());
    }
    @Test
    public void testInsert(){
        GroupAdmin gd= new GroupAdmin();
        ConcreteMember orel = new ConcreteMember("A");
        ConcreteMember roy = new ConcreteMember("B");
        gd.register(roy);
        gd.register(orel);
        gd.insert(0,"Test");
        gd.insert(4,"Insert");
        assertEquals("TestInsert",orel.getUsb().toString(),roy.getUsb().toString());
    }
    @Test
    public void testDelete(){
        GroupAdmin gd= new GroupAdmin();
        ConcreteMember orel = new ConcreteMember("orel");
        ConcreteMember roy = new ConcreteMember("roy");
        gd.register(roy);
        gd.register(orel);
        gd.append("TestDelete");
        gd.delete(4,10);
        assertEquals("Test",orel.getUsb().toString(),roy.getUsb().toString());
    }
    @Test
    public void testUndo(){
        GroupAdmin groupAdmin = new GroupAdmin();
        ConcreteMember orel = new ConcreteMember("orel");
        ConcreteMember roy = new ConcreteMember("roy");
        groupAdmin.register(roy);
        groupAdmin.register(orel);
        groupAdmin.append("hello");
        groupAdmin.append("world");
        groupAdmin.undo();
        assertEquals("hello",orel.getUsb().toString(),roy.getUsb().toString());
        groupAdmin.undo();
        assertEquals("",orel.getUsb().toString(),roy.getUsb().toString());
    }
    @Test
    public void testCopy(){
        GroupAdmin gad= new GroupAdmin();
        ConcreteMember mem = new ConcreteMember("a");
        ConcreteMember mam= new ConcreteMember("b");
        gad.register(mem);
        gad.register(mam);
        gad.append("Test");
        gad.append("!");
        assertEquals(gad.getUsb(),mam.getUsb());
        assertEquals(gad.getUsb(),mam.getUsb());
        gad.unregister(mam);
        gad.append("test");
        ConcreteMember m = new ConcreteMember("c");
        gad.register(m);
        assertEquals(gad.getUsb(),mam.getUsb());
        assertNull(m.getUsb());
        m.update(gad.getUsb());
        assertEquals(m.getUsb(),gad.getUsb());

    }
    @Test
    public void testUpdate() {
        GroupAdmin gadi = new GroupAdmin();
        gadi.append("This is the first line");
        gadi.append("AB");
        ConcreteMember orel = new ConcreteMember("orel");
        assertNull(orel.getUsb());
        gadi.register(orel);
        gadi.append(null);
        assertEquals("This is the first lineABnull", orel.getUsb().toString());
    }
}







