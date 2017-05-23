package nl.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

/**
 * Created by ckyoung on 22-May-17.
 */
@WebServlet(name = "MancalaServlet")
public class MancalaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("currentGameState") == null) {
            initiateNewGame(session);
        }

        int clickedHole = getClickedHole(request);
        GameState currentGameState = (GameState) session.getAttribute("currentGameState");

        if (clickedHole > 0) {
            playHole(currentGameState, clickedHole);
        }

        session.setAttribute("isGameOver", currentGameState.isGameOver());

        if (currentGameState.isGameOver()) {
            setAttributesForGameOver(session, currentGameState);
        }

        session.setAttribute("getActivePlayer", currentGameState.getActivePlayer().getName());
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    private void initiateNewGame(HttpSession session) {
        session.setAttribute("currentGameState", new GameState());
    }

    private int getClickedHole(HttpServletRequest request) {
        int clickedHole = 0;
        if (request.getParameter("hole") != null) {
            clickedHole = Integer.parseInt(request.getParameter("hole"));
        }
        return clickedHole;
    }

    private GameState playHole(GameState currentState, int hole) {
        currentState.playHole(hole);
        return currentState;
    }

    private void setAttributesForGameOver(HttpSession session, GameState currentGameState) {
        if (currentGameState.getWinner() != null) {
            session.setAttribute("getWinner", currentGameState.getWinner().getName());
        } else {
            session.setAttribute("getWinner", "Game is tied.");
        }
    }


}
