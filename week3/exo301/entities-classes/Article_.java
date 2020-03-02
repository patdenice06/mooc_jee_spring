package fr.eservices.drive.metamodel;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;

@StaticMetamodel(Article.class)
public class Article_ {
	public static volatile SingularAttribute<Article, Integer> id; 
	public static volatile SingularAttribute<Article, String> ean; 
	public static volatile SingularAttribute<Article, Integer> price; 
	public static volatile SingularAttribute<Article, Double> vat; 
	public static volatile SingularAttribute<Article, String> name; 
	public static volatile SingularAttribute<Article, List<Category>> categories; 
	
}
