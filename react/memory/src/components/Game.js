import React from "react";
import Grid from "../components/Grid.js";
import utils from "../utils.js";

const COLS = 6;
const ROWS = 6;

const useGameState = (props) => {
  const [visibleItems, setVisibleItems] = React.useState(
    utils.rand(0, ROWS * COLS - 1, utils.rand(0, (ROWS * COLS) / 2, 1)[0] + 3)
  );
  const [allHidden, setAllHidden] = React.useState(false);
  const [wrongItems, setWrongItems] = React.useState([]);
  const [normalItems, setNormalItems] = React.useState(
    utils.range(ROWS * COLS - 1).filter((e) => !visibleItems.includes(e))
  );
  const [counter, setCounter] = React.useState(5);

  React.useEffect(() => {
    if (!props.gameStart) {
      return;
    }
    if (counter === 0) {
      setTimeout(() => setCounter("Play"), 500);
    }
    if (typeof counter != "number" || counter <= 0) {
      setAllHidden(true);
      return;
    }

    setTimeout(() => setCounter(counter - 1), 1000);
  });

  const onItemClick = (item, currentState) => {
    if (!allHidden || visibleItems.length === 0) {
      return;
    }
    if (currentState === "right" || currentState === "wrong") {
      return;
    }

    if (currentState === "normal") {
      setWrongItems(wrongItems.concat(item));
      setNormalItems(normalItems.filter((e) => e !== item));
    } else {
      setVisibleItems(visibleItems.filter((e) => e !== item));
      if (visibleItems.length === 1) {
        setCounter("WOW");
        props.setScore(props.score + 300 + wrongItems.length * -50);
      }
    }
  };

  const calcItemState = (item) => {
    if (!props.gameStart || normalItems.includes(item)) {
      return "normal";
    }

    if (visibleItems.includes(item) && allHidden) {
      return "hidden";
    }

    if (visibleItems.includes(item)) {
      return "visible";
    }

    if (wrongItems.includes(item)) {
      return "wrong";
    }

    return "right";
  };

  return [counter, onItemClick, calcItemState];
};

const Game = (props) => {
  [counter, onItemClick, calcItemState] = useGameState(props);
  return (
    <div>
      <div className="score">{`Score: ${props.score}`}</div>
      <div className="counter">{counter}</div>
      <Grid
        rows={ROWS}
        cols={COLS}
        onItemClick={onItemClick}
        getItemState={calcItemState}
      />
      <button className="start" onClick={() => props.startNewGame()}>
        Start
      </button>
    </div>
  );
};

export default Game;
