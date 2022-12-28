/**
*ConcreteMember class implement the Member interface.
*This class represents a Member of a group administered by a GroupAdmin.
*ConcreteMember can register and get updates from a GroupAdmin.
*ConcreteMember class has a name and own copy of the group's UndoableStringBuilder object.
**/
package observer;
public class ConcreteMember implements Member {
    private final String name;
    private UndoableStringBuilder usb;

    /**
     *A constructor with String name of member
     * @param name - a name of a member
     */
    public ConcreteMember(String name) {
        this.name = name;
        this.usb = null;
    }
     /**
     * This method updates the UndoableStringBuilder object of the ConcreteMember
     * with the given UndoableStringBuilder object.
     * @param usb the new UndoableStringBuilder object
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
        System.out.println(this.name + " value -> " + this.usb);
    }

    /**
     * A to string method that returns the current string that's stored.
     * @return String
     */

    public String getData() {
        return usb.toString();
    }
}
