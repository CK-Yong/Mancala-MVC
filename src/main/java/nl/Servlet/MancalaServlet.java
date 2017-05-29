package nl.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
        GameState currentGameState = (GameState) session.getAttribute("gameState");

        if (session.getAttribute("gameState") == null) {
            currentGameState = new GameState();
            session.setAttribute("gameState", currentGameState);
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            return; //prevents game from playing automatically upon refresh
        }

        int clickedHole = getClickedHole(request);

        if (clickedHole > 0 & clickedHole < 14) {
            currentGameState.playHole(clickedHole);
        }

        session.setAttribute("gameState", currentGameState);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }


    private int getClickedHole(HttpServletRequest request) {
        int clickedHole = 0;
        if (request.getParameter("hole") != null) {
            try {
                clickedHole = Integer.parseInt(request.getParameter("hole"));
            } catch (NumberFormatException e) {
                clickedHole = 0;
            }
        }
        return clickedHole;
    }
}
