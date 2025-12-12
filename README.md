# Xococ - Android XO Game

An Android Tic-Tac-Toe (XO) game with a clean UI, built using Java and Gradle.

## Features

- **Clean UI**: Modern, intuitive interface with custom styling
- **Offline Mode vs AI**: Play against an intelligent AI opponent using the Minimax algorithm
- **Local Multiplayer**: Two-player mode on the same device
- **Game Mode Selection**: Choose between AI and multiplayer modes from the main menu

## Technical Details

- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 33 (Android 13)
- **Build System**: Gradle 7.5
- **Android Gradle Plugin**: 7.4.2
- **Language**: Java 8

## Project Structure

```
app/
├── src/main/
│   ├── java/com/xococ/game/
│   │   ├── AIPlayer.java         # AI opponent with Minimax algorithm
│   │   ├── GameBoard.java        # Game logic and state management
│   │   ├── MainActivity.java     # Main game screen
│   │   └── MenuActivity.java     # Game mode selection screen
│   ├── res/
│   │   ├── drawable/             # Custom button and cell backgrounds
│   │   ├── layout/               # XML layouts for activities
│   │   ├── mipmap-*/             # Launcher icons
│   │   └── values/               # Colors, strings, themes
│   └── AndroidManifest.xml
└── build.gradle
```

## Building and Running

### Prerequisites

- Android Studio (latest version recommended)
- Android SDK with minimum SDK 21 installed
- JDK 8 or higher

### Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/jxjxjhdzmbos-bot/Xococ.git
   cd Xococ
   ```

2. Open the project in Android Studio

3. Sync Gradle files

4. Build and run on an emulator or physical device

Alternatively, build from command line:
```bash
./gradlew build
./gradlew installDebug
```

## How to Play

1. **Launch the app** - You'll see the main menu
2. **Choose a game mode**:
   - **Play vs AI**: Challenge the intelligent AI opponent
   - **Play vs Player**: Play with a friend on the same device
3. **Make your move** - Tap on any empty cell to place your mark (X or O)
4. **Win the game** - Get three in a row horizontally, vertically, or diagonally
5. **Reset** - Use the Reset Game button to start a new game

## Game Features

### AI Opponent
The AI uses the Minimax algorithm to provide a challenging opponent that makes optimal moves. The AI is nearly unbeatable when playing perfectly!

### Game Logic
- Turn-based gameplay
- Win detection for rows, columns, and diagonals
- Draw detection when the board is full
- Input validation to prevent invalid moves

### UI Features
- Clean, modern design with Material Design components
- Visual feedback for game state
- Status display showing whose turn it is
- Reset button for quick game restart

## License

This project is open source and available for educational purposes.