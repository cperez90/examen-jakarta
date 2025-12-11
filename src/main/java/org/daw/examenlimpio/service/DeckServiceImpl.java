package org.daw.examenlimpio.service;

import com.google.gson.JsonObject;
import org.daw.examenlimpio.dao.card.CardDao;
import org.daw.examenlimpio.dao.card.CardOrmDao;
import org.daw.examenlimpio.dao.deck.DeckDao;
import org.daw.examenlimpio.dao.deck.DeckOrmDao;
import org.daw.examenlimpio.model.Card;
import org.daw.examenlimpio.model.Deck;
import org.daw.examenlimpio.util.ApiProvider;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DeckServiceImpl implements DeckService{

    private static final URI BASE_URL = URI.create("https://www.deckofcardsapi.com/api/deck/");
    DeckDao deckDao = new DeckOrmDao();
    CardDao cardDao = new CardOrmDao();

    @Override
    public Deck crearDeck(int count) throws Exception {
        JsonObject json = ApiProvider.parseToJson(ApiProvider.requestApi(BASE_URL+"new/shuffle/?deck_count="+count));
        Deck deck = new Deck();
        deck.setId(json.get("deck_id").getAsString());
        deck.setRemaining(json.get("remaining").getAsInt());

        deckDao.addDeck(deck);
        return deckDao.findById(deck.getId());
    }

    @Override
    public List<Card> drawnCards(int count, Deck deck) throws Exception {
        JsonObject json = ApiProvider.parseToJson(ApiProvider.requestApi(BASE_URL+deck.getId()+"/draw/?count="+count));
        List<JsonObject> list = ApiProvider.getListJsonObjects(json,"cards");

        for (JsonObject c : list){
            Card card = new Card();

            card.setCode(c.get("code").getAsString());
            card.setImage(c.get("image").getAsString());
            card.setValue(c.get("value").getAsString());
            card.setSuit(c.get("suit").getAsString());
            card.setDeck(deck);
            cardDao.addCard(card);
            deck.getDrawnCards().add(card);
        }
        deck.setRemaining(json.get("remaining").getAsInt());
        deckDao.updateDeck(deck);
        return deck.getDrawnCards();
    }

    @Override
    public List<Deck> findAll() {
        return deckDao.findAll();
    }

    @Override
    public Deck findById(String id) {
        return deckDao.findById(id);
    }
}
