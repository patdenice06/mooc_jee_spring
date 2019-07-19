package fr.eservices.drive.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Article_;
import fr.eservices.drive.model.Category;
import fr.eservices.drive.model.Category_;
import fr.eservices.drive.model.Perishable;

public class CatalogDaoJPAImpl implements CatalogDao {
	
	private static final int MAX_PERISHABLES = 500;
	private EntityManager em;
	
	public CatalogDaoJPAImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Category> getCategories() {
		/*
		 * SQL:
		 * SELECT * FROM CATEGORY
		 * ORDER BY ORDERIDX  ASC;
		 * 
		 * JPQL:
		 * SELECT c FROM Category c ORDER BY c.orderIdx ASC
		 */
		
		// Using the criteria query API
		TypedQuery<Category> tq = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Category> cq = cb.createQuery(Category.class);
			Root<Category> cat = cq.from(Category.class);
			cq.select(cat);
			cq.orderBy( cb.asc( cat.get("orderIdx") ) );
			tq = em.createQuery(cq);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		// Execute the query and return the result
		return tq.getResultList();		
	}

	
	@Override
	public List<Article> getArticles() throws DataException {
		// SQL: SELECT * FROM ARTICLE
		TypedQuery<Article> tq = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Article> cq = cb.createQuery(Article.class);
			Root<Article> art = cq.from(Article.class);
			cq.select(art);
			tq = em.createQuery(cq);
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return tq.getResultList();
	}


	@Override
	public List<Category> getArticleCategories(int articleId) throws DataException {
		/*
	 		SQL:
			SELECT * FROM CATEGORY 
			JOIN ARTICLE AS a
			WHERE a.ID = <articleId>			
		 */
		// JPQL
		List<Category> cat = null;	
		
		try {
			if( em.find(Article.class, articleId) == null )
				throw new DataException("Article with id: "+ articleId +" does not exist");
			else {
					
					 cat = em.createQuery("select c from Category c join c.articles as a where a.id = :artid", Category.class )
						.setParameter("artid", articleId)
						.getResultList();				 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Exception while reading the database.", e);

		}
		
		return cat;		
	}	
	
	
	@Override
	public List<Category> getArticleCategories_Criteria(int articleId) throws DataException {
		List<Category> categories = null;
		
		if( em.find(Article.class, articleId) == null )
			throw new DataException("Article with id: "+ articleId +" does not exist");		
		else {
			try {
				CriteriaBuilder cb = em.getCriteriaBuilder();		
				CriteriaQuery<Category> cq = cb.createQuery(Category.class);
				Root<Category> cat = cq.from(Category.class);				
				Join<Category, List<Article>> art = cat.join(Category_.articles);
				cq.select(cat)
					 .where(
							  cb.equal( art.get("id"), articleId )

					 );				
				categories = em.createQuery( cq ).getResultList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return categories;
	}

	
	@Override
	public List<Category> getArticleCategories_Criteria_2(int articleId) throws DataException {
        if (!exists(Article.class, articleId)) {
            throw new DataException("No article with id = " + articleId);
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Article> art = cq.from(Article.class);
        Join<Article, Category> cat = art.join("categories");
        cq.select(cat)
          .where(cb.equal(art.get(Article_.id), articleId));
        List<Category> categories = em.createQuery(cq).getResultList();
        return categories;
	}	

	

 	@Override
	public List<Article> getCategoryContent(int categoryId) throws DataException {
 		List<Article> articles = null;
 		
		try {
			
	 		if( em.find(Category.class, categoryId) == null )
	 			throw new DataException("Category with id: " + categoryId +" does not exist");
	 		else {
				 articles = em.createQuery("select a from Article a join a.categories as c where c.id = :catid", Article.class )
					.setParameter("catid", categoryId)
					.getResultList();
	 		}
	 		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Exception while reading the database.", e); 
		}
 		
		return articles;
	}
 	

	@Override
	public List<Perishable> getPerished(Date day) throws DataException {
		 List<Perishable> perishables = null;
		try {
			 perishables =  em.createQuery("select p from Perishable p where p.bestBefore < :perishableDate", Perishable.class)
					.setParameter( "perishableDate", day )
					.getResultList();	
			 
			 // DataException if too many perishables
			 int nb_perishables = perishables.size();
			 if( nb_perishables > MAX_PERISHABLES )
				 throw new DataException("Too many perishable articles = " + nb_perishables);
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException("Exception while reading the database.", e);
		}
		
		// DEBUG
		for (Perishable perishable : perishables) {
			System.out.println( perishable.toString() );
		}
		
		return perishables;		
	}

    
	private <T> boolean exists(Class<T> c, int id) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> rt = cq.from(c);
        cq.select(cb.count(rt))
          .where(cb.equal(rt.get("id"), id));
        long count = em.createQuery(cq).getSingleResult();
        return count != 0;
    }

	
}
