package by.taskManager.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import by.taskManager.beans.Task;
import by.taskManager.services.ITaskService;


	@org.springframework.stereotype.Controller
	public class DeleteController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		
		public void init() throws ServletException {
			  super.init();
			  WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			  AutowireCapableBeanFactory ctx = context.getAutowireCapableBeanFactory();
			  ctx.autowireBean(this);
			}
		

	    @Autowired(required = true)
	    private ITaskService taskService;

		public DeleteController() {
			super();
		}

		protected void doGet (HttpServletRequest request,HttpServletResponse response) 
				throws ServletException, IOException 
		{
			String id = request.getParameter("id") != null ? request.getParameter("id") : "";
			Task task = new Task();
			task.setId(Long.valueOf(id));
	        taskService.delete(task);
			
		}
		

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

	}

