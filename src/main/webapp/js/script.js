document.addEventListener('DOMContentLoaded', function () {
    // Form di login
    const loginForm = document.getElementById('loginForm');
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const username = document.getElementById('loginUsername').value;
        // Simula il login
        sessionStorage.setItem('username', username);
        updateUserInterface();
        // Chiudi il modale
        bootstrap.Modal.getInstance(document.getElementById('loginModal')).hide();
    });

    // Form di registrazione
    const registerForm = document.getElementById('registerForm');
    registerForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const username = document.getElementById('registerUsername').value;
        // Simula la registrazione
        sessionStorage.setItem('username', username);
        updateUserInterface();
        // Chiudi il modale
        bootstrap.Modal.getInstance(document.getElementById('registerModal')).hide();
    });

    // Aggiorna l'interfaccia utente
    function updateUserInterface() {
        const userInfo = document.getElementById('user-info');
        const username = sessionStorage.getItem('username');
        if (username) {
            userInfo.innerHTML = `<span class="navbar-text">Benvenuto, ${username}</span>`;
        } else {
            userInfo.innerHTML = '';
        }
    }

    // Inizializza l'interfaccia utente
    updateUserInterface();
});


    function setPriceRange(min, max) {
    document.getElementById('minPrice').value = min;
    document.getElementById('maxPrice').value = max;
}

