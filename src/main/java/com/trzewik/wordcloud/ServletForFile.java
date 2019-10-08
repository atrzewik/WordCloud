package com.trzewik.wordcloud;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.trzewik.wordcloud.AddObservers.addObservers;

/**
 * @author Agnieszka Trzewik
 */
public class ServletForFile extends HttpServlet implements SubjectOfObservationForText {

    @AddObserver
    private List<Observer> observerList;

    public ServletForFile(){
        addObservers(this);
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientOrigin = request.getHeader("origin");
        Logger.getLogger(ServletForFile.class.getName()).log(Level.INFO, "Client origin: " + clientOrigin);

        ContentInfo contentInfo = new Gson().fromJson(request.getReader(), ContentInfo.class);

        PrintWriter writer = response.getWriter();

        if (contentInfo != null) {
            observerList.forEach(observer -> {
                try {
                    notifyAboutText(observer, contentInfo);
                    response.setContentType("text/plain");
                    response.setStatus(HttpServletResponse.SC_OK);
                    writer.println("Proper content info");
                    Logger.getLogger(ServletForFile.class.getName()).log(Level.INFO, contentInfo + " was added.");

                } catch (WrongPathException e) {
                    response.setContentType("text/plain");
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    writer.println("Wrong path file was given");
                    Logger.getLogger(ServletForFile.class.getName()).log(Level.WARNING, contentInfo + " was wrong.");
                }
            });
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain");
            writer.println("Wrong request body was given");
            Logger.getLogger(ServletForFile.class.getName()).log(Level.WARNING, "Wrong request body was given");
        }
    }

    @Override
    public void notifyAboutText(Observer observer, ContentInfo contentInfo) throws WrongPathException {
        observer.updateText(contentInfo.isPath(), contentInfo.getContent());
    }
}
