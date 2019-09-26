package com.trzewik.TomcatWordCloud;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.trzewik.TomcatWordCloud.AddObservers.addObservers;

/**
 * @author Agnieszka Trzewik
 */
public class ServletForFile extends HttpServlet {

    @AddObserver
    private List<Observer> observerList;

    public ServletForFile(){
        addObservers(this);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ContentInfo contentInfo = new Gson().fromJson(request.getReader(), ContentInfo.class);

        PrintWriter writer = response.getWriter();

        if (contentInfo != null) {
            observerList.forEach(observer -> {
                try {
                    observer.updateText(contentInfo.isPath(), contentInfo.getContent());
                    response.setContentType("text");
                    response.setStatus(HttpServletResponse.SC_OK);
                    writer.println("Proper content info");
                    Logger.getLogger(ServletForFile.class.getName()).log(Level.INFO, contentInfo + " was added.");

                } catch (WrongPathException e) {
                    response.setContentType("text");
                    response.setStatus(HttpServletResponse.SC_OK);
                    writer.println("Wrong path file was given");
                    Logger.getLogger(ServletForFile.class.getName()).log(Level.WARNING, contentInfo + " was wrong.", e);
                }
            });
        }
        else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text");
            writer.println("Wrong request body was given");
            Logger.getLogger(ServletForFile.class.getName()).log(Level.WARNING, "Wrong request body was given");
        }
    }

}
