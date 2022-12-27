/**
 * GroupAdmin class implements the Sender interface.
 * This class contains a list of members and UndoableStringBuilder object.
 * GroupAdmin class has a methods to register ,unregister members,append and insert strings to UndoableStringBuilder,
 * delete strings from the UndoableStringBuilder and makes an undo command.
 * It also has a notifyAllMembers method that notifies the members that are registered to the GroupAdmin to
 * update their UndoableStringBuilder
 *
 * @param members is an ArrayList that holds this GroupAdmin members
 * @param usb is an UndoableStringBuilder that the members points to
 */

package observer;
import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender{
    private List<Member> members;
    private UndoableStringBuilder usb;

    /**
     * A constructor with default values.
     */
    public GroupAdmin()
    {
        members=new ArrayList<>();
        this.usb=new UndoableStringBuilder();
    }

    /**
     * TODO
     * @return usb
     */

    public UndoableStringBuilder getUsb() {
        return usb;
    }

    /**
     *  TODO
     * @return  members
     */

    public List<Member> getMembers() {
        return members;
    }

    /**
     *
     * This method updates all the members that are registered to GroupAdmin with their UndoableStringBuilder
     * @param usb - TODO
     * running time complexity O(n), n is a size of arraylist
     *
     **/
    public void notifyAllMembers(UndoableStringBuilder usb) {
        for (Member m : this.members) {
            m.update(usb);
        }
    }
    /**
     * This method adds given member to members list
     * @param obj - the member to add
     *
     */
    @Override
    public void register(Member obj) {
        if (members.contains(obj))
            System.out.println("the member already exist");
        else
        {
            members.add(obj);
        }
    }

    /**
     * This method removes given member of the members list.
     * @param obj - the member to remove
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
     *This method Inserts the string into this character sequence.
     * If str is null, then the four characters "null" are inserted into this sequence.
     * Supports undo method.
     * notifies the members that are registered to the GroupAdmin to update their UndoableStringBuilder
     *
     * @param offset-the offset.
     * @param  obj - the string to insert
     *
     */
        @Override
     public void insert(int offset, String obj) {
        this.usb.insert(offset,obj);
        notifyAllMembers(this.getUsb());

    }
    /**
     *
     * This method appends the specified string to this character sequence.
     * Supports undo method.
     * notifies the members that are registered to the GroupAdmin to update their UndoableStringBuilder
     *
     * @param obj - the string to append
     *
     */

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notifyAllMembers(this.getUsb());
    }
    /**
     * This method removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * Supports undo method.
     * notifies the members that are registered to the GroupAdmin to update their UndoableStringBuilder
     *
     *  @param start - The beginning index.
     *  @param  end -The ending index.
     *
     */
    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        notifyAllMembers(this.getUsb());

    }
    /**
     * This method makes an undo command - reverse the action of an earlier action.
     * Works in the following methods : append, delete and insert.
     * notifies the members that are registered to the GroupAdmin to update their UndoableStringBuilder
     */

    @Override
    public void undo() {
        this.usb.undo();
        notifyAllMembers(this.getUsb());

    }
}
