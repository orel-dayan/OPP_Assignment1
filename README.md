
## _OOP Course Exercise 1_
_Authors [orel-dayan@]() & [@roy-asraf1](https://github.com/roy-asraf1)_


### Built With

* Editor : IntelliJ IDEA
* Language: Java
* JDK: 19.0.1
* Maven 4.0.0
* JUnit 5.9.0

### How to use our project

Clone the repo
   ```sh
   git clone https://github.com/orel-dayan/OPP_Assignmment1.git
   ```

To run the project, open it in the IntelliJ IDEA editor.
then enter to pom.xml file and rebuild it.


## About the project

### UndoableStringBuilder

The UndoableStringBuilder is a StringBuilder that can use the undo method to go back to his previous state before his last change.
It can undo in the following methods :
- append
- delete
- reverse
- replace
- insert


You can find the UndoableStringBuilder class in this [this](https://github.com/orel-dayan/assigment-0) repository.  

### Observer Design Pattern
The `observer` design pattern is a behavioral design pattern that defines a one-to-many dependency between objects.
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
The `GroupAdmin` class implement the member interface.
`GroupAdmin` has methods that oparate  append ,delete,insert and undo methods from the UndoableStringBuilder , by calling them from the UndoableStringBuilder class.
After each of these called operations, a `notifyAllMembers` method is being called.
The `notifyAllMembers` method is going through all the members contained in the member's list and for each member it calls the Update method, which is being operated in each member in the ConcreteMember class.
GroupAdmin contains a list of members and an UndoableStringBuilder that the members points to.  
We use a list of members we want to update on any change within the UndoableStringBuilder of GroupAdmin.


### ConcreteMember class
The `ConcreteMember` class implement the Member interface.
This class represents a Member of a group administered by a GroupAdmin.
ConcreteMember can register and get updates from a GroupAdmin.
ConcreteMember class has a name and own copy of the group's UndoableStringBuilder object.
When the update method is called, the ConcreteMember class updates its  copy of the UndoableStringBuilder object with the current state of the `UndoableStringBuilder` object.

## Usage

To use the GroupAdmin and ConcreteMember classes in a program . first ,build a GdroupAdmin object and a ConcreteMember object.
After that, register the ConcreteMember with the GroupAdmin by using the register method. then, call the method to modify the UndoableStringBuilder object .The ConcreteMember object will be automatically notified of the change and will update its copy of the UndoableStringBuilder object.

## UML

![image](https://user-images.githubusercontent.com/117816462/209984745-6e4919a6-4312-4419-b2ea-08a85f5ec724.png)


## Part B

package `test` included 3 classes:
- TestMembers
- Tests
- JvmUtilities

In `TestMembers` class  we'd tested the functionality of the update method of the ConcreteMember class and the functionality of GroupAdmin and his abilities and how it can handle different situations including passing an already registered member. 

In addition, we asked to track the size of the object in the heap. 
We found out that one of the parameters for checking the efficiency of the code is the size of the object in the JVM memory. 
we saw that in the `JvmUtilities` class there are 3 static methods that we used to test the efficiency.

__The method objectFootprint__ : shows all the references that the object contains the size of each reference.

__The method objectTotalSize__ : show the total size of the object. This method is the most relevant to us in this assignment.

__The jvmInfo method__ : show the process ID of the JVM and the total amount of memory for the benefit of the program.  
 ### we have added documentation to this part:
 in this test we checked the total size of GroupAdmin and ConcreteMember object before and after append and undo actions:
 
![image](https://user-images.githubusercontent.com/117816462/210057005-95f73363-0fb1-4e06-a9e1-451f60f1d482.png)


![image](https://user-images.githubusercontent.com/117816462/210057112-813ff5e5-aa7a-4f87-8b34-d1452dd4dbed.png)

in this test we checked the total size of GroupAdmin and ConcreteMember object before and after insert and delete actions:
 

![image](https://user-images.githubusercontent.com/117816462/210057294-65a2ca5d-b7e3-45f6-93bf-73402daca5a2.png)


![image](https://user-images.githubusercontent.com/117816462/210057531-d3690b54-e45d-40d9-9b3d-1a2a112d89cd.png)










