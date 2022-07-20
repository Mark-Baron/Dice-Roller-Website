const API_BASE = 'http://localhost:8080/';
let userData = {};
let loginData = {};

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('create-user').addEventListener('click', saveUser);
    document.getElementById('login-button').addEventListener('click', login);

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

function login() {
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    loginData['username'] = username.value;
    loginData['password'] = password.value;

    fetch(API_BASE + 'login', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
        .then((response) => {
            if (response.ok) {
                alert('Logged In!');
            }
        })
        .catch((err) => {
            console.error(err);
            alert('Could not login!');
        });
}