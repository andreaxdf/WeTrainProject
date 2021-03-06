package controllers;

import database.dao_classes.AthleteDAO;
import database.dao_classes.TrainerDAO;
import database.dao_classes.UserDAO;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import exceptions.runtime_exception.IsNeitherATrainerNorAnAthleteException;
import models.User;
import engineering.LoggedUserSingleton;
import beans.CredentialsBean;
import beans.UserBean;

import java.sql.SQLException;

public class LoginController {

    public User getLoggedUser() throws SQLException, DBUnreachableException {
        User user = new AthleteDAO().loadAthlete(LoggedUserSingleton.getFc());
        if(user == null){
            user = new TrainerDAO().loadTrainer(LoggedUserSingleton.getFc());
        }
        if(user == null){
            throw new IsNeitherATrainerNorAnAthleteException();
        }
        return user;
    }

    public UserBean login(CredentialsBean credentials) throws SQLException, DBUnreachableException, UserNotFoundException{
        User user = new UserDAO().loadUser(credentials.getEmail(), credentials.getPassword());
        return LoggedUserSingleton.getUserBean(user);
    }
}
