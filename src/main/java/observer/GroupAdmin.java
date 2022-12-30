/**
 * GroupAdmin class implements the Sender interface.
 * This class contains a list of members .
 * GroupAdmin class has a methods to register ,unregister members,append and insert strings to UndoableStringBuilder,
 * delete strings from the UndoableStringBuilder and makes an undo command.
 * It also has a notifyAllMembers method that notifies the members that are registered to the GroupAdmin to
 * update UndoableStringBuilder.
 * @param members is an ArrayList that holds this GroupAdmin members
 * @param usb is an UndoableStringBuilder that the members points to
 **/

package observer;
import java.util.ArrayList;

import java.util.List;

public class GroupAdmin implements Sender {
    private final List<Member> members;
    private UndoableStringBuilder usb;

    /**
     * A constructor with default values.
     */
    public GroupAdmin()
    {
       this.usb=new UndoableStringBuilder();
        members = new ArrayList<>();
    }

    /**
     * a function that runs through all members and updates them
     * */
    public void notifyAllMembers() {
        for (Member m:this.members) {
            m.update(this.usb);
        }
    }

    /**
     * The function adds a member to the group
     * @param obj  a member to add (a new member)
     */
    @Override
    public void register(Member obj) {
        if (members.contains(obj)) {
            System.out.println("the member already exist");
         } else {
            members.add(obj);
        }
    }
    /**
     * The function removes a member from the group
     * @param obj a member to remove
     */
    @Override
    public void unregister(Member obj) {
        if (members.contains(obj)) {
            members.remove(obj);
        } else {
            System.out.println("This member already isn't a member.");
        }
    }

    /**
     * @see observer.UndoableStringBuilder#insert(int, String)
     * Also notifies that a change has been made.
     */

     @Override
     public void insert(int offset, String obj) {
        this.usb.insert(offset,obj);
        this.notifyAllMembers();
    }

    /**
     * @see observer.UndoableStringBuilder#append(String)
     * Also notifies that a change has been made.
     *
     */
    @Override
    public void append(String obj) {
       this.usb.append(obj);
       this.notifyAllMembers();
    }

    /**
     * @see observer.UndoableStringBuilder#delete(int, int)
     * Also notifies that a change has been made.
     */

    @Override
    public void delete(int start, int end) {
      this.usb.delete(start,end);
      this.notifyAllMembers();

    }

    /**
     * @see observer.UndoableStringBuilder#undo()
     * Also notifies that a change has been made.
     */
    @Override
    public void undo() {
       this.usb.undo();
       this.notifyAllMembers();
    }

    /**
     * A function to get the private members list
     * @return the members list
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * A to string method that returns the current string that's stored.
     * @return String
     */
    @Override
    public String toString() {
        return usb.toString();
    }
}