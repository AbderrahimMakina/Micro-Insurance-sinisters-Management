package tn.esprit.spring.control;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.dao.entities.Admin;
import tn.esprit.spring.dao.entities.Insured;
import tn.esprit.spring.dao.entities.Insurer;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.service.IUserService;

@Scope(value ="session")
@Controller(value ="userController") // Name of the bean in Spring IoC
@ELBeanName(value ="userController") // Name of the bean used by JSF
@Join(path ="/", to ="/login.jsf")
public class UserControllerImpl {
	
	@Autowired
	IUserService userService;
	
	
	private String login; private String password;
	private    User user;
	private Insurer  insurer ;
	private Insured insured ;
	private Boolean loggedIn;
	private User authenticatedUser;
	private static int idUserC=0;
	private String nom , prenom ,email ;
	
	
	public String doLogin() {
		String navigateTo = "null";
		 user=userService.authenticate(login, password);
		 System.out.println(user.getLogin()+"aaaaaaaaaaaaaaaa") ;
		 idUserC=user.getId();
		 System.out.println(user.getId()+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz") ;

		if (user != null  && user instanceof Insurer) {
			
			
		 navigateTo = "/pages/admin/welcome?faces-redirect=true";
		 System.out.println(user.getLogin() +"aaaaaaaaaaaaaaaa") ;
		 loggedIn = true; 
		}
		
		else if (user != null  && user instanceof Insured){
			
			 navigateTo = "/pages/client/welcomeC?faces-redirect=true";
			 System.out.println(user.getLogin() +"aaaaaaaaaaaaaaaa") ;
			 loggedIn = true; 
		}	else if (user != null  && user instanceof Admin){
			
			 navigateTo = "/pages/admin/welcomeSuperAd?faces-redirect=true";
			 System.out.println(user.getLogin() +"aaaaaaaaaaaaaaaa") ;
			 loggedIn = true; 
		}
		
		else   {
			System.out.println("faaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaux") ;
			

		FacesMessage facesMessage = new FacesMessage("Login Failed: please check your username/password and try again.");

		FacesContext.getCurrentInstance().addMessage("form:btn",facesMessage);
		
		}
		
		return navigateTo;
		}
	
	
	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
		}
	
	public String doRegister(){
		
		return "/pages/client/registration?faces-redirect=true";
	}
	
	
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public  User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}






	public Insurer getInsurer() {
		return insurer;
	}



	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}



	public Insured getInsured() {
		return insured;
	}



	public void setInsured(Insured insured) {
		this.insured = insured;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public IUserService getUserService() {
		return userService;
	}



	public void setUserService(IUserService userService) {
		this.userService = userService;
	}



	public User getAuthenticatedUser() {
		return authenticatedUser;
	}



	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}



	public static int getIdUserC() {
		return idUserC;
	}



	public static void setIdUserC(int idUserC) {
		UserControllerImpl.idUserC = idUserC;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}





}
