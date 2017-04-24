/**
  *project demo-l
  *@author changchun.wu
  *2017年4月24日上午10:51:31
  */
package com.jianfei.d.base.mybatis;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import lombok.Setter;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import com.jianfei.d.base.mybatis.interceptor.PageInterceptor;
import com.jianfei.d.entity.common.Page;


public class MySqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>,InitializingBean,ApplicationListener<ApplicationEvent>{
	
	private static final Log LOGGER = LogFactory.getLog(MySqlSessionFactoryBean.class);
	
	private Resource configLocation;
	
	private Configuration configuration;
	
	private Resource[] mapperLocations;
	
	private DataSource dataSource;
	
	private TransactionFactory transactionFactory;
	
	private Properties configurationProperties;
	
	private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
	
	private SqlSessionFactory sqlSessionFactory;
	
	private String environment = SqlSessionFactoryBean.class.getSimpleName();
	
	private boolean failFast;
	
	private Interceptor[] plugins;
	
	private TypeHandler<?>[] typeHandlers;
	
	private String typeHandlersPackage;
	
	private Class<?>[] typeAliases;
	
	private String typeAliasesPackage;
	
	private Class<?> typeAliasesSuperType;
	
	private DatabaseIdProvider databaseIdProvider;
	
	private Class<? extends VFS> vfs;
	
	private Cache cache;
	
	private ObjectFactory objectFactory;
	
	private ObjectWrapperFactory objectWrapperFactory;
	
