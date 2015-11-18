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
import by.taskManager.services.ITaskService;

public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		super.init();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		AutowireCapableBeanFactory ctx = context.getAutowireCapableBeanFactory();
		ctx.autowireBean(this);
	}

	@Autowired(required = true)
	private ITaskService taskService;

	public AddController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id") != null ? request.getParameter("id") : "";
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		String description = request.getParameter("description") != null ? request.getParameter("description") : "";
		String priority = request.getParameter("priority") != null ? request.getParameter("priority") : "";
		JsonTask jt = new JsonTask();

		if (name.length() > 50) {
			jt.setError("You cannot enter more than 50 characters in the \"Task\"!");
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String error = mapper.writeValueAsString(jt);
			out.print(error);
		} else if (description.length() > 1000) {
			jt.setError("You cannot enter more than 1000 characters in the \"Description\"!");
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String error = mapper.writeValueAsString(jt);
			out.print(error);
		} else if (name == "" || description == "" || priority == "") {
			jt.setError("Field cannot be left blank!");
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String error = mapper.writeValueAsString(jt);
			out.print(error);
		} else {
			Task task = new Task();
			if (!id.isEmpty()) {
				task.setId(Long.valueOf(id));
			}
			task.setName(name);
			task.setDescription(description);
			task.setPriorityId(Integer.valueOf(priority));
			taskService.saveOrUpdate(task);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
