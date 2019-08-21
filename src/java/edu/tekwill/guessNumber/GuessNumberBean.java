package edu.tekwill.guessNumber;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "GuessNumberBean")
@SessionScoped
public class GuessNumberBean implements Serializable {

    Integer randomInt;
    Integer userNumber;

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public GuessNumberBean() {
        Random randomGR = new Random();
        randomInt = randomGR.nextInt(10);
        System.out.println("Duke's number: " + randomInt);
    }

    public String getResponse() {
        if (userNumber != null && (userNumber.equals(randomInt))) {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
            return "You got it right";
        } else {
            return String.format("<p>Sorry, %d is not what I have thought about (Hint: %d). Guess again....</p>", userNumber, randomInt);
        }
    }

}
