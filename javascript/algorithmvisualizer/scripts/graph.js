let stage = document.getElementById('stage');
const ROWS = 12;
const COLS = 20;
const NODEWIDTH = (stage.clientWidth/COLS);
const PROP = 0; // increase this to have more nodes with less edges than the MAXDEGREE

// the user will change these values from the form
let NODES = 10;
let MAXDEGREE = 4;

let ansGraph = [];
let ansEdges = [];

main();


function main() {

  // show the form 
  document.getElementById('graphAlgorithms').style.display = 'block';

  //remove all event listener
  let apply = document.getElementById('apply');
  apply.replaceWith(apply.cloneNode(true)); // this a hack since there is no removeAllListener function

  // clear stage
  stage.innerHTML = "";
  ansGraph = [];
  ansEdges = [];

  // generate graph
  for (let i = 0; i < NODES; i++) {
    // generate random place in the logical grid
    let r = Math.floor(Math.random() * ROWS); 
    let c = Math.floor(Math.random() * COLS);
    // console.log(r+" "+c)

    // create a new node to put in the graph
    let node = document.createElement("span");

    // set up width and position
    node.style.width = NODEWIDTH -5 + "px";
    node.style.height = NODEWIDTH -5 + "px";
    node.style.top = r * NODEWIDTH + "px";
    node.style.left = c * NODEWIDTH + "px";

    // add it to the DOM
    stage.appendChild(node);
    ansGraph.push(node);

    // style and functionality
    node.classList.add('ansGraph');
    node.id = ansGraph.length-1;
    node.innerText = ansGraph.length;
    node.style.padding = NODEWIDTH/4 + "px";
     dragElement(node);
    // node.addEventListener("dragover", redrawOnDrag);
  }

  // initialize the edge list
  for (let i = 0; i < ansGraph.length; i++) {
    ansEdges[i] = [];
  }

  // generate random edges
  for (let i = 0; i < ansGraph.length; i++) {
    for (let j = 0; j < MAXDEGREE; j++) {
      let rand = Math.floor(Math.random()*ansGraph.length+PROP);

      // don't make loops
      if(rand == i) {
        j--;
        continue;
      }

      // skip if the node reached MAXDEGREE or it is unlucky node
      if (rand >= ansGraph.length || ansEdges[rand].length == MAXDEGREE) 
        continue;

      // skip if the node reached MAXDEGREE
      if (ansEdges[i].length == MAXDEGREE) 
        break;

      // add to the edge list
      ansEdges[rand].push(i);
      ansEdges[i].push(rand);

      drawLine(i, rand)
    }
  }

  // for the apply button
  document.getElementById('apply').addEventListener("click", applyChanges);
}


// allow edge dragging
function dragElement(elmnt) {
  elmnt.addEventListener('mousedown', dragMouseDown);

  function dragMouseDown(e) {
    e.preventDefault();

    // listen to the event from the document not the element
    document.addEventListener('mouseup', closeDragElement);
    document.addEventListener('mousemove', elementDrag);
  }
  

  // make node follow the mouse
  function elementDrag(e) {
    e.preventDefault();
    if(e.x == 0) return;

    // move the node under the mouse
    elmnt.style.left = e.x - NODEWIDTH/2 + "px";
    elmnt.style.top = e.y - NODEWIDTH/2 + "px";

    // get the node index
    let id = elmnt.id;

    // remove all edges and redraw them
    for (let i of ansEdges[id]) {
      let e1 = document.getElementById(Math.min(id, i)+""+Math.max(id, i));
      e1.remove();
      drawLine(i, id);
    }
  }

  // release the node otherwise the node will follow the mouse indefinityl
  function closeDragElement(e) {
    document.removeEventListener('mouseup', closeDragElement);
    document.removeEventListener('mousemove', elementDrag);
  }

}


// draw a line(edge) between node i and node j in the ansGraph
function drawLine(i, j) {
  let from = ansGraph[i].getBoundingClientRect();
  let to = ansGraph[j].getBoundingClientRect();

  // always make sure the from node is the one to the left(the computation below assumes this)
  if(to.left < from.left) {
    let temp = from;
    from = to;
    to = temp;
  }


  // create the line
  let edge = document.createElement('span');
  edge.id = Math.min(i,j) +""+ Math.max(i,j);
  edge.style.position = "absolute";
  edge.style.border = "1px solid black";

  // the distance (hypotinous)
  const hyp = Math.sqrt(Math.pow(from.left - to.left, 2) + Math.pow(from.top - to.top, 2));

  // the angle degrees
  let deg = Math.acos((to.left - from.left) / hyp);
  if(to.top < from.top)
    deg = -deg;

  edge.style.minWidth = hyp + "px";
  edge.style.left = from.left+from.width/2+"px";
  edge.style.top = from.top+from.height/2+"px";
  edge.style.transformOrigin = "0%";
  edge.style.transform = "rotate("+deg+"rad)";

  // add it to the DOM
  stage.appendChild(edge);
}


// on submitting the form
function applyChanges() {
  MAXDEGREE = document.querySelector('#maxDegrees').value;
  NODES = document.querySelector('#nodes').value;
  main();
}



