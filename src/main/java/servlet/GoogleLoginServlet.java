package servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authorizationUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly"
                + "&access_type=offline"
                + "&include_granted_scopes=true"
                + "&response_type=code"
                + "&state=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"
                + "&redirect_uri=http://localhost:8080/callback"
                + "&client_id=34703303946-snsg5p13jmullq38d0vfc24jnao8rtgm.apps.googleusercontent.com";

        response.sendRedirect(authorizationUrl);
    }
}
