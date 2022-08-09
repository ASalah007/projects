import React from "react";
import ReactDOM from "react-dom";
import Game from "./components/Game.js";

const App = () => {
  const [gameId, setGameId] = React.useState(0);
  const [score, setScore] = React.useState(0);
  const [gameStart, setGameStart] = React.useState(false);
  return (
    <Game
      key={gameId}
      score={score}
      setScore={setScore}
      gameStart={gameStart}
      startNewGame={() => {
        setGameId(gameId + 1);
        setGameStart(true);
      }}
    />
  );
};
ReactDOM.render(<App />, document.getElementById("stage"));
