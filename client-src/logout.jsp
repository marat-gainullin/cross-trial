<!DOCTYPE html>
<html>
    <% 
        request.getSession().invalidate(); 
        response.sendRedirect(request.getContextPath() + "/logged-in.jsp"); 
    %>
</html>

