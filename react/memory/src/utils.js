const utils = {
  rand: (s, e, c) => {
    if (c > e - s - 1) {
      return null;
    }

    const set = new Set();
    while (set.size < c) {
      set.add(Math.floor(Math.random() * (e - s + 1) + s));
    }
    return Array.from(set);
  },
  range: (e) => {
    const arr = [];
    for (let i = 0; i <= e; i++) {
      arr.push(i);
    }
    return arr;
  },
};

export default utils;
