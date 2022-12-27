
## _OOP Course Exercise 1_  
_Authors [orel-dayan@]() & [@roy-asraf1](https://github.com/roy-asraf1)_


### Built With

* Editor : IntelliJ IDEA
* Language: Java
* JDK: 19.0.1
* Maven 4.0.0
* JUnit 5.9.0

## About the project

### Undoable String Builder

The UndoableStringBuilder is a StringBuilder that can use the undo method to go back to previous state before last change.   
It can undo in the following methods :
- append
- delete
- reverse
- replace
- insert


You can find the UndoableStringBuilder class in this [this](https://github.com/orel-dayan/assigment-0) repository.

### Observer Design Pattern 
The `observer` design pattern is a behavioral design pattern that defines a one-to-many dependency between objects. .
so that when one object changes state, all its dependents are notified and updated automatically. The pattern has behavioral purpose and applies to the objects.

### When to use
- when an abstraction has two aspects, one dependent on the other
- when a change to one object requires changing others, and you don't know how many objects need to be changed
- when an object should be able to notify other objects without making assumptions about who these objects are.

### _In this project, we have implemented the observer design pattern with UndoableStringBuilder using the following classes:_

## Classes

### Member interface
The `member` interface defines an updating interface for objects that should be update his pointer of UndoableStringBuilder.
All members that registered as an member of a sender need to implement the member interface.


### Sender interface
The `Sender` interface defines the methods that a sender object should implement.
Objects use this interface to register as members and to remove themselves from being members.
Also `sender` includes a methods to handle the shared object.

### GroupAdmin class
The `GroupAdmin` class implements the Sender interface, which defines methods to `register` and `unregister` Member objects, as well as methods to append and insert strings to the `UndoableStringBuilder` object, `delete` strings from the UndoableStringBuilder, and `undo` previous change on the UndoableStringBuilder.  
The `GroupAdmin` class has a `notifyAllMembers` method that notifies the members that are registered to the GroupAdmin to update their UndoableStringBuilder .
GroupAdmin contains a list of members and an UndoableStringBuilder that the members points to.  
We use a list of memebers we want to update on any change within the UndoableStringBuilder of GroupAdmin.
 

### ConcreteMember class
The `ConcreteMember` class implement the Member interface.
This class represents a Member of a group administered by a GroupAdmin.
ConcreteMember can register and get updates from a GroupAdmin.
ConcreteMember class has a name and own copy of the group's UndoableStringBuilder object.
When the update method is called, the ConcreteMember class updates its internal copy of the UndoableStringBuilder object with the current state of the shared `UndoableStringBuilder`.

## Usage

To use the GroupAdmin and ConcreteMember classes in a program . first , build a GroupAdmin object and a ConcreteMember object.
after that register the ConcreteMember with the GroupAdmin by using the register method. then, call the method to modify the shared UndoableStringBuilder object .The ConcreteMember object will be automatically notified of the change and will update its copy of the UndoableStringBuilder object.
## UML


![diagram](https://user-images.githubusercontent.com/117816462/209584184-8311d348-2df6-4804-80cc-b9d662eac968.png)







