package org.daw.examenlimpio.service;

import org.daw.examenlimpio.model.Card;
import org.daw.examenlimpio.model.Deck;

import java.util.List;

public interface DeckService {
    Deck crearDeck(int count) throws Exception;
    List<Card> drawnCards(int count , Deck deck) throws Exception;
    List<Deck> findAll();
    Deck findById(String id);
}
