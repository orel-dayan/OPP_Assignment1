package observer;
import java.util.EmptyStackException;
import java.util.Stack;
/*
Use the class you've implemented in previous assignment
 */
/**
 *@author Roy Asraf
 *@author Orel Dayan
 *@version 1.0
 **/
public class UndoableStringBuilder {

        private StringBuilder stringBuilder;
        private  Stack<String> stackStringHistory;
        /**
         * A constructor with default values.
         */
        public UndoableStringBuilder() {
            this.stringBuilder = new StringBuilder();
            this.stackStringHistory=new Stack<>();
        }

        /**
         * A to string method that returns the current string that's stored.
         * @return String
         */

        @Override
        public String toString() {
            return this.stringBuilder.toString();
        }

        /**
         *
         * Appends the specified string to this character sequence.
         * Supports undo method.
         * @param str the string to append
         * we change signature to void
         *
         */
        public UndoableStringBuilder  append(String str) {
            this.stringBuilder.append(str);
            stackStringHistory.push(this.stringBuilder.toString());
            return this;

        }
        /**
         * Removes the characters in a substring of this sequence. The substring begins
         * at the specified start and extends to the character at index
         * end - 1 or to the end of the sequence if no such character exists.
         * If start is equal to end, no changes are made.
         * Supports undo method.
         *  @param start - The beginning index.
         *  @param  end -The ending index.
         *  we change signature to void
         */
        public UndoableStringBuilder delete(int start, int end) {

            if ((start < 0) || (end < start) || this.stringBuilder.toString().length() <end) {
                System.err.println("out of bounds");
                return this;
            }
            this.stringBuilder.delete(start, end);
            stackStringHistory.push(this.stringBuilder.toString());
            return this;

        }

        /**
         * Inserts the string into this character sequence.
         * If str is null, then the four characters "null" are inserted into this sequence.
         * Supports undo method.
         * @param offset-the offset.
         * @param str - the string to insert.
         * we change signature to void
         *
         */

        public UndoableStringBuilder insert(int offset, String str) {

            if (offset < 0 || offset> this.stringBuilder.toString().length()) {
                System.out.println("out of bounds");
                return this;

            }
            this.stringBuilder.insert(offset, str);
            stackStringHistory.push(this.stringBuilder.toString());
            return this;
        }
        /**
         *
         * Replaces the characters in a substring of this sequence with characters in
         * the specified String. The substring begins at the specified start and
         * extends to the character at index end - 1 or to the end of the sequence if
         * no such character exists. First the characters in the substring are removed
         * and then the specified String is inserted at start.
         * Supports undo method.
         * @param start  -The beginning index
         * @param end - The ending index
         * @param str -String that will replace previous contents.
         * @return  a reference to this object.
         */
        public  UndoableStringBuilder replace(int start, int end, String str) {
            try {
                if (str == null) {
                    System.err.println("str cant be null");
                    return this;

                }
                this.stringBuilder.replace(start, end, str);
                stackStringHistory.push(this.stringBuilder.toString());
            } catch (StringIndexOutOfBoundsException e) {
                System.err.println("your index is out of bounds");

            }
            return this;

        }
        /**
         * Causes this character sequence to be replaced by the reverse of the sequence.
         * Supports undo method.
         * @return  a reference to this object.
         */
        public UndoableStringBuilder reverse() {
            this.stringBuilder.reverse();
            stackStringHistory.push(this.stringBuilder.toString());
            return this;

        }
        /**
         * Makes an undo command - reverse the action of an earlier action.
         * Works in the following methods : append, delete ,reverse ,replace and insert.
         */
        public void undo() {
            try {
                this.stackStringHistory.pop();
                this.stringBuilder = new StringBuilder();
                this.stringBuilder.append(this.stackStringHistory.peek());
            } catch (EmptyStackException e) {
                //Do nothing
            }
        }
    }

