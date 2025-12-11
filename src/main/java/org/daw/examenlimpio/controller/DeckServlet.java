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
import java.util.List;

@WebServlet(name="deckServlet",value = "/decks")
public class DeckServlet extends HttpServlet {
    private DeckService service;

    @Override
    public void init() throws ServletException {
        this.service = new DeckServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("id") == null){
            List<Deck> decks = service.findAll();

            request.setAttribute("decks", decks);

            request.getRequestDispatcher("decks.jsp").forward(request, response);
        }
        else {
            String id = request.getParameter("id");

            Deck deck = service.findById(id);

            request.setAttribute("deck", deck);
            request.getRequestDispatcher("/deck.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberOfDecks = request.getParameter("numberOfDecks");

        if (numberOfDecks != null) {
            Deck deck;
            try {
                deck = service.crearDeck(Integer.parseInt(numberOfDecks));
                request.setAttribute("deck", deck);
            } catch (Exception e) {
                throw new ServletException("Error fetching deck: " + e.getMessage(), e);
            }
        }

        response.sendRedirect("decks");

    }
}
