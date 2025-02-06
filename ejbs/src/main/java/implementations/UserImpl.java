package implementations;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import entities.User;
import services.IUser;
import utils.HashSecure;

@Stateless
@LocalBean
public class UserImpl implements IUser {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    private HashSecure hashSecure = new HashSecure();

    @Override
    public void sInscrire(String nom, String prenom, String nom_utilisateur, String mot_de_passe) {
        User user = new User();
        try {
            // Générer un sel unique
            String salt = hashSecure.generateSalt();

            // Hacher le mot de passe avec le sel généré
            String hashedPassword = hashSecure.hashPassword(mot_de_passe, salt);

            // Configurer les informations de l'utilisateur
            user.setFirstName(prenom);
            user.setLastName(nom);
            user.setUserName(nom_utilisateur);
            user.setPassword(hashedPassword); // Mot de passe haché
            user.setSalt(salt); // Stocker le sel dans la base de données

            // Sauvegarder l'utilisateur dans la base de données
            em.persist(user); // L'EntityManager gère la transaction automatiquement

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User seConnecter(String nom_utilisateur, String mot_de_passe) {
        User user = null;
        try {
            user = em.createQuery("SELECT u FROM User u WHERE u.userName = :nom_utilisateur", User.class)
                    .setParameter("nom_utilisateur", nom_utilisateur)
                    .getSingleResult();

            if (user != null) {
                String storedPasswordHash = user.getPassword();
                String storedSalt = user.getSalt();

                if (hashSecure.checkPassword(mot_de_passe, storedPasswordHash, storedSalt)) {
                    return user; // Connexion réussie
                }
            }
        } catch (NoResultException e) {
            // Aucun utilisateur trouvé
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

