package fr.eservices.drive.model;

import java.util.List;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;

@StaticMetamodel(Category.class)
public class Category_ {
	public static volatile SingularAttribute<Category, Integer> id;
	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, Integer> orderIdx;
	public static volatile ListAttribute<Category, List<Article>>  articles;
}
