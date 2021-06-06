package tn.esprit.spring;

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import tn.esprit.spring.config.LoginFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class TpEntitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpEntitiesApplication.class, args);}
	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
	FacesServlet servlet = new FacesServlet();
	return new ServletRegistrationBean<FacesServlet>(servlet,"*.jsf"); }

	@Bean
	public FilterRegistrationBean<RewriteFilter> rewriteFilter() {
	FilterRegistrationBean<RewriteFilter> rwFilter = new FilterRegistrationBean<RewriteFilter>(new RewriteFilter());
	rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
	rwFilter.addUrlPatterns("/*");
	return rwFilter;}
	/*
	@Bean
	public FilterRegistrationBean<Filter> loginFilter() {
	FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
	registration.setFilter ((Filter) new LoginFilter());
	registration.addUrlPatterns("/pages/admin/*");
	return registration;
	}
	*/
	}


