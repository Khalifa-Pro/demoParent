package services;

import entities.User;
import jakarta.ejb.Remote;

@Remote
public interface IUser {
    public void sInscrire(String nom, String prenom, String nom_utilisateur, String mot_de_passe );
    public User seConnecter(String nom_utilisateur, String mot_de_passe);
}
