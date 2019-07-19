package fr.eservices.drive.model;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;

@StaticMetamodel(Article.class)
public class Article_ {
	public static volatile SingularAttribute<Article, Integer> id;
	public static volatile SingularAttribute<Article, String> ean13;
	public static volatile SingularAttribute<Article, String> name;
	public static volatile SingularAttribute<Article, Integer> price;
	public static volatile SingularAttribute<Article, Double> vat;
	public static volatile SingularAttribute<Article, String> img;
	public static volatile ListAttribute<Article, List<Category>>  categories;
}
