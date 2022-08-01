const API_BASE = 'http://localhost:8080/';
let token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', () => {
    getCharacters();
});

function getCharacters() {
    fetch(API_BASE + 'characters', {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => response.json())
        .then(json => {

            if ('content' in document.createElement('template')) {
                const main = document.getElementById('main');
                json.forEach(character => {
                    const tmpl = document.getElementById('character-template').content.cloneNode(true);
                    tmpl.querySelector('h3').innerText = character.characterName;
                    tmpl.querySelector('h4').innerText = character.characterLvl + " " + character.characterClass;
                    main.appendChild(tmpl);
                });
            } else {
                console.error('Your browser does not support templates');
            }
        });
}
