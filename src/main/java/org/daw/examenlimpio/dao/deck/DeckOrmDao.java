package org.daw.examenlimpio.dao.deck;

import jakarta.persistence.EntityManager;
import org.daw.examenlimpio.model.Deck;
import org.daw.examenlimpio.util.ConnectionManager;

import java.util.List;

public class DeckOrmDao implements DeckDao{
    @Override
    public Deck addDeck(Deck newDeck) {
        EntityManager em = ConnectionManager.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(newDeck);
            em.getTransaction().commit();
            return newDeck;
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al agregar la deck",e);
        }finally {
            em.close();
        }
    }

    @Override
    public Deck findById(String id) {
        EntityManager em = ConnectionManager.getEntityManager();

        try {
            return em.find(Deck.class,id);
        }catch (Exception e){
            throw new RuntimeException("No se encontro el deck",e);
        }
    }

    @Override
    public List<Deck> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        List<Deck> decks;
        try {
            decks = em.createQuery("SELECT d FROM Deck d",Deck.class).getResultList();
        }catch (Exception e){
            throw new RuntimeException("Error al encontrar los decks",e);
        }
        return decks;
    }

    @Override
    public Deck updateDeck(Deck deck) {
        EntityManager em = ConnectionManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(deck);
            em.getTransaction().commit();
            return deck;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la deck",e);
        }finally {
            em.close();
        }
    }
}
