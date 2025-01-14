# Game of Life

A simple implementation of Conway's Game of Life using a randomly generated board of cells. The board evolves over time based on simple rules:
- Any live cell with fewer than two live neighbors dies (underpopulation).
- Any live cell with two or three live neighbors lives on to the next generation (stability).
- Any live cell with more than three live neighbors dies (overpopulation).
- Any dead cell with exactly three live neighbors becomes a live cell (reproduction).

## Features
- Random board generation
- Conway's Game of Life simulation
- Console rendering with "o" for live cells and spaces for dead cells

## Setup

1. Clone the repository
    ```bash
    git clone https://github.com/your-repository/random-board.git
    cd random-board
    ```

2. Run the program
    ```bash
    javac randomBoard.java
    java randomBoard
    ```
