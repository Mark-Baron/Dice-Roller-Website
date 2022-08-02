const API_BASE = 'http://localhost:8080/';
let characterId = localStorage.getItem('characterId');
let token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', () => {
    selectCharacter();
});


function selectCharacter() {
    fetch(API_BASE + "characters/" + characterId, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })

        .then(response => response.json())
        .then(character => {
            if ('content' in document.createElement('template')) {
                const dexSection = document.getElementById('dex');
                const tmplDex = document.getElementById('dex-template').content.cloneNode(true);
                tmplDex.querySelector('h3').innerText = character.dexScore;
                dexSection.insertBefore(tmplDex, dexSection.firstChild);

                const strSection = document.getElementById('str');
                const tmplStr = document.getElementById('str-template').content.cloneNode(true);
                tmplStr.querySelector('h3').innerText = character.strengthScore;
                strSection.insertBefore(tmplStr, strSection.firstChild);

                const conSection = document.getElementById('con');
                const tmplCon = document.getElementById('con-template').content.cloneNode(true);
                tmplCon.querySelector('h3').innerText = character.conScore;
                conSection.insertBefore(tmplCon, conSection.firstChild);

                const intSection = document.getElementById('int');
                const tmplInt = document.getElementById('int-template').content.cloneNode(true);
                tmplInt.querySelector('h3').innerText = character.intScore;
                intSection.insertBefore(tmplInt, intSection.firstChild);

                const wisSection = document.getElementById('wis');
                const tmplWis = document.getElementById('wis-template').content.cloneNode(true);
                tmplWis.querySelector('h3').innerText = character.wisScore;
                wisSection.insertBefore(tmplWis, wisSection.firstChild);

                const charSection = document.getElementById('char');
                const tmplChar = document.getElementById('char-template').content.cloneNode(true);
                tmplChar.querySelector('h3').innerText = character.charScore;
                charSection.insertBefore(tmplChar, charSection.firstChild);

            } else {
                console.error('Your browser does not support templates');
            }
        });
}