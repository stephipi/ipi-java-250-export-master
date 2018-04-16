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

        Client client1  = new Client();
        client1.setNom("PETRILLO");
        client1.setPrenom("Alexandre");
        em.persist(client1);

        Client client2  = new Client();
        client2.setNom("DUPOND");
        client2.setPrenom("Patrick");
        em.persist(client2);

        Client client3  = new Client();
        client3.setNom("BON");
        client3.setPrenom("Jean");
        em.persist(client3);

        Facture facture = new Facture();
        facture.setClient(client1);
        em.persist(facture);
 
        Facture facture2 = new Facture();
        facture2.setClient(client2);
        em.persist(facture2);
      
        Facture facture3 = new Facture();
        facture3.setClient(client2);
        em.persist(facture3);

        Facture facture4 = new Facture();
        facture3.setClient(client1);
        em.persist(facture4);

        Article article1 = new Article();
        article1.setLibelle("Lunettes de natation Fastskin Elite Mirror");
        article1.setPrix(54.00);
        em.persist(article1);
        
        Article article2 = new Article();
        article2.setLibelle("Serviette de sport Border");
        article2.setPrix(25.00);
        em.persist(article2);

        Article article3 = new Article();
        article3.setLibelle("Slip de bain Mango Bango Allover 14 cm");
        article3.setPrix(35.00);
        em.persist(article3);
        
        LigneFacture ligneFacture1 = new LigneFacture();
        ligneFacture1.setFacture(facture);
        ligneFacture1.setArticle(article1);
        ligneFacture1.setQuantite(1);
        em.persist(ligneFacture1);

        LigneFacture ligneFacture2 = new LigneFacture();
        ligneFacture2.setFacture(facture2);
        ligneFacture2.setArticle(article2);
        ligneFacture2.setQuantite(3);
        em.persist(ligneFacture2);
        
        LigneFacture ligneFacture3 = new LigneFacture();
        ligneFacture3.setFacture(facture);
        ligneFacture3.setArticle(article1);
        ligneFacture3.setQuantite(1);
        em.persist(ligneFacture3);

        LigneFacture ligneFacture4 = new LigneFacture();
        ligneFacture4.setFacture(facture2);
        ligneFacture4.setArticle(article3);
        ligneFacture4.setQuantite(1);
        em.persist(ligneFacture4);

    }
}
