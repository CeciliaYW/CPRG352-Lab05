import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cecilia Wang
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get session
        HttpSession session = request.getSession();

        //welcome page is ShoppingList page
        //check if userName attribute is null or not, if yes redirect to login page.
        String username = (String) session.getAttribute("userName");

        if (username != null) {
           String actionValue = request.getParameter("action");
            if (actionValue != null && actionValue.equals("logout") ) {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;

        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get session
        HttpSession session = request.getSession();
        //handle registration. if user_name is not yet input, load register jsp. 
        //otherwise get username session attribute and pass to shoppinglist jsp
        String actionValue = request.getParameter("action");
        if (actionValue.equals("register")) {
            String username = request.getParameter("user_name");

            if (username != null) {
                session.setAttribute("userName", username);
                response.sendRedirect("ShoppingList");
                return;
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        } else if (actionValue.equals("add")) {
            //get the list of items from the session
            ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("cartItems");
            //if there's no list of cartItems in the session, create the list of items
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
            //get the item added by user
            //if there an input string , add to the list
            if (!request.getParameter("cart_item").isEmpty()) {
                String item = request.getParameter("cart_item");
                itemList.add(item);
                session.setAttribute("cartItems", itemList);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else if (actionValue.equals("delete")) {
            //get the list of items from the session
            ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("cartItems");
            String deleteItem = request.getParameter("cart_item");
            if (deleteItem != null) {
                itemList.remove(deleteItem);
            }
            session.setAttribute("cartItems", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }

    }

}