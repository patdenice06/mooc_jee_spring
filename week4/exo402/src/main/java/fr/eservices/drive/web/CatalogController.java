package fr.eservices.drive.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eservices.drive.dao.CatalogDao;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;


// set as a Controller
// map to an url starting with "/catalog"
@Controller
@RequestMapping(path = "/catalog")
public class CatalogController {
	
	// Inject this with spring
	@Autowired
	CatalogDao dao;
	
	// Map this method to "categories.html"
	@RequestMapping(path = "/categories.html")
	public String list(Model model) throws DataException {
		List<Category> categories = dao.getCategories();
		// add categories to model 
		model.addAttribute("categories", categories);
		// return the view name
		return "categories";
	}
	
	// Map this method to "category/ID_CATEGORY.html"
	@RequestMapping(path = "/category/{id}.html")
	public String categoryContent( Model model,
			// Inject this parameter from url variable
			@PathVariable int id ) 
	throws DataException {
		List<Article> articles = dao.getCategoryContent(id);
		// add articles to model
		model.addAttribute("articles", articles);
		// return the view name
		return "category";
	}

}
