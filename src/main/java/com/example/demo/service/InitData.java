package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class InitData {

    @Autowired
    private EntityManager em;

    public void insertTestData() {
        Client client1 = newClient("PETRILLO", "Alexandre");
        em.persist(client1);

        Client client2  = new Client();
        client2.setNom("PROVIST");
        client2.setPrenom("Alain");
        em.persist(client2);

        Client client3  = new Client();
        client3.setNom("BON");
        client3.setPrenom("Jean");
        em.persist(client3);

        Article article1 = new Article();
        article1.setLibelle("Lunettes de natation Fastskin Elite Mirror");
        article1.setPrix(54.50);
        em.persist(article1);
        
        Article article2 = new Article();
        article2.setLibelle("Serviette de sport Border");
        article2.setPrix(25.00);
        em.persist(article2);

        Article article3 = new Article();
        article3.setLibelle("Slip de bain Mango Bango Allover 14 cm");
        article3.setPrix(35.00);
        em.persist(article3);
        
        Facture facture = newFacture(client1);
        em.persist(facture);
        em.persist(newLigneFacture(article1, facture, 1));
        em.persist(newLigneFacture(article3, facture, 2));
        
        Facture facture2 = newFacture(client1);
        em.persist(facture2);
        em.persist(newLigneFacture(article1, facture2, 1));
        em.persist(newLigneFacture(article2, facture2, 5));
    }

    private Client newClient(String nom, String prenom) {
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        return client;
    }

    private Article newArticle(String libelle, double prix) {
        Article article = new Article();
        article.setLibelle(libelle);
        article.setPrix(prix);
        return article;
    }

    private Facture newFacture(Client client) {
        Facture facture = new Facture();
        facture.setClient(client);
        return facture;
    }

    private LigneFacture newLigneFacture(Article article, Facture facture, int quantite) {
        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article);
        ligneFacture1.setQuantite(quantite);
        return ligneFacture1;
    }
}
