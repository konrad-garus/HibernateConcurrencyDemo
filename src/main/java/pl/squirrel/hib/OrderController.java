package pl.squirrel.hib;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class OrderController {
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("/page.html")
	public ModelAndView renderMain() {
		prepareOrderIfNeeded();
		return renderForm(loadOrder());
	}

	@RequestMapping(value = "/page.html", method = RequestMethod.POST)
	public ModelAndView formSubmitted(Order order) {
		order = updateOrder(order);
		return renderForm(order);
	}

	private ModelAndView renderForm(Order order) {
		ModelAndView result = new ModelAndView("page");
		result.addObject("command", order);
		return result;
	}

	/**
	 * Create sample data on first run
	 */
	private void prepareOrderIfNeeded() {
		List<Order> orders = entityManager.createQuery("from Order")
				.getResultList();
		if (orders.isEmpty()) {
			Order order = new Order(1);
			order.setDescription("42,000 containers of Jedi Goofy figurines");
			order.setStatus("New");
			entityManager.persist(order);
		}
	}

	private Order loadOrder() {
		return (Order) entityManager.createQuery("from Order")
				.getSingleResult();
	}

	private Order updateOrder(Order order) {
		return entityManager.merge(order);
	}
}
