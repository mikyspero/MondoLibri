package com.azienda.shop.businessLogic;

import com.azienda.shop.dao.UserDAO;
import com.azienda.shop.model.User;
import com.azienda.shop.utils.PasswordHasher;

import javax.persistence.EntityManager;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

public class UserService extends AbstractService<User> {

    // Controlla se la registrazione di un utente è consentita verificando la presenza di username e email già esistenti nel sistema.
    private boolean allowRegister(User toBeRegistered) {
        // Verifica se esiste un utente con lo stesso username
        User sameName = ((UserDAO) getDao()).findByName(toBeRegistered.getUsername());
        if (sameName != null) {
            throw new RuntimeException("Username già esistente");
        }

        // Verifica se esiste un utente con la stessa email
        User sameMail = ((UserDAO) getDao()).findByEmail(toBeRegistered.getEmail());
        if (sameMail != null) {
            throw new RuntimeException("Email già utilizzata");
        }

        return true;
    }

    // Registra un nuovo utente nel sistema. Esegue la registrazione all'interno di una transazione per garantire la coerenza dei dati.
    public User register(User toBeRegistered) {
        return executeTransaction(() -> {
            if (allowRegister(toBeRegistered)) {
                User registeredUser = ((UserDAO) getDao()).create(toBeRegistered);
                return registeredUser;
            } else {
                return null;
            }
        });
    }

    // Esegue il login di un utente autenticandolo tramite username o email e password. Si basa su una funzione per recuperare l'utente in base all'identificatore fornito.
    protected User login(String identifier, String password, Function<String, User> findByIdentifier) {
        return executeTransaction(() -> {
            try {
                User user = findByIdentifier.apply(identifier);

                // Controlla se l'utente esiste
                if (user == null) {
                    throw new RuntimeException("Utente non trovato con l'identificatore fornito");
                }

                // Effettua l'hashing della password fornita
                String hashedPassword = PasswordHasher.hashPassword(password);

                // Controlla se la password è corretta
                if (user.getPassword().equals(hashedPassword)) {
                    return user;
                } else {
                    throw new RuntimeException("Password errata");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Errore durante l'hashing della password", e);
            }
        });
    }

    // Esegue il login utilizzando l'username e la password dell'utente.
    public User loginWithUser(String username, String password) {
        return login(username, password, (name) -> ((UserDAO) getDao()).findByName(name));
    }

    // Esegue il login utilizzando l'indirizzo email e la password dell'utente.
    public User loginWithEmail(String email, String password) {
        return login(email, password, (mail) -> ((UserDAO) getDao()).findByEmail(mail));
    }

    // Costruttore della classe che riceve l'EntityManager per l'accesso al database e l'implementazione di UserDAO per interagire con i dati degli utenti.
    public UserService(EntityManager manager, UserDAO dao) {
        super(manager, dao);
    }
}
