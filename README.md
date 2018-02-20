# Othello

### How to run the application and tests
1. Git clone
2. With intellij, File -> New -> Project from Existing Sources -> Select build.gradle
3. Run OthelloConsoleMain.java to start the game
4. All tests reside in src/test/java

### Board display explanation

Sample board
````text
1 --------
2 -----*--
3 ----O*--
4 --XXO*--
5 ---XO*--
6 -----*--
7 --------
8 --------
  abcdefgh
````

 - X / O indicate cell occupied by Player X / O
 - `-` indicate empty cell
 - `*` indicate possible moves