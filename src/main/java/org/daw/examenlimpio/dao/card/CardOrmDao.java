package org.daw.examenlimpio.dao.card;

import jakarta.persistence.EntityManager;
import org.daw.examenlimpio.model.Card;
import org.daw.examenlimpio.model.Deck;
import org.daw.examenlimpio.util.ConnectionManager;

public class CardOrmDao implements CardDao{
    @Override
    public Card addCard(Card newCard) {
        EntityManager em = ConnectionManager.getEntityManager();

        try {
            em.getTransaction().begin();
            if (newCard.getDeck() != null) {
                Deck managedDeck = em.merge(newCard.getDeck());
                newCard.setDeck(managedDeck);
            }
            em.persist(newCard);
            em.getTransaction().commit();
            return newCard;
        }catch (Exception e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al agregar la carta",e);
        }finally {
            em.close();
        }
    }
}