	@Setter
	private String enumBasePackage;
	
	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}
	
	public void setObjectWrapperFactory(
			ObjectWrapperFactory objectWrapperFactory) {
		this.objectWrapperFactory = objectWrapperFactory;
	}
	
	public DatabaseIdProvider getDatabaseIdProvider() {
		return databaseIdProvider;
	}
	
	public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {
		this.databaseIdProvider = databaseIdProvider;
	}
	
	public void setVfs(Class<? extends VFS> vfs) {
		this.vfs = vfs;
	}
	
	public Class<? extends VFS> getVfs() {
		return vfs;
	}
	
	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	public void setPlugins(Interceptor[] plugins) {
		this.plugins = plugins;
	}
	
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}
	
	public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {
		this.typeAliasesSuperType = typeAliasesSuperType;
	}
	
	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}
	
	public void setTypeHandlers(TypeHandler<?>[] typeHandlers) {
		this.typeHandlers = typeHandlers;
	}
	
	public void setTypeAliases(Class<?>[] typeAliases) {
		this.typeAliases = typeAliases;
	}
	
	public void setFailFast(boolean failFast) {
		this.failFast = failFast;
	}
	
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	
	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}
	
	public void setDataSource(DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
	      // If we got a TransactionAwareDataSourceProxy, we need to perform
	      // transactions for its underlying target DataSource, else data
	      // access code won't see properly exposed transactions (i.e.
	      // transactions for the target DataSource).
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
		} else {
	    	this.dataSource = dataSource;
	    }
	}
	
	public void setSqlSessionFactoryBuilder(
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
		this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
	}
	
	
	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 notNull(dataSource, "Property 'dataSource' is required");
		 notNull(sqlSessionFactoryBuilder, "Property 'sqlSessionFactoryBuilder' is required");
		 state((configuration == null && configLocation == null) || !(configuration != null && configLocation != null),"Property 'configuration' and 'configLocation' can not specified with together");
		 this.sqlSessionFactory = buildSqlSessionFactory();
	}
	
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		Configuration configuration;

	    XMLConfigBuilder xmlConfigBuilder = null;
	    if (this.configuration != null) {
	      configuration = this.configuration;
	      if (configuration.getVariables() == null) {
	        configuration.setVariables(this.configurationProperties);
	      } else if (this.configurationProperties != null) {
	        configuration.getVariables().putAll(this.configurationProperties);
	      }
	    } else if (this.configLocation != null) {
	      xmlConfigBuilder = new XMLConfigBuilder(this.configLocation.getInputStream(), null, this.configurationProperties);
	      configuration = xmlConfigBuilder.getConfiguration();
	    } else {
	      if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("Property 'configuration' or 'configLocation' not specified, using default MyBatis Configuration");
	      }
	      configuration = new Configuration();
	      if (this.configurationProperties != null) {
	        configuration.setVariables(this.configurationProperties);
	      }
	    }

	    if (this.objectFactory != null) {
	      configuration.setObjectFactory(this.objectFactory);
	    }

	    if (this.objectWrapperFactory != null) {
	      configuration.setObjectWrapperFactory(this.objectWrapperFactory);
	    }

	    if (this.vfs != null) {
	      configuration.setVfsImpl(this.vfs);
	    }

	    if (hasLength(this.typeAliasesPackage)) {
	      String[] typeAliasPackageArray = tokenizeToStringArray(this.typeAliasesPackage,
	          ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
	      for (String packageToScan : typeAliasPackageArray) {
	        configuration.getTypeAliasRegistry().registerAliases(packageToScan,
	                typeAliasesSuperType == null ? Object.class : typeAliasesSuperType);
	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Scanned package: '" + packageToScan + "' for aliases");
	        }
	      }
	    }

	    if (!isEmpty(this.typeAliases)) {
	      for (Class<?> typeAlias : this.typeAliases) {
	        configuration.getTypeAliasRegistry().registerAlias(typeAlias);
	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Registered type alias: '" + typeAlias + "'");
	        }
	      }
	    }

	    if (!isEmpty(this.plugins)) {
	      for (Interceptor plugin : this.plugins) {
	        configuration.addInterceptor(plugin);
	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Registered plugin: '" + plugin + "'");
	        }
	      }
	    }

	    if (hasLength(this.typeHandlersPackage)) {
	      String[] typeHandlersPackageArray = tokenizeToStringArray(this.typeHandlersPackage,
	          ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
	      for (String packageToScan : typeHandlersPackageArray) {
	        configuration.getTypeHandlerRegistry().register(packageToScan);
	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Scanned package: '" + packageToScan + "' for type handlers");
	        }
	      }
	    }

	    if (!isEmpty(this.typeHandlers)) {
	      for (TypeHandler<?> typeHandler : this.typeHandlers) {
	        configuration.getTypeHandlerRegistry().register(typeHandler);
	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Registered type handler: '" + typeHandler + "'");
	        }
	      }
	    }
	    
	    if (hasLength(this.enumBasePackage)) {  
	        ResolverUtil<Class<?>> resolverUtil = new ResolverUtil<Class<?>>();  
	        resolverUtil.find(new ResolverUtil.IsA(Enum.class), this.enumBasePackage);  
	        Set<Class<? extends Class<?>>> mTypes = resolverUtil.getClasses();  
	        for (Class<?> javaTypeClass : mTypes) {  
	            configuration.getTypeHandlerRegistry().register(javaTypeClass, EnumOrdinalTypeHandler.class);  
	        }  
	    }  

	    if (this.databaseIdProvider != null) {//fix #64 set databaseId before parse mapper xmls
	      try {
	        configuration.setDatabaseId(this.databaseIdProvider.getDatabaseId(this.dataSource));
	      } catch (SQLException e) {
	        throw new NestedIOException("Failed getting a databaseId", e);
	      }
	    }

	    if (this.cache != null) {
	      configuration.addCache(this.cache);
	    }

	    if (xmlConfigBuilder != null) {
	      try {
	        xmlConfigBuilder.parse();

	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Parsed configuration file: '" + this.configLocation + "'");
	        }
	      } catch (Exception ex) {
	        throw new NestedIOException("Failed to parse config resource: " + this.configLocation, ex);
	      } finally {
	        ErrorContext.instance().reset();
	      }
	    }

	    if (this.transactionFactory == null) {
	      this.transactionFactory = new SpringManagedTransactionFactory();
	    }

	    configuration.setEnvironment(new Environment(this.environment, this.transactionFactory, this.dataSource));

	    if (!isEmpty(this.mapperLocations)) {
	      for (Resource mapperLocation : this.mapperLocations) {
	        if (mapperLocation == null) {
	          continue;
	        }

	        try {
	          XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(),
	              configuration, mapperLocation.toString(), configuration.getSqlFragments());
	          xmlMapperBuilder.parse();
	        } catch (Exception e) {
	          throw new NestedIOException("Failed to parse mapping resource: '" + mapperLocation + "'", e);
	        } finally {
	          ErrorContext.instance().reset();
	        }

	        if (LOGGER.isDebugEnabled()) {
	          LOGGER.debug("Parsed mapper file: '" + mapperLocation + "'");
	        }
	      }
	    } else {
	      if (LOGGER.isDebugEnabled()) {
	        LOGGER.debug("Property 'mapperLocations' was not specified or no matching resources found");
	      }
	    }
	    
	    List<Interceptor> interceptors = configuration.getInterceptors();
	    for(Interceptor interceptor : interceptors){
	        if(interceptor instanceof com.jianfei.d.base.mybatis.interceptor.PageInterceptor){
	            this.setPageInterceptorPageId((PageInterceptor)interceptor, configuration.getMappedStatements());
	        }
	    }
	    return this.sqlSessionFactoryBuilder.build(configuration);
		
	}
	
	private void setPageInterceptorPageId(PageInterceptor pageInterceptor, Collection<MappedStatement> mappedStatements){
	      try{
	          for(Object obj : mappedStatements){
	              if(obj instanceof MappedStatement){
	                  MappedStatement ms = (MappedStatement)obj;
	                  if(ms.getSqlCommandType() == SqlCommandType.SELECT){
	                      String id = ms.getId();
	                      String className = id.substring(0, id.lastIndexOf("."));
	                      String methodName = id.substring(id.lastIndexOf(".") + 1);
	                      try {
	                          Class<?> daoClass = Class.forName(className);
	                          Method daoClassMethods[] = daoClass.getMethods();
	                          for (Method method : daoClassMethods) {
	                              if (method.getName().equals(methodName) && method.getReturnType() == Page.class) {
	                                  pageInterceptor.addPageId(id);
	                              }
	                          }
	                      } catch (Exception e) {
	                          e.printStackTrace();
	                      }
	                  }
	              }
	          }
	      }
	      catch(Exception e){
	          e.printStackTrace();
	      }
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public SqlSessionFactory getObject() throws Exception {
	    if (this.sqlSessionFactory == null) {
	      afterPropertiesSet();
	    }

	    return this.sqlSessionFactory;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public Class<? extends SqlSessionFactory> getObjectType() {
	    return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public boolean isSingleton() {
	    return true;
	  }

	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void onApplicationEvent(ApplicationEvent event) {
	    if (failFast && event instanceof ContextRefreshedEvent) {
	      // fail-fast -> check all statements are completed
	      this.sqlSessionFactory.getConfiguration().getMappedStatementNames();
	    }
	  }

}
