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

public class GroupAdmin extends UndoableStringBuilder implements Sender{
    private List<Member> members;

    /**
     * A constructor with default values.
     */
    public GroupAdmin()
    {
        super();
        members =new ArrayList<>();
    }

    public void notifyAllMembers() {
        for (Member m:this.members) {
            m.update(this);
        }
    }
    @Override
    public void register(Member obj) {
        if (members.contains(obj))
            System.out.println("the member already exist");
        else
        {
            members.add(obj);

        }
    }

    @Override
    public void unregister(Member obj) {
        if (members.contains(obj)) {
            members.remove(obj);
        } else {
            System.out.println("This member already isn't a member.");
        }
    }

        @Override
     public void insert(int offset, String obj) {
        super.insert(offset,obj);
        this.notifyAllMembers();
    }

    @Override
    public void append(String obj) {
       super.append(obj);
       this.notifyAllMembers();
    }

    @Override
    public void delete(int start, int end) {
      super.delete(start,end);
      this.notifyAllMembers();

    }

    @Override
    public void undo() {
       super.undo();
       this.notifyAllMembers();
    }

    public List<Member> getMembers() {
        return members;
    }

    public UndoableStringBuilder getUsb() {
        return this;
    }
}
