package wuyi.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import wuyi.util.BaseModelState;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.yiwutech.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.yiwutech.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 *         Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 */
@Transactional
public class MyGenericDaoHibernate<T, PK extends Serializable> extends GenericDAOImpl<T, Serializable> {
	

	/**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
	
    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public MyGenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public MyGenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        super.setSessionFactory(sessionFactory);
    }


    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
    	super.setSessionFactory(sessionFactory);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }
    
	public void update(T object) {
		// TODO Auto-generated method stub
		if (!persistentClass.isInstance(object))
			throw new IllegalArgumentException(
					"Object class does not match dao type.");
		Session ses = getSession();
		ses.update(object);
		ses.flush();
	}
	
	public T findActive(PK id){
		T entity = (T) getSession().createCriteria(persistentClass)
				.add(Restrictions.eq("id", id))
				.add(Restrictions.ne("state", BaseModelState.DELETE)).uniqueResult();
		return entity;
	}
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);
        for (String s : queryParams.keySet()) {
            Object val = queryParams.get(s);
            if (val instanceof Collection) {
                namedQuery.setParameterList(s, (Collection) val);
            } else {
                namedQuery.setParameter(s, val);
            }
        }
        return namedQuery.list();
    }

}
