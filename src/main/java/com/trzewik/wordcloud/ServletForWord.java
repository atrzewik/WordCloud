package com.trzewik.wordcloud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.trzewik.wordcloud.AddObservers.addObservers;

/**
 * @author Agnieszka Trzewik
 */
public class ServletForWord extends HttpServlet implements SubjectOfObservationForWord{

    @AddObserver
    private List<Observer> observerList;

    private int quantityOfWord;

    public ServletForWord(){
        addObservers(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String clientOrigin = request.getHeader("origin");
        Logger.getLogger(ServletForFile.class.getName()).log(Level.INFO, "Client origin: " + clientOrigin);

        String searchWord = request.getParameter("searchWord");
        PrintWriter writer = response.getWriter();

        if (searchWord != null){
            observerList.forEach(observer -> quantityOfWord = retrieveAmountOfWord(observer, searchWord));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            writer.println("{\"number of words\": \"" + quantityOfWord + "\"}");
            Logger.getLogger(ServletForFile.class.getName()).log(Level.INFO, "Calculation done. (number of words: " + quantityOfWord + ")");
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain");
            writer.println("Wrong parameter was given");
            Logger.getLogger(ServletForFile.class.getName()).log(Level.WARNING, "Wrong parameter was given");
        }

    }

    @Override
    public int retrieveAmountOfWord(Observer observer, String searchWord) {
        return observer.calculateQuantityOfWord(searchWord);
    }
}
