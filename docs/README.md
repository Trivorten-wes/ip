# Hermes User Guide

Hermes is a simple app for managing tasks meant to be used via a
Command Line Interface (CLI).

Notes about the command format
- Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in send w/WALLET_NAME, WALLET_NAME is a parameter which can be used as w/alice.
- Items in square brackets are optional.
- Command word in case-insensitive

## Adding Tasks
There are 3 different types of tasks that can be added:
Todos, Deadlines and Events
### Todo
Format: todo DESCRIPTION

- Adds a todo task with the specified description to the list.

Example: todo complete user guide

### Deadline
Format: deadline DESCRIPTION by:DATE [TIME]

- Adds a deadline task with the specified description and with the due date set to the list.
- DATE and TIME takes the format of dd/MM/yyyy HH:mm

Example: deadline finish ip by: 06/03/2026 23:59

### Event
Format: event DESCRIPTION from: DATE [TIME] to: DATE [TIME]

- Adds an event task with the specified description and with a start and end date to the list.
- DATE and TIME takes the format of dd/MM/yyyy HH:mm

Example: event CS2113 lecture from: 06/03/2026 16:00 to: 06/03/2026 18:00

## List
Shows the list of all the tasks added to your list so far.
Format: list

- Includes information of what kind of task 
- Includes information of if the task is marked

## Mark
Mark a task as completed

Format: mark INDEX

- Marks the task specified at INDEX
- The index refers to the index number shown in the displayed task list
- INDEX has to be a positive integer. 1, 2, 3...

Example: mark 2

## Unmark
Mark a task as completed

Format: unmark INDEX

- Unmarks the task specified at INDEX
- The index refers to the index number shown in the displayed task list
- INDEX has to be a positive integer. 1, 2, 3...

Example: unmark 3
## Delete
Delete a task from the list

Format: delete INDEX

- Deletes the task specified at INDEX
- The index refers to the index number shown in the displayed task list
- INDEX has to be a positive integer. 1, 2, 3...

Example: delete 1

## Find
Find tasks which contain any of the given keywords

Format: find KEYWORD [MORE_KEYWORD]

- Search is case-sensitive
- The order of the keywords does not matter
- Can only search the description not the date/time
- Matches partial words eg. keyword "lec" will return "lecture"

Example: find lecture homework

## Exit
Exits the program

Format: bye

## Saving the data
Data is automatically saved whenever changes are made to the list.
It is stored in the "data" file inside the tasks.txt.
Tasks are automatically loaded upon start up so Hermes remembers tasks from previous launches.
