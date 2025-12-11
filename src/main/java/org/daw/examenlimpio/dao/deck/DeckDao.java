package org.daw.examenlimpio.dao.deck;

import org.daw.examenlimpio.model.Deck;

import java.util.List;

public interface DeckDao {

    Deck addDeck(Deck newDeck);
    Deck findById(String id);
    List<Deck> findAll();
    Deck updateDeck(Deck deck);
}
