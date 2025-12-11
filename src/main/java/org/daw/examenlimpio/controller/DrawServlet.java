package org.daw.examenlimpio.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.daw.examenlimpio.model.Deck;
import org.daw.examenlimpio.service.DeckService;
import org.daw.examenlimpio.service.DeckServiceImpl;

import java.io.IOException;

@WebServlet(name = "drawServlet", value = "/draw")
public class DrawServlet extends HttpServlet {
    private DeckService service;

    @Override
    public void init() throws ServletException {
        service = new DeckServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String deckId = request.getParameter("deckId");
        int numberOfCards = Integer.parseInt(request.getParameter("numberOfCards"));
        Deck deck = service.findById(deckId);
        try {
            service.drawnCards(numberOfCards,deck);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("deck", deck);
        response.sendRedirect("decks?id="+deckId);
    }
}
