const API_BASE = 'http://localhost:8080/';
let userData = {};

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('create-user').addEventListener('click', saveUser);


});

function saveUser() {
    const username = document.getElementById('create-username');
    const password = document.getElementById('create-password');
    userData['username'] = username.value;
    userData['password'] = password.value;

    // save
    fetch(API_BASE + 'register', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then((response) => {
            if (response.ok) {
                alert('Saved!');
            }
        })
        .catch((err) => {
            console.error(err);
            alert('Could not save user!');
        });
}