package by.taskManager.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import by.taskManager.beans.Task;
import by.taskManager.json.JsonTask;
import by.taskManager.services.IPriorityService;
import by.taskManager.services.ITaskService;

public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		super.init();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		AutowireCapableBeanFactory ctx = context.getAutowireCapableBeanFactory();
		ctx.autowireBean(this);
	}

	@Autowired(required = true)
	private IPriorityService priorityService;

	@Autowired(required = true)
	private ITaskService taskService;

	public TaskController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JsonTask tt = new JsonTask();
		Integer page = request.getParameter("page") != null ? Integer.valueOf(request.getParameter("page")) : 0;
		String id = request.getParameter("id") != null ? request.getParameter("id") : "";
		Task task = taskService.get(Long.valueOf(id));
		tt.setTask(task);
		tt.setPriorities(priorityService.getAll());
		tt.setCurrentPage(page);
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String message = mapper.writeValueAsString(tt);
		out.print(message);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
