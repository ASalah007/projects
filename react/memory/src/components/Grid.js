const stateColors = {
  visible: "#3B9AE1",
  hidden: "white",
  normal: "white",
  right: "#3FA796",
  wrong: "#EB1D36",
};
const Grid = (props) => {
  const GridItem = (props) => {
    return (
      <span
        className="grid-item"
        style={{ backgroundColor: stateColors[props.state] }}
        onClick={() => props.onClick(props.id, props.state)}
      ></span>
    );
  };

  const grid = [];
  for (let i = 0; i < props.rows; i++) {
    let row = [];
    for (let j = 0; j < props.cols; j++) {
      row.push(
        <GridItem
          key={i * props.cols + j}
          id={i * props.cols + j}
          state={props.getItemState(i * props.cols + j)}
          onClick={props.onItemClick}
        />
      );
    }
    grid.push(
      <div key={i} className="grid-row">
        {row}
      </div>
    );
  }

  return <div className="grid">{grid}</div>;
};

export default Grid;
