package com.sample.configuration;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.sample.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class, includeFilters = @Filter(type = FilterType.ANNOTATION, value = Controller.class) , useDefaultFilters = false)
class WebMvcConfiguration extends WebMvcConfigurationSupport {

	private static final String MESSAGE_SOURCE = "WEB-INF/i18n/messages,WEB-INF/i18n/application";
	private static final String RESOURCES_HANDLER = "/resources/";
	private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	/**
	 * Handles HTTP GET requests for /resources/** by efficiently serving up static resources
	 * 
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations(RESOURCES_LOCATION);
	}

	/**
	 * Allows for mapping the DispatcherServlet to "/" by forwarding static resource requests to the container's default Servlet
	 * 
	 * @param configurer
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Register "global" interceptor beans to apply to all registered HandlerMappings
	 * 
	 * @param registry
	 */
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ThemeChangeInterceptor());
		registry.addInterceptor(new ThemeChangeInterceptor());
		registry.addInterceptor(new LocaleChangeInterceptor());
		super.addInterceptors(registry);
	}

	/**
	 * Selects a static view for rendering without the need for an explicit controller.
	 * 
	 * @param registry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/uncaughtException").setViewName("");
		registry.addViewController("/resourceNotFound");
		registry.addViewController("/dataAccessFailure");
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	/**
	 * Resolves localized messages*.properties and application.properties files in the application to allow for internationalization. The messages*.properties
	 * files translate Roo generated messages which are part of the admin interface, the application.properties resource bundle localizes all application
	 * specific messages such as entity names and menu items.
	 * 
	 * @return
	 */
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(MESSAGE_SOURCE);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	/**
	 * Store preferred language configuration in a cookie
	 * 
	 * @return
	 */
	@Bean(name = "localeResolver")
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("locale");
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	/**
	 * Resolves localized <theme_name>.properties files in the classpath to allow for theme support
	 * 
	 * @return
	 */
	@Bean(name = "themeSource")
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
		return themeSource;
	}

	/**
	 * Store preferred theme configuration in a cookie
	 * 
	 * @return
	 */
	@Bean(name = "themeResolver")
	public CookieThemeResolver themeResolver() {
		CookieThemeResolver themeResolver = new CookieThemeResolver();
		themeResolver.setCookieName("theme");
		themeResolver.setDefaultThemeName("standard");
		return themeResolver;
	}

	/**
	 * Enable this for integration of file upload functionality
	 * 
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}

	/**
	 * Enable this for tiles3 integration
	 * 
	 * @return
	 */
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	/**
	 * Enable this for tiles3 integration
	 * 
	 * @return
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/layouts/layouts.xml", "/views/**/views.xml" });
		return tilesConfigurer;
	}

	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping();
		requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
		requestMappingHandlerMapping.setUseTrailingSlashMatch(false);
		return requestMappingHandlerMapping;
	}

	/**
	 * This bean resolves specific types of exceptions to corresponding logical - view names for error views. The default behaviour of DispatcherServlet - is to
	 * propagate all exceptions to the servlet container: this will happen - here with all other types of exceptions.
	 * 
	 * @return
	 */
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties exceptionMappings = new Properties();
		exceptionMappings.setProperty(".DataAccessException", "dataAccessFailure");
		exceptionMappings.setProperty(".NoSuchRequestHandlingMethodException", "resourceNotFound");
		exceptionMappings.setProperty(".TypeMismatchException", "resourceNotFound");
		exceptionMappings.setProperty(".MissingServletRequestParameterException", "resourceNotFound");
		resolver.setExceptionMappings(exceptionMappings);
		return resolver;
	}

}
