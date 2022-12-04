# Tictactoe Game

Tic-tac-toe is played on a three-by-three grid by two 
players, who alternately place the marks X and O in one 
of the nine spaces in the grid.

The player who succeeds in placing three of their marks 
in a horizontal, vertical, or diagonal row is the winner.

## Setup
Source code written by Java. So be sure that the JDK is 
already installed on your computer. If you don't have any 
JDK you can download it from Oracle or else.

After you have the JDK, you can clone or download this repository
from GitHub. You can use any IDE that has Java support or you can 
just use cmd for compile and running.

### The Board
```
---------
|       |
|       |
|       |
---------
```
To play you are giving a coordinate to computer like "2 3" (x,y axes). 
This means second column third row is the picked coordinate. 
Program puts an 'X' or 'O' in process on the players turn.

#### Example
```
---------
|       |
|       |
|       |
---------
>1 1
---------
| O     |
|       |
|       |
---------
>2 2
---------
| O     |
|   X   |
|       |
---------
```
The greater-than symbol followed by a space (> ) represents 
the user input. Note that it's not part of the input.