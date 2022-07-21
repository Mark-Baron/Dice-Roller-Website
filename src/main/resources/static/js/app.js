const API_BASE = 'http://localhost:8080/';
let characterData = {};
let token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('create-character').addEventListener('click', saveCharacter);
});

function saveCharacter() {
  const characterName = document.getElementById('char-name');
  const characterClass = document.getElementById('class');
  const characterLvl = document.getElementById('level');
  const strengthScore = document.getElementById('strength');
  const dexScore = document.getElementById('dexterity');
  const conScore = document.getElementById('constitution');
  const intScore = document.getElementById('intelligence');
  const wisScore = document.getElementById('wisdom');
  const charScore = document.getElementById('charisma');
  const characterSkills = document.querySelectorAll(".skill");

  characterData['characterName'] = characterName.value;
  characterData['characterClass'] = characterClass.value;
  characterData['characterLvl'] = characterLvl.value;
  characterData['strengthScore'] = strengthScore.value;
  characterData['dexScore'] = dexScore.value;
  characterData['conScore'] = conScore.value;
  characterData['intScore'] = intScore.value;
  characterData['wisScore'] = wisScore.value;
  characterData['charScore'] = charScore.value;
  characterData['characterSkills'] = [];

  for (let skill of characterSkills) {
    if (skill.checked === true) {
      characterData['characterSkills'].push(skill.getAttribute("name"));
    }
  }

  fetch(API_BASE + 'character/create', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify(characterData)
  })
    .then((response) => {
      if (response.ok) {
        alert('Character Saved!');
      }
    })
    .catch((err) => {
      console.error(err);
      alert('Could not save character!');
    });
}

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


