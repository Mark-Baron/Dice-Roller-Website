function statRoll() {
  document.getElementById("stat_roll").reset();
  let rolls = [];
  let rollTotals = [];
  for (let i = 0; i < 6; i++) {
    var roll1 = Math.floor(Math.random() * 6) + 1;
    rolls.push(roll1);
    var roll2 = Math.floor(Math.random() * 6) + 1;
    rolls.push(roll2);
    var roll3 = Math.floor(Math.random() * 6) + 1;
    rolls.push(roll3);
    var roll4 = Math.floor(Math.random() * 6) + 1;
    rolls.push(roll4);

    var lowRoll = 10;
    for (let i = 0; i < rolls.length; i++) {
      if (rolls[i] < lowRoll) {
        lowRoll = rolls[i];
      }
    }
    for (let i = 0; i < rolls.length; i++) {
      if (rolls[i] == lowRoll) {
        rolls[i] = 0;
        break;
      }
    }
    var rollTotal = 0;
    for (let i = 0; i < rolls.length; i++) {
      rollTotal += rolls[i];
    }

    rollTotals.push(rollTotal);
    rolls = [];
  }
  for (let i = 0; i < rollTotals.length; i++) {
    if (i == 5) {
      document.getElementById("stat_roll_display").value += rollTotals[i];
    } else {
      document.getElementById("stat_roll_display").value += rollTotals[i] + ", ";
    }
  }
}
